package com.give.donagi.funding.controller;

import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.give.donagi.funding.service.UserFundingServiceImpl;
import com.give.donagi.member.service.UserMemberServiceImpl;
import com.give.donagi.reviewFunding.controller.UserReviewFundingController;
import com.give.donagi.reviewFunding.service.UserReviewFundingServicempl;
import com.give.donagi.util.service.CommonsUtilServiceImpl;
import com.give.donagi.vo.*;

@Controller
@RequestMapping("/funding/*")
public class UserFundingController {
	@Autowired
	private CommonsUtilServiceImpl utilService;
	@Autowired
	private UserFundingServiceImpl fundingService;
	@Autowired
	private UserMemberServiceImpl memberService;
	
	@Autowired
	private UserReviewFundingServicempl UserReviewFundingService;
	
	@RequestMapping("list_page.do")
	public String listPage(Model model, HttpSession session, @RequestParam(value="page_num", defaultValue="1") int page_num, 
			@RequestParam(value="search_status", defaultValue="2") int status, @RequestParam(value="categoryNo", defaultValue="0") int categoryNo) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		this.utilService.getBanner(model);
		
		if(loginUser != null) {
			ArrayList<HashMap<String, Object>> fundingList = null; // 펀딩리스트
			HashMap<String, Object> searchMap = new HashMap<String, Object>(); // 검색 조건
			int currentPage = page_num; // 현재 페이지
			int beginPage = ((currentPage - 1) / 5) * 5 + 1; // 시작 페이지
			int endPage = ((currentPage - 1) / 5 + 1) * 5; // 끝 페이지
			int pageCount = 0; // 최대 페이지
			
			path = "user/funding/list_page";
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			
			
			// 검색 조건 설정
			searchMap.put("currentPage", currentPage); // 페이지 조건
			searchMap.put("status", status);
			
			if(categoryNo > 0) {
				searchMap.put("categoryNo", categoryNo);
			}
			
			fundingList = this.fundingService.getFundingList(searchMap); // 검색조건의 펀딩리스트 출력
			
			// 페이지 처리
			pageCount = this.fundingService.pageCount(searchMap);
			
			if(endPage > pageCount) {
				endPage = pageCount;
			}

			// 페이지에 펀딩정보 전송
			model.addAttribute("fundingList", fundingList);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("beginPage", beginPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("categoryNo", categoryNo);
			model.addAttribute("searchStatus", status);
			model.addAttribute("categoryList", this.fundingService.getCategoryList()); // 카테고리 표시
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("create_page.do")
	public String createFunding(Model model, HttpSession session) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			path = "user/funding/create_page";
			
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			this.utilService.setLookupPoint(model, loginUser); // 포인트 조회
			model.addAttribute("categoryList", this.fundingService.getCategoryList()); // 카테고리리스트 정보 전송
			
		}else {
			
			path = "redirect:../member/login_page.do";
			
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("create_process.do")
	public String createProcess(HttpSession session, MSTFundingVo mstFundingParam, MSTFImgVo mstFImgParam, @RequestParam("mst_fo_name") String[] mstFoNames, @RequestParam("mst_fo_price") int[] mstFoPrices) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			path = "redirect:./list_page.do";
			int mstFNo = 0; // 펀딩번호
			
			mstFNo = this.fundingService.createFunding(loginUser, mstFundingParam); // 펀딩정보 입력후 펀딩번호 받기
			this.fundingService.createFundingOption(mstFNo, mstFoNames, mstFoPrices); // 펀딩옵션 정보 입력
			this.fundingService.createFundingImg(mstFNo, mstFImgParam); // 펀딩 썸네일 이미지 설정
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("read_page.do")
	public String readPage(Model model, HttpSession session, int mst_f_no) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			//리뷰펀딩리스트
			ArrayList<HashMap<String, Object>> frfList =
					UserReviewFundingService.getFundingReadList(mst_f_no);
					
			
			
			path = "user/funding/read_page";
			
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			
			model.addAttribute("fundingData", this.fundingService.getFunding(mst_f_no)); // 펀딩 정보
			model.addAttribute("mstFOptionVoList", this.fundingService.getOptionList(mst_f_no)); // 옵션 정보 리스트
			model.addAttribute("frfList", frfList); //리뷰리스트
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("order_page.do")
	public String orderPage(Model model, HttpSession session, int mst_f_no, @RequestParam("mst_fo_no") int[] mstFoNo, 
			@RequestParam("od_quantity") int[] odQuantity) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			HashMap<String, Object> fundingData = null;
			ArrayList<HashMap<String, Object>> orderDetailData = null;
			MPointVo mPointVo = this.utilService.lookupPoint((MemberVo)loginUser.get("memberVo")); // 유저포인트 정보
			int totalPrice = 0; // 총펀딩금액
			int resultPoint = 0; // 결과포인트
			
			path = "user/funding/order_page";
			
			this.utilService.getProfile(model, loginUser); // 프로필 조회
			this.utilService.setLookupPoint(model, loginUser); // 포인트 조회
			
			fundingData = this.fundingService.getFunding(mst_f_no); // 펀딩 정보 조회
			orderDetailData = this.fundingService.resetOption(mstFoNo, odQuantity); // 옵션 정보 조회
			totalPrice = this.fundingService.getTotalPrice(mstFoNo, odQuantity); // 펀딩 총포인트
			
			// 펀딩결과 포인트
			if(mPointVo.getMp_point() >= totalPrice) {
				
				resultPoint = mPointVo.getMp_point() - totalPrice;
			}

			// 전송 정보
			model.addAttribute("fundingData", fundingData);
			model.addAttribute("orderDetailData", orderDetailData);
			model.addAttribute("totalPrice", totalPrice);
			model.addAttribute("resultPoint", resultPoint);
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("order_process.do")
	public String orderProcess(HttpSession session, OrderFVo orderFParam, @RequestParam("total_point") int totalPointParam,  @RequestParam("mst_fo_no") int[] mstFoNoParam, 
			@RequestParam("od_quantity") int[] odQuantityParam) {
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		String path = null;
		
		if(loginUser != null) {
			MemberVo orderUser = new MemberVo(); // 주문자 정보
			MemberVo fundingUser = new MemberVo(); // 펀딩개설자 정보
			HashMap<String, Object> fundingData = null; // 펀딩 데이터
			int ofNo = 0; // 주문번호
			
			ofNo = this.fundingService.insertOrderF(loginUser, orderFParam); // 펀딩 주문 추가
			this.fundingService.insertOrderDetail(ofNo, mstFoNoParam, odQuantityParam); // 주문 상세 추가
			
			orderUser = (MemberVo)loginUser.get("memberVo"); // 주문자 정보 조회
			fundingData = this.fundingService.getFunding(orderFParam.getMst_f_no()); // 펀딩정보 조회
			fundingUser = this.memberService.selectMemberByNo(((MemberVo)fundingData.get("memberVo")).getM_no()); // 개설자 정보 조회
			
			// 포인트 처리
			this.utilService.updateMPoint(orderUser, 3, fundingUser.getM_nick(), -totalPointParam, 
					"펀딩참여: " + ((MSTFundingVo)fundingData.get("mstFundingVo")).getMst_f_title());
			this.utilService.updateMPoint(fundingUser, 3, orderUser.getM_nick(), totalPointParam, 
					"펀딩: " + ((MSTFundingVo)fundingData.get("mstFundingVo")).getMst_f_title());
			
			path = "redirect:./list_page.do";
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("delete_process.do")
	public String deleteProcess(int mst_f_no) {
		String path = null;
		
		path = "redirect:../funding/list_page.do";
		this.fundingService.deleteFunding(mst_f_no);
		
		System.out.println("[Move] " + path);
		
		return path;
	}
}
