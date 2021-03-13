package com.give.donagi.funding.service;

import java.util.*;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.give.donagi.funding.mapper.UserFundingSQLMapper;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.reviewFunding.mapper.UserReviewFundingSQLMapper;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.vo.*;

@Service
public class UserFundingServiceImpl {
	@Autowired
	private UserFundingSQLMapper fundingMapper;
	@Autowired
	private UserMemberSQLMapper memberMapper;
	@Autowired
	private UserReviewFundingSQLMapper reviewFundingMapper;
	// 카테고리 리스트 정보 얻기
	public ArrayList<MSTFCategoryVo> getCategoryList() {
		
		System.out.println("[System] getCategoryList");
		
		return this.fundingMapper.selectMSTFCategory();
	}
	
	// 펀딩 개설
	public int createFunding(HashMap<String, Object> loginUser, MSTFundingVo mstFundingParam) {
		
		mstFundingParam.setMst_f_no(this.fundingMapper.createMSTFNo()); // 펀딩번호 생성
		mstFundingParam.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no()); // 펀딩 유저 설정
		
		this.fundingMapper.insertMSTFunding(mstFundingParam); // 펀딩정보 입력

		System.out.println("[System] createFunding");
		
		return mstFundingParam.getMst_f_no();
	}
	
	// 펀딩 개설 옵션 추가
	public void createFundingOption(int mstFNo, String[] mstFoNames, int[] mstFoPrices) {
		
		// 펀딩옵션 추가 처리
		for(int i = 0; i < mstFoNames.length; i++) {
			MSTFOptionVo mstFOptionVo = new MSTFOptionVo(this.fundingMapper.createMSTFoNo(), mstFNo, mstFoNames[i], mstFoPrices[i]); // 펀딩옵션정보 재설정

			this.fundingMapper.insertMSTFOption(mstFOptionVo); // 펀딩옵션 정보 입력
		}
				
		System.out.println("[System] createFundingOption");
	}
	
	// 펀딩 썸네일 이미지 추가
	public void createFundingImg(int mstFNo, MSTFImgVo mstFImgVo) {
		
		mstFImgVo.setMst_fi_no(this.fundingMapper.createMSTFiNo()); // 펀딩 썸네일 이미지 키 설정
		mstFImgVo.setMst_f_no(mstFNo); // 펀딩 썸네일 이미지 해당 펀딩 번호 설정
		
		this.fundingMapper.insertMSTFImg(mstFImgVo);
		
		System.out.println("[System] createFundingImg");
	}
	
	// 페이지수 받기
	public int pageCount(HashMap<String, Object> searchMap) {
		int tempPage = this.fundingMapper.searchForPageCount(searchMap); // 임시 페이지 수
		int pageCount = 0; // 페이지 수
		
		if(tempPage > 0) { // 검색 조건에 맞는 결과가 있을 경우
			pageCount = (int)tempPage;
			
		}else { // 검색 조건에 맞는 결과가 없을 경우
			pageCount = 1;
		}
		
		return pageCount;
	}
	
	// 펀딩 리스트 받기
		public ArrayList<HashMap<String, Object>> getFundingList(HashMap<String, Object> searchMap) {
		ArrayList<HashMap<String, Object>> fundingList = new ArrayList<HashMap<String,Object>>(); // 펀딩 리스트
		ArrayList<MSTFundingVo> tempFundingVoList = this.fundingMapper.selectFundingList(searchMap); // 임시펀딩 정보 리스트
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
				map.put("thumnailImg", this.fundingMapper.selectThumnailByFNo(mstFundingVo.getMst_f_no())); // 펀딩 썸네일
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
		
	
	
	// 펀딩 정보 받기
	public HashMap<String, Object> getFunding(int mst_f_no) {
		CommonsDateConverter dateConverter = new CommonsDateConverter();
		HashMap<String, Object> fundingData = new HashMap<String, Object>(); // 펀딩정보
		MSTFundingVo mstFundingVo = this.fundingMapper.selectFundingByNo(mst_f_no); // 펀딩 데이터
		ArrayList<OrderFVo> orders = this.fundingMapper.selectOrderListByFundingNo(mstFundingVo.getMst_f_no()); // 주문리스트
		String date_from = dateConverter.getDate(mstFundingVo.getMst_f_date_from());
		String date_to = dateConverter.getDate(mstFundingVo.getMst_f_date_to());
		String end_date = dateConverter.getDate(mstFundingVo.getMst_f_date_end());
		int totalPrice = 0;
		int percent = 0;
		
		// HTML Escape
        String fundingContent = StringEscapeUtils.escapeHtml4(mstFundingVo.getMst_f_content());
        mstFundingVo.setMst_f_content(fundingContent.replaceAll("\n", "<br>"));
		
		// 총 펀딩액 조회
		for(OrderFVo order: orders) {
			ArrayList<ODetailVo> details = this.fundingMapper.selectOrderDetailsByOrderNo(order.getOf_no());
			
			
			for(ODetailVo detail: details) {
				MSTFOptionVo option = this.fundingMapper.selectOptionByNo(detail.getMst_fo_no());
				
				totalPrice += (detail.getOd_quantity() * option.getMst_fo_price());
			}
		}
		
		percent = calcPercent(totalPrice, mstFundingVo.getMst_f_goal());
		
		fundingData.put("mstFCategoryVo", this.fundingMapper.selectCategoryByNo(mstFundingVo.getMst_fc_no())); // 펀딩카테고리
		fundingData.put("mstFundingVo", mstFundingVo); // 펀딩정보 담기
		fundingData.put("date_from", date_from);
		fundingData.put("date_to", date_to);
		fundingData.put("end_date", end_date);
		fundingData.put("dDay", dateConverter.getDday(mstFundingVo.getMst_f_date_to()));
		fundingData.put("totalPrice", totalPrice);
		fundingData.put("percent", percent);
		fundingData.put("fundingDateStatus", dateConverter.getDateStatus(mstFundingVo.getMst_f_date_from(), mstFundingVo.getMst_f_date_to())); // 펀딩 날짜 상태
		fundingData.put("fundingStatus",this.fundingMapper.selectFundingStatusByNo(mstFundingVo.getMst_fs_no())); // 펀딩상태
		fundingData.put("memberVo", this.memberMapper.selectByNo(mstFundingVo.getM_no())); // 개설자 정보 담기
		fundingData.put("thumnailImg", this.fundingMapper.selectThumnailByFNo(mstFundingVo.getMst_f_no())); // 썸네일 이미지
		
		System.out.println("[System] getFunding");
		
		return fundingData;
	}
	
	// 해당 펀딩 옵션 받기
	public ArrayList<MSTFOptionVo> getOptionList(int mst_f_no) {
		
		return this.fundingMapper.selectOptionByFundingNo(mst_f_no);
	}
	
	// 펀딩 옵션 재설정
	public ArrayList<HashMap<String, Object>> resetOption(int[] mstFoNo, int[] odQuantity) {
		ArrayList<HashMap<String, Object>> orderDetailList = new ArrayList<HashMap<String,Object>>();
		
		for(int i = 0; i < mstFoNo.length; i++) {
			HashMap<String, Object> optionMap = new HashMap<String, Object>(); // 주문옵션정보
			ODetailVo oDetailVo = new ODetailVo(); // 주문옵션상세정보
			MSTFOptionVo mstFOptionVo = this.fundingMapper.selectOptionByNo(mstFoNo[i]); // 펀딩옵션정보
			int sumPrice = 0; // 각옵션별 총가격
			
			oDetailVo.setMst_fo_no(mstFoNo[i]);
			oDetailVo.setOd_quantity(odQuantity[i]);
			sumPrice = odQuantity[i] * mstFOptionVo.getMst_fo_price();
			
			optionMap.put("oDetailVo", oDetailVo);
			optionMap.put("mstFOptionVo", mstFOptionVo);
			optionMap.put("sumPrice", sumPrice);
			
			orderDetailList.add(optionMap);
		}
		
		return orderDetailList;
	}
	
	// 펀딩총금액 받기
	public int getTotalPrice(int[] mstFoNo, int[] odQuantity) {
		int totalPrice = 0;
		
		for(int i = 0; i < mstFoNo.length; i++) {
			MSTFOptionVo mstFOptionVo = this.fundingMapper.selectOptionByNo(mstFoNo[i]); // 펀딩옵션정보
			int sumPrice = 0; // 각옵션별 총가격
			
			sumPrice = odQuantity[i] * mstFOptionVo.getMst_fo_price();
			totalPrice += sumPrice;
		}
		
		return totalPrice;
	}
	
	// 펀딩총금액 조회
	public int getTotalPriceOfOrderDetails(ArrayList<HashMap<String, Object>> oDetailDatas) {
		int totalPrice = 0;
		
		for(HashMap<String, Object> map: oDetailDatas) {
			ODetailVo oDetail = (ODetailVo)map.get("oDetailVo");
			MSTFOptionVo mstFOptionVo = (MSTFOptionVo)map.get("mstFOptionVo"); // 펀딩옵션정보
			int sumPrice = 0; // 각옵션별 총가격
			
			sumPrice = oDetail.getOd_quantity() * mstFOptionVo.getMst_fo_price();
			totalPrice += sumPrice;
		}
		
		return totalPrice;
	}
	
	// 총 펀딩가격 조회
	private int getTotalPoint(int mst_f_no) {
		ArrayList<OrderFVo> orders = this.fundingMapper.selectOrderListByFundingNo(mst_f_no);
		int total = 0;
		
		for(OrderFVo order: orders) {
			ArrayList<ODetailVo> details = this.fundingMapper.selectOrderDetailsByOrderNo(order.getOf_no());
			
			for(ODetailVo detail: details) {
				MSTFOptionVo option = this.fundingMapper.selectOptionByNo(detail.getMst_fo_no());
				
				total += option.getMst_fo_price() * detail.getOd_quantity();
			}
		}
		
		return total;
	}
	
	// 주문정보 삽입
	public int insertOrderF(HashMap<String, Object> loginUser, OrderFVo orderFParam) {
		
		orderFParam.setOf_no(this.fundingMapper.createOrderNo()); // 주문번호 생성
		orderFParam.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no()); // 주문자 정보 설정
		orderFParam.setMst_os_no(0); // 펀딩중 상태 설정
		
		this.fundingMapper.insertOrder(orderFParam); // 주문
		
		System.out.println("[System] insertOrderF");
		
		return orderFParam.getOf_no();
	}
	
	// 주문 상세정보 삽입
	public void insertOrderDetail(int ofNo, int[] mstFoNo, int[] odQuantity) {
		
		// 펀딩 상세주문 삽입 처리
		for (int i = 0; i < mstFoNo.length; i++) {
			ODetailVo oDetailVo = new ODetailVo();
			
			oDetailVo.setOd_no(this.fundingMapper.createOrderDetailNo()); // 펀딩주문상세번호 생성
			oDetailVo.setOf_no(ofNo); // 주문번호 설정
			oDetailVo.setMst_fo_no(mstFoNo[i]); // 펀딩옵션 번호 설정
			oDetailVo.setOd_quantity(odQuantity[i]); // 해당 옵션 주문 수량 설정
			
			this.fundingMapper.insertOrderDetail(oDetailVo); // 상세주문
		}
		
		System.out.println("[System] insertOrderDetail");
	}
	
	// 주문리스트 조회(주문한유저)
	public ArrayList<HashMap<String, Object>> getOrderList(MemberVo memberVo) {
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		ArrayList<OrderFVo> orderList = this.fundingMapper.selectOrderList(memberVo.getM_no()); // 유저의 주문리스트 조회
		
		for(OrderFVo order: orderList) {
			HashMap<String, Object> map = new HashMap<String, Object>(); // 주문리스트 매핑
			ArrayList<ODetailVo> orderDetails= this.fundingMapper.selectOrderDetailsByOrderNo(order.getOf_no()); // 주문 상세 조회
			HashMap<String, Object> funding = getFunding(order.getMst_f_no()); // 펀딩정보 조회
			ArrayList<MSTFOptionVo> options = getOptionList(((MSTFundingVo)funding.get("mstFundingVo")).getMst_f_no()); // 옵션정보 조회
			MSTOStatusVo status = this.fundingMapper.selectOrderStatus(order.getMst_os_no()); // 주문 상태 조회
			CommonsDateConverter dateConverter = new CommonsDateConverter();
			int totalPrice = sumPriceByOptionDetail(orderDetails);
			
			map.put("orderVo", order);
			map.put("totalPrice", totalPrice);
			map.put("funding", funding);
			map.put("options", options);
			map.put("status", status);
			map.put("reviewCount", this.reviewFundingMapper.countOFNo(order.getOf_no()));
			map.put("orderDate", dateConverter.getDate(order.getOf_order_date()));
			
			resultList.add(map);
		}
		
		return resultList;
	}
	
	// 개설한 펀딩리스트 조회
	public ArrayList<HashMap<String, Object>> getFundingListByFounder(MemberVo memberVo) {
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		ArrayList<MSTFundingVo> fundingList = this.fundingMapper.selectFundingListByMemberNo(memberVo.getM_no());
		
		
		for(MSTFundingVo funding: fundingList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			CommonsDateConverter dateConverter = new CommonsDateConverter();
			ArrayList<OrderFVo> orders = this.fundingMapper.selectOrderListByFundingNo(funding.getMst_f_no());
			int totalPrice = 0;
			int percent = 0;
			
			// 총 펀딩액 조회
			for(OrderFVo order: orders) {
				ArrayList<ODetailVo> details = this.fundingMapper.selectOrderDetailsByOrderNo(order.getOf_no());
				
				
				for(ODetailVo detail: details) {
					MSTFOptionVo option = this.fundingMapper.selectOptionByNo(detail.getMst_fo_no());
					
					totalPrice += (detail.getOd_quantity() * option.getMst_fo_price());
				}
			}
			
			percent = calcPercent(totalPrice, funding.getMst_f_goal());
			
			map.put("fundingVo", funding);
			map.put("createDate", dateConverter.getDate(funding.getMst_f_create_date()));
			map.put("dateFrom", dateConverter.getDate(funding.getMst_f_date_from()));
			map.put("dateTo", dateConverter.getDate(funding.getMst_f_date_to()));
			map.put("totalPrice", totalPrice);
			map.put("percent", percent);
			map.put("fundingDateStatus", dateConverter.getDateStatus(funding.getMst_f_date_from(), funding.getMst_f_date_to())); // 펀딩 날짜 상태
			map.put("fundingStatus",this.fundingMapper.selectFundingStatusByNo(funding.getMst_fs_no())); // 펀딩 상태
			map.put("thumnailImg", this.fundingMapper.selectThumnailByFNo(funding.getMst_f_no())); // 썸네일 이미지
			
			resultList.add(map);
		}
		
		System.out.println("[System] getFundingListByFounder");
		
		return resultList;
	}
	
	// 주문리스트 조회(펀딩 개설자)
	public ArrayList<HashMap<String, Object>> getOrderListByFundingNo(int mst_f_no) {
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		ArrayList<OrderFVo> orderList = this.fundingMapper.selectOrderListByFundingNo(mst_f_no);
		
		for (OrderFVo order: orderList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			ArrayList<ODetailVo> detailList = this.fundingMapper.selectOrderDetailsByOrderNo(order.getOf_no());
			ArrayList<HashMap<String, Object>> resultDetails = new ArrayList<HashMap<String,Object>>();
			MSTOStatusVo oStatus = this.fundingMapper.selectOrderStatus(order.getMst_os_no());
			MemberVo orderer = this.memberMapper.selectByNo(order.getM_no());
			int totalPrice = 0;
			
			for(ODetailVo detail: detailList) {
				HashMap<String, Object> resultDetail = new HashMap<String, Object>();
				MSTFOptionVo option = this.fundingMapper.selectOptionByNo(detail.getMst_fo_no());
				
				totalPrice += (detail.getOd_quantity() * option.getMst_fo_price());
				
				resultDetail.put("oDetailVo", detail);
				resultDetail.put("mstFOptionVo", option);
				
				resultDetails.add(resultDetail);
			}
			
			map.put("details", resultDetails);
			map.put("orderer", orderer);
			map.put("orderVo", order);
			map.put("orderStatus", oStatus);
			map.put("totalPrice", totalPrice);
			
			resultList.add(map);
		}
		
		return resultList;
	}
	
	public int getPercent(int x, int y) {
		
		return calcPercent(x, y);
	}
	
	// 주문 정보 조회
	public HashMap<String, Object> getOrder(int of_no) {
		HashMap<String, Object> orderData = new HashMap<String, Object>();
		OrderFVo order = this.fundingMapper.selectOrderByNo(of_no);
		
		orderData.put("orderVo", order);
		orderData.put("orderStatus", this.fundingMapper.selectOrderStatus(order.getMst_os_no()));
		
		return orderData;
	}
	
	// 주문 상세 조회
	public ArrayList<HashMap<String, Object>> getOrderDetailList(int of_no) {
		ArrayList<HashMap<String, Object>> orderDetailList = new ArrayList<HashMap<String,Object>>();
		ArrayList<ODetailVo> oDetailList = this.fundingMapper.selectOrderDetailsByOrderNo(of_no);
		
		for(ODetailVo oDetailVo: oDetailList) {
			HashMap<String, Object> optionMap = new HashMap<String, Object>();
			MSTFOptionVo mstFOptionVo = this.fundingMapper.selectOptionByNo(oDetailVo.getMst_fo_no());
			int sumPrice = 0;
			
			sumPrice = oDetailVo.getOd_quantity() * mstFOptionVo.getMst_fo_price();
		
			optionMap.put("oDetailVo", oDetailVo);
			optionMap.put("mstFOptionVo", mstFOptionVo);
			optionMap.put("sumPrice", sumPrice);
		
			orderDetailList.add(optionMap);
		}
		
		return orderDetailList;
	}
	
	// 펀딩 삭제
	public void deleteFunding(int mst_f_no) {
		
		this.fundingMapper.deleteFundingByNo(mst_f_no);
		this.fundingMapper.deleteOptionByFundingNo(mst_f_no);
		
		System.out.println("[System] deleteFunding");
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
	
	// 주문 옵션 총 금액 조회
	private int sumPriceByOptionDetail(ArrayList<ODetailVo> details) {
		int sumPrice = 0;
		
		for(ODetailVo detail: details) {
			MSTFOptionVo option = this.fundingMapper.selectOptionByNo(detail.getMst_fo_no());
			int sum = option.getMst_fo_price() * detail.getOd_quantity();
			
			sumPrice += sum;
		}
		
		return sumPrice;
	}
	
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
}
