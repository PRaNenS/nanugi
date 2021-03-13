package com.give.donagi.donate.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.give.donagi.donate.mapper.UserDonateSQLMapper;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.vo.*;

@Service
public class UserDonateServiceImpl {
	@Autowired
	private UserDonateSQLMapper donateMapper;
	@Autowired
	private UserMemberSQLMapper memberMapper;
	
	// 카테고리 리스트 정보 얻기
	public ArrayList<MSTDCategoryVo> getCategoryList() {
		
		System.out.println("[System] getCategoryList");
		
		return this.donateMapper.selectMSTDCategory();
	}
	
	// 기부 개설
	public int createDonate(HashMap<String, Object> loginUser, MSTDonateVo mstDonateParam, MSTDImgVo mstDImgParam) {
		int donateNo = this.donateMapper.createMSTDNo();
		
		mstDonateParam.setMst_d_no(donateNo); // 기부번호 생성
		mstDonateParam.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no()); // 기부 유저 설정
		mstDImgParam.setMst_d_no(donateNo);
		
		this.donateMapper.insertMSTDonate(mstDonateParam); // 기부정보 입력
		this.donateMapper.insertDonateImg(mstDImgParam); // 기부 이미지 생성

		System.out.println("[System] createDonate");
		
		return mstDonateParam.getMst_d_no();
	}
	
	// 페이지수 받기
	public int pageCount(HashMap<String, Object> searchMap) {
		int tempPage = this.donateMapper.searchForPageCount(searchMap); // 임시 페이지 수
		int pageCount = 0; // 페이지 수
		
		if(tempPage > 0) { // 검색 조건에 맞는 결과가 있을 경우
			pageCount = (int)tempPage;
			
		}else { // 검색 조건에 맞는 결과가 없을 경우
			pageCount = 1;
		}
		
		return pageCount;
	}
	
	// 기부 리스트 받기
	public ArrayList<HashMap<String, Object>> getDonateList(HashMap<String, Object> searchMap) {
		ArrayList<HashMap<String, Object>> donateList = new ArrayList<HashMap<String,Object>>(); // 기부 리스트
		ArrayList<MSTDonateVo> tempDonateVoList = this.donateMapper.selectDonateList(searchMap); // 임시기부 정보 리스트
		CommonsDateConverter dateConverter = new CommonsDateConverter(); // 날짜 포맷 변환
		
		// 기부 리스트 재설정 처리
		for(MSTDonateVo mstDonateVo: rebuildDonateList(tempDonateVoList, Integer.parseInt((searchMap.get("status")).toString()))) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			int totalPoint = donatePointSum(mstDonateVo.getMst_d_no());
			
			// 기부리스트 정보 설정
			map.put("memberVo", this.memberMapper.selectByNo(mstDonateVo.getM_no())); // 기부 개설자 정보
			map.put("mstDonateVo", mstDonateVo); // 기부 정보
			map.put("dateFrom", dateConverter.getDate(mstDonateVo.getMst_d_date_from())); // 기부 시작 기간
			map.put("dateTo", dateConverter.getDate(mstDonateVo.getMst_d_date_to())); // 기부 종료 기간
			map.put("donateStatus", dateConverter.getDateStatus(mstDonateVo.getMst_d_date_from(), mstDonateVo.getMst_d_date_to())); // 기부 상태
			map.put("thumnailImg", this.donateMapper.selectThumnailByDNo(mstDonateVo.getMst_d_no())); // 기부 썸네일
			map.put("totalPoint", totalPoint);
			map.put("percent", calcPercent(totalPoint, mstDonateVo.getMst_d_goal()));
			
			donateList.add(map);
		}
		
		System.out.println("[System] getDonateList");
		
		return donateList;
	}
	
	// 기부 정보 조회
	public HashMap<String, Object> getDonate(int mst_d_no) {
		CommonsDateConverter dateConverter = new CommonsDateConverter();
		HashMap<String, Object> donateData = new HashMap<String, Object>(); // 기부정보
		MSTDonateVo mstDonateVo = this.donateMapper.selectDonateByNo(mst_d_no); // 기부 데이터
		String date_from = dateConverter.getDate(mstDonateVo.getMst_d_date_from());
		String date_to = dateConverter.getDate(mstDonateVo.getMst_d_date_to());
		int totalPoint = donatePointSum(mstDonateVo.getMst_d_no());
		
		donateData.put("mstDCategoryVo", this.donateMapper.selectCategoryByNo(mstDonateVo.getMst_dc_no())); // 기부카테고리
		donateData.put("mstDonateVo", mstDonateVo); // 기부정보 담기
		donateData.put("date_from", date_from);
		donateData.put("date_to", date_to);
		donateData.put("donateStatus", dateConverter.getDateStatus(mstDonateVo.getMst_d_date_from(), mstDonateVo.getMst_d_date_to())); // 기부 상태
		donateData.put("memberVo", this.memberMapper.selectByNo(mstDonateVo.getM_no())); // 개설자 정보 담기
		donateData.put("thumnailImg", this.donateMapper.selectThumnailByDNo(mstDonateVo.getMst_d_no())); // 기부 썸네일
		donateData.put("dDay", dateConverter.getDday(mstDonateVo.getMst_d_date_to()));
		donateData.put("totalPoint", totalPoint);
		donateData.put("percent", calcPercent(totalPoint, mstDonateVo.getMst_d_goal()));
		
		System.out.println("[System] getDonate");
		
		return donateData;
	}
	
	// 모금 참여자 조회
	public ArrayList<HashMap<String, Object>> getDonator(int mst_d_no) {
		ArrayList<HashMap<String, Object>> donatorList = new ArrayList<HashMap<String,Object>>();
		ArrayList<DonateDVo> tempList = this.donateMapper.selectPointByMSTDNo(mst_d_no);
		
		for(DonateDVo vo: tempList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("donateVo", vo);
			map.put("donatorData", this.memberMapper.selectByNo(vo.getM_no()));
			
			donatorList.add(map);
		}
		
		System.out.println("[System] getDonator");
		
		return donatorList;
	}
	
	// 기부
	public void donate(DonateDVo donateVo) {
		
		this.donateMapper.insertDonateD(donateVo);
	}
	
	// 상태에 따른 기부리스트 재설정
	private ArrayList<MSTDonateVo> rebuildDonateList(ArrayList<MSTDonateVo> list, int targetStatus) {
		ArrayList<MSTDonateVo> resultList = new ArrayList<MSTDonateVo>();
		CommonsDateConverter dateConverter = new CommonsDateConverter();
		
		if(targetStatus == 0) { // 검색조건이 없을 시
			resultList = list;
			
		}else { // 검색조건이 있을 시
		
			for(MSTDonateVo vo: list) {
				int status = dateConverter.getDateStatus(vo.getMst_d_date_from(), vo.getMst_d_date_to());

				if(status == targetStatus) {
					resultList.add(vo);
				}
			}
		}
		
		return resultList;
	}
	
	// 퍼센트 조회
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
	
	// 기부 합계 조회
	private int donatePointSum(int mstDNo){
		ArrayList<DonateDVo> donateList = donateMapper.selectPointByMSTDNo(mstDNo);
		int result = 0;
		
		if(donateList != null) {
			
			for(DonateDVo donateVo: donateList) {
				
				result += donateVo.getDd_donate_point();
			}
		}
		
		return result;
	}
}