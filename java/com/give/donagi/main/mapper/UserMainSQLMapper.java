package com.give.donagi.main.mapper;

import java.util.*;
import com.give.donagi.vo.*;

public interface UserMainSQLMapper {

	public ArrayList<MSTFundingVo> selectAllFundingList(); // 모든 펀딩 정보 조회
	public ArrayList<MSTFundingVo> selectFundingList(HashMap<String, Object> searchMap); // 펀딩 리스트 받기
	public MSTFImgVo selectThumnailByFNo(int mst_f_no); // 펀딩 썸네일 받기
	public ArrayList<OrderFVo> selectOrderListByFundingNo(int mst_f_no); // 펀딩번호로 주문리스트 조회
	public ArrayList<ODetailVo> selectOrderDetailsByOrderNo(int of_no); // 주문 상세 조회
	public MSTFOptionVo selectOptionByNo(int mst_fo_no); // 펀딩옵션정보 받기
	public int selectFundingParticipantCount(); // 펀딩참가자수 조회
	
	// 봉사
	
	public ArrayList<MSTVolunteerVo> selectVolunteerList(HashMap<String, Object> searchMap);
	public MSTVImgVo selectVImgByVNo(int mst_v_no);
	public int JoinCount(int mst_v_no);
	public int selectVolunteerParticipantCount();
	
	// 기부
	public ArrayList<MSTDonateVo> selectDonateList(); // 기부 리스트 조회
	public MSTDImgVo selectThumnailByDNo(int mst_d_no); // 기부 썸네일 조회
	public int selectDonateParticipantCount(); // 기부 참가자수 조회
	public ArrayList<DonateDVo> selectAllDonate(); // 모든 기부 정보 조회
	public ArrayList<DonateDVo> selectPointByMSTDNo(int mst_d_no); // 매 기부 포인트 조회
}