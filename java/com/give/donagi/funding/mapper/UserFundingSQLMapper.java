package com.give.donagi.funding.mapper;

import java.util.*;
import com.give.donagi.vo.*;

public interface UserFundingSQLMapper {

	public int createMSTFNo(); // 펀딩 키 생성
	public ArrayList<MSTFCategoryVo> selectMSTFCategory(); // 카테고리 정보 받기
	public MSTFCategoryVo selectCategoryByNo(int mst_fc_no); // 카테고리 받기
	public void insertMSTFunding(MSTFundingVo mstFundingVo); // 펀딩 정보 입력
	public int createMSTFoNo(); // 펀딩 옵션 키 생성
	public void insertMSTFOption(MSTFOptionVo mstFOptionVo); // 펀딩 옵션 정보 입력
	public int createMSTFiNo(); // 펀딩 이미지 번호 생성
	public void insertMSTFImg(MSTFImgVo mstFImgVo); // 펀딩 썸네일 이미지 설정
	public MSTFStatusVo selectFundingStatusByNo(int mst_fs_no); // 펀딩 상태 조회
	public int searchForPageCount(HashMap<String, Object> searchMap); // 페이지수 출력을 위한 리스트 받기
	public ArrayList<MSTFundingVo> selectFundingList(HashMap<String, Object> searchMap); // 펀딩 리스트 받기
	public MSTFundingVo selectFundingByNo(int mst_f_no); // 펀딩 정보 받기
	public ArrayList<MSTFOptionVo> selectOptionByFundingNo(int mst_f_no); // 펀딩하나의 옵션 받기
	public MSTFOptionVo selectOptionByNo(int mst_fo_no); // 펀딩옵션정보 받기
	public MSTFImgVo selectThumnailByFNo(int mst_f_no); // 펀딩 썸네일 받기
	public int createOrderNo(); // 주문번호 생성
	public void insertOrder(OrderFVo orderFVo); // 주문정보 입력
	public int createOrderDetailNo(); // 주분상세번호 생성
	public void insertOrderDetail(ODetailVo oDetailVo); // 주문상세정보 입력
	public ArrayList<OrderFVo> selectOrderList(int m_no); // 주문리스트 조회
	public MSTOStatusVo selectOrderStatus(int mst_os_no); // 주문 상태 조회
	public ArrayList<ODetailVo> selectOrderDetailsByOrderNo(int of_no); // 주문 상세 조회
	public ArrayList<MSTFundingVo> selectFundingListByMemberNo(int m_no); // 개설자번호로 펀딩리스트 조회
	public ArrayList<OrderFVo> selectOrderListByFundingNo(int mst_f_no); // 펀딩번호로 주문리스트 조회
	public ArrayList<MSTFundingVo> selectAllFunding(); // 모든펀딩 조회
	public void updateFundingStatus(HashMap<String, Object> map); // 펀딩 상태 업데이트
	public ArrayList<OrderFVo> selectAllOrder(); // 모든주문 조회
	public OrderFVo selectOrderByNo(int of_no); // 주문 조회
	public void updateOrderStatus(HashMap<String, Object> map); // 주문 상태 업데이트
	public ArrayList<DeliveryOFVo> selectAllDelivery(); // 배송 조회
	public DeliveryOFVo selectDeliveryByOrderNo(int of_no); // 해당 주문번호의 배송 조회
	public void updateDeliveryStatus(HashMap<String, Object> map); // 배송 상태 업데이트
	public void deleteFundingByNo(int mst_f_no); // 펀딩 삭제
	public void deleteOptionByFundingNo(int mst_f_no); // 옵션 삭제
}
