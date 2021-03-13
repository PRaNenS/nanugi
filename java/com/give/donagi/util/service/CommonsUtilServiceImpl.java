package com.give.donagi.util.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.util.mapper.CommonsUtilSQLMapper;
import com.give.donagi.vo.*;

@Service
public class CommonsUtilServiceImpl {
	@Autowired
	private CommonsUtilSQLMapper utilSQLMapper;
	@Autowired
	private UserMemberSQLMapper memberMapper;
	
	// 배너 이미지 조회
	public void getBanner(Model model) {
		
		model.addAttribute("banner1", this.utilSQLMapper.selectEventBanner1());
		model.addAttribute("banner2", this.utilSQLMapper.selectEventBanner2());
		model.addAttribute("banner3", this.utilSQLMapper.selectEventBanner3());
	}
	
	// 포인트 조회
	public MPointVo lookupPoint(MemberVo memberVo) {
		MPointVo mPointVo = this.utilSQLMapper.lookupPoint(memberVo.getM_no());
		
		System.out.println("[System] lookupPoint Success");
		
		return mPointVo;
	}
	
	// 포인트 내역 조회
		public ArrayList<HashMap<String, Object>> getRecordP(HashMap<String, Object> searchMap) {
			ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>(); 
			ArrayList<MRecordPVo> mRecordPList = this.utilSQLMapper.getMRecordP(searchMap);
					
			for(MRecordPVo vo: mRecordPList) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				CommonsDateConverter dateConverter = new CommonsDateConverter();
				
				map.put("mRecordPVo", vo);
				map.put("recordDateTime", dateConverter.getDateTime(vo.getM_rp_record_date()));
				map.put("type", vo.getM_rp_type());
				
				resultList.add(map);
			}
			
			System.out.println("[System] getRecordP Success");
			
			return resultList;
		}
	
	// 포인트 충전
	public void chargePoint(String chargePointStr, MemberVo memberVo) {
		int chargePoint = Integer.parseInt(chargePointStr);
		
		updateMPoint(memberVo, 1, "은행", chargePoint, "충전");
		
		System.out.println("[System] chargePoint Success");
	}
	
	// 포인트 조회 설정(상단 바)
	public void setLookupPoint(Model model, HashMap<String, Object> loginUser) {
		MPointVo mPointVo = lookupPoint((MemberVo)loginUser.get("memberVo"));
		
		model.addAttribute("mPointVo", mPointVo);
	}
	
	// 포인트 업데이트(from, to, 포인트, 기록내용)
	public void updateMPoint(MemberVo memberVo, int type, String object, int updatePoint, String record) {
		MPointVo mPointVo = new MPointVo(); // 업데이트 정보
		
		// 포인트 충전
		mPointVo.setM_no(memberVo.getM_no()); // 업데이트 대상자 설정
		mPointVo.setMp_point(updatePoint); // 포인트 설정
		this.utilSQLMapper.updatePoint(mPointVo); // 포인트 업데이트
		insertRecord(mPointVo.getM_no(), type, object, updatePoint, record); // 포인트 내역
		
		System.out.println("[System] updateMPoint");
	}
	
	// 프로필 이미지 조회 설정
	public void getProfile(Model model, HashMap<String, Object> loginUser) {
		MemberVo memberVo = this.memberMapper.selectByNo(((MemberVo)loginUser.get("memberVo")).getM_no());
		
		model.addAttribute("profileImgLink", memberVo.getM_profile_img_link());
		
		System.out.println("[System] getProfile");
	}
	
	// 내역 추가(1=충전, 2=기부, 3=펀딩)
	private void insertRecord(int mNo, int type, String object, int point, String record) {
		MRecordPVo mRecordPVo = new MRecordPVo(); // 포인트내역
		
		mRecordPVo.setM_rp_no(this.utilSQLMapper.createMRecordPKey());
		mRecordPVo.setM_no(mNo);
		mRecordPVo.setM_rp_type(type);
		mRecordPVo.setM_rp_object(object);
		mRecordPVo.setM_rp_point(point);
		mRecordPVo.setM_rp_record(record);
		
		this.utilSQLMapper.insertRecord(mRecordPVo);
		
		System.out.println("[System] insertPointRecord");
	}
}
