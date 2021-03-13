package com.give.donagi.main.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.give.donagi.main.mapper.UserMainSQLMapper;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.vo.*;

@Service
public class UserMainServiceImpl {
	@Autowired
	private UserMainSQLMapper mainMapper;
	@Autowired
	private UserMemberSQLMapper memberMapper;
	
	// 펀딩 리스트 받기
	public ArrayList<HashMap<String, Object>> getFundingList(HashMap<String, Object> searchMap) {
		ArrayList<HashMap<String, Object>> fundingList = new ArrayList<HashMap<String,Object>>(); // 펀딩 리스트
		ArrayList<MSTFundingVo> tempFundingVoList = this.mainMapper.selectFundingList(searchMap); // 임시펀딩 정보 리스트
		CommonsDateConverter dateConverter = new CommonsDateConverter(); // 날짜 포맷 변환
		
		if(tempFundingVoList != null) {
			
			// 펀딩 리스트 재설정 처리
			for(MSTFundingVo mstFundingVo: rebuildFundingList(tempFundingVoList, Integer.parseInt((searchMap.get("status")).toString()))) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				int totalPoint = getTotalPoint(mstFundingVo.getMst_f_no());
				int percent = calcPercent(totalPoint, mstFundingVo.getMst_f_goal());
				
				// 펀딩리스트 정보 설정
				map.put("memberVo", this.memberMapper.selectByNo(mstFundingVo.getM_no())); // 펀딩 개설자 정보
				map.put("mstFundingVo", mstFundingVo); // 펀딩 정보
				map.put("dateFrom", dateConverter.getDate(mstFundingVo.getMst_f_date_from())); // 펀딩 시작 기간
				map.put("dateTo", dateConverter.getDate(mstFundingVo.getMst_f_date_to())); // 펀딩 종료 기간
				map.put("fundingStatus", dateConverter.getDateStatus(mstFundingVo.getMst_f_date_from(), mstFundingVo.getMst_f_date_to())); // 펀딩 상태
				map.put("thumnailImg", this.mainMapper.selectThumnailByFNo(mstFundingVo.getMst_f_no())); // 펀딩 썸네일
				map.put("totalPoint", totalPoint);
				map.put("percent", percent);
				
				fundingList.add(map);
			}

		}else {
			fundingList = null;
		}
		
		System.out.println("[System] getFundingList");
		
		return fundingList;
	}
	
	
	
	// 펀딩 통계
	public HashMap<String, Object> getFundingStatistics() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("fundingTotalPoint", getFundingTotalPoint());
		result.put("fundingParticipantCount", this.mainMapper.selectFundingParticipantCount());
		
		return result;
	}
	
	private int getFundingTotalPoint() {
		ArrayList<MSTFundingVo> fundingAllList = this.mainMapper.selectAllFundingList();
		int fundingTotalPoint = 0;
		
		for(MSTFundingVo funding: fundingAllList) {
			
			fundingTotalPoint += getTotalPoint(funding.getMst_f_no());
		}
		
		return fundingTotalPoint;
	}
	
	// 상태에 따른 펀딩리스트 재설정
	private ArrayList<MSTFundingVo> rebuildFundingList(ArrayList<MSTFundingVo> list, int targetStatus) {
		ArrayList<MSTFundingVo> resultList = new ArrayList<MSTFundingVo>();
		CommonsDateConverter dateConverter = new CommonsDateConverter();
		
		if(targetStatus == 0) { // 검색조건이 없을 시
			resultList = list;
			
		}else { // 검색조건이 있을 시
		
			for(MSTFundingVo vo: list) {
				int status = dateConverter.getDateStatus(vo.getMst_f_date_from(), vo.getMst_f_date_to());
				
				if(status == targetStatus) {
					resultList.add(vo);
				}
			}
		}
		
		return resultList;
	}
	
	// 총 펀딩가격 조회
	private int getTotalPoint(int mst_f_no) {
		ArrayList<OrderFVo> orders = this.mainMapper.selectOrderListByFundingNo(mst_f_no);
		int total = 0;
		
		for(OrderFVo order: orders) {
			ArrayList<ODetailVo> details = this.mainMapper.selectOrderDetailsByOrderNo(order.getOf_no());
			
			for(ODetailVo detail: details) {
				MSTFOptionVo option = this.mainMapper.selectOptionByNo(detail.getMst_fo_no());
				
				total += option.getMst_fo_price() * detail.getOd_quantity();
			}
		}
		
		return total;
	}
	
	// 펀딩 퍼센트 조회
	private int calcPercent(int price, int goal) {
		int percent = 0;
		double dblPrice = price;
		double dblGoal = goal;
		double resultDouble = Math.floor((dblPrice/dblGoal * 100));
		
		if(resultDouble < 1) {
			percent = 0;
			
		}else if(resultDouble >= 1) {
			percent = (int)resultDouble;
			
		}else if(resultDouble >= 100) {
			percent = 100;
			
		}
		
		return percent;
	}
	
	public ArrayList<HashMap<String, Object>> getVolunteerList(HashMap<String, Object> searchMap)
	{
		ArrayList<HashMap<String, Object>> VolunteerList = new ArrayList<HashMap<String,Object>>();
		ArrayList<MSTVolunteerVo> tempVolunteerList = this.mainMapper.selectVolunteerList(searchMap);
		CommonsDateConverter dateConvert = new CommonsDateConverter();
		
		if(tempVolunteerList != null)
		{
			for(MSTVolunteerVo mstVolunteerVo : rebuildVolunteerList(tempVolunteerList,Integer.parseInt((searchMap.get("status")).toString())))
			{
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				map.put("imgvo", this.mainMapper.selectVImgByVNo(mstVolunteerVo.getMst_v_no()));
				map.put("memberVo", this.memberMapper.selectByNo(mstVolunteerVo.getM_no()));
				map.put("mstVolunteerVo", mstVolunteerVo);
				map.put("dateFrom", dateConvert.getDate(mstVolunteerVo.getmst_v_from()));
				map.put("dateTo", dateConvert.getDate(mstVolunteerVo.getmst_v_to()));
				map.put("volunteerStauts", dateConvert.getDateStatus(mstVolunteerVo.getmst_v_from(), mstVolunteerVo.getmst_v_to()));
				
				VolunteerList.add(map);
			}
		}
		else
		{
			VolunteerList=null;
		}
		
		System.out.println("[System] getVolunteerList");
		
		return VolunteerList;
	}
	
	private ArrayList<MSTVolunteerVo> rebuildVolunteerList(ArrayList<MSTVolunteerVo> list, int targetStatus) {
		ArrayList<MSTVolunteerVo> resultList = new ArrayList<MSTVolunteerVo>();
		CommonsDateConverter dateConverter = new CommonsDateConverter();
		
		if(targetStatus == 0) { // 검색조건이 없을 시
			resultList = list;
			
		}else { // 검색조건이 있을 시
		
			for(MSTVolunteerVo vo: list) {
				int status = dateConverter.getDateStatus(vo.getmst_v_from(), vo.getmst_v_to());
				
				if(status == targetStatus) {
					resultList.add(vo);
				}
			}
		}
		
		return resultList;
	}
	
	
	public HashMap<String, Object> getVolunteerStatistics() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("volunteerParticipantCount", this.mainMapper.selectVolunteerParticipantCount());
		
		return result;
	}
	
	// 기부 리스트 받기
		public ArrayList<HashMap<String, Object>> getDonateList() {
			ArrayList<HashMap<String, Object>> donateList = new ArrayList<HashMap<String,Object>>(); // 기부 리스트
			ArrayList<MSTDonateVo> tempDonateVoList = this.mainMapper.selectDonateList(); // 임시기부 정보 리스트
			CommonsDateConverter dateConverter = new CommonsDateConverter(); // 날짜 포맷 변환
			
			// 기부 리스트 재설정 처리
			for(MSTDonateVo mstDonateVo: tempDonateVoList) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				int totalPoint = donatePointSum(mstDonateVo.getMst_d_no());
				
				// 기부리스트 정보 설정
				map.put("memberVo", this.memberMapper.selectByNo(mstDonateVo.getM_no())); // 기부 개설자 정보
				map.put("mstDonateVo", mstDonateVo); // 기부 정보
				map.put("dateFrom", dateConverter.getDate(mstDonateVo.getMst_d_date_from())); // 기부 시작 기간
				map.put("dateTo", dateConverter.getDate(mstDonateVo.getMst_d_date_to())); // 기부 종료 기간
				map.put("donateStatus", dateConverter.getDateStatus(mstDonateVo.getMst_d_date_from(), mstDonateVo.getMst_d_date_to())); // 기부 상태
				map.put("thumnailImg", this.mainMapper.selectThumnailByDNo(mstDonateVo.getMst_d_no())); // 기부 썸네일
				map.put("totalPoint", totalPoint);
				map.put("percent", calcPercent(totalPoint, mstDonateVo.getMst_d_goal()));
				
				donateList.add(map);
			}

			System.out.println("[System] getDonateList");
			
			return donateList;
		}
		
		// 기부 통계
		public HashMap<String, Object> getDonateStatistics() {
			HashMap<String, Object> result = new HashMap<String, Object>();
			
			result.put("donateTotalPoint", getDonateTotalPoint());
			result.put("donateParticipantCount", this.mainMapper.selectDonateParticipantCount());
			
			return result;
		} 
		
		// 각 기부 합계 조회
		private int donatePointSum(int mstDNo){
			ArrayList<DonateDVo> donateList = this.mainMapper.selectPointByMSTDNo(mstDNo);
			int result = 0;
			
			if(donateList != null) {
				
				for(DonateDVo donateVo: donateList) {
					
					result += donateVo.getDd_donate_point();
				}
			}
			
			return result;
		}
		
		// 기부 총 포인트 조회
		private int getDonateTotalPoint() {
			ArrayList<DonateDVo> donateList = this.mainMapper.selectAllDonate();
			int donateTotalPoint = 0;
			
			for(DonateDVo donate: donateList) {
				
				donateTotalPoint += donate.getDd_donate_point();
			}
			
			return donateTotalPoint;
		}
}
