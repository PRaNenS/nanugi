package com.give.donagi.mypage.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.give.donagi.funding.service.UserFundingServiceImpl;
import com.give.donagi.mypage.service.UserMypageServiceImpl;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.util.service.CommonsUtilServiceImpl;
import com.give.donagi.vo.MPointVo;
import com.give.donagi.vo.MSTFOptionVo;
import com.give.donagi.vo.MSTFundingVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.OrderFVo;
import com.give.donagi.volunteer.service.UserVolunteerServiceImpl;

@Controller
@RequestMapping("/mypage/*")
public class UserMypageController {
	@Autowired
	private CommonsUtilServiceImpl utilService;
	@Autowired
	private UserMypageServiceImpl mypageService;
	@Autowired
	private UserFundingServiceImpl fundingService;
	@Autowired
	private UserVolunteerServiceImpl volunteerService;
	
	@RequestMapping("point_page.do")
	public String pointPage(Model model, HttpSession session, @RequestParam(value="search_date_from", defaultValue="null") String date_from, @RequestParam(value="search_date_to", defaultValue="null") String date_to) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			HashMap<String, Object> searchMap = new HashMap<String, Object>();
			
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			this.utilService.setLookupPoint(model, loginUser); // 포인트 정보 설정
			
			searchMap.put("m_no", ((MemberVo)loginUser.get("memberVo")).getM_no());
			
			if(!date_from.equals("null") && !date_to.equals("null")) {
				Calendar cal = Calendar.getInstance();
				CommonsDateConverter dateConverter = new CommonsDateConverter();
				String dateTo = null;
				System.out.println("test");
				cal.setTime(dateConverter.parseStringToDate(date_to));
				cal.add(Calendar.DATE, +1);
				dateTo = dateConverter.getDate(cal.getTime());
				
				searchMap.put("date_from", date_from);
				searchMap.put("date_to", dateTo);
			}
			
			model.addAttribute("recordList", this.utilService.getRecordP(searchMap));
			
			path = "user/mypage/point_page";
			
		}else {
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	// 포인트 충전
	@RequestMapping("charge_process.do")
	public String chargePoint(String charge_point, HttpSession session) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			path = "redirect:../mypage/point_page.do";
			this.utilService.chargePoint(charge_point, (MemberVo)loginUser.get("memberVo"));
		
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("update_user_page.do")
	public String updateUserPage(Model model, HttpSession session) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			path = "user/mypage/update_user_page";
			
			this.utilService.setLookupPoint(model, loginUser);
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			model.addAttribute("memberData", (MemberVo)loginUser.get("memberVo"));
			model.addAttribute("ckRequest", this.mypageService.existRequestCom(loginUser));
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	
	
	@RequestMapping("order_page.do")
	public String orderPage(Model model, HttpSession session) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			ArrayList<HashMap<String, Object>> orderList = this.fundingService.getOrderList((MemberVo)loginUser.get("memberVo")); // 주문리스트 조회
			
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			this.utilService.setLookupPoint(model, loginUser); // 상단 포인트 조회
			path = "user/mypage/order_page";
			
			model.addAttribute("orderList", orderList);
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("read_order_page.do")
	public String readOrderPage(Model model, HttpSession session, int of_no) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			HashMap<String, Object> fundingData = null;
			ArrayList<HashMap<String, Object>> orderDetailData = null;
			HashMap<String, Object> orderData = this.fundingService.getOrder(of_no);
			MPointVo mPointVo = this.utilService.lookupPoint((MemberVo)loginUser.get("memberVo")); // 유저포인트 정보
			int totalPrice = 0; // 총펀딩금액
			int resultPoint = 0; // 결과포인트
			
			path = "user/mypage/read_order_page";
			
			this.utilService.getProfile(model, loginUser); // 프로필 조회
			this.utilService.setLookupPoint(model, loginUser); // 포인트 조회
			
			fundingData = this.fundingService.getFunding(((OrderFVo)orderData.get("orderVo")).getMst_f_no()); // 주문 정보 조회
			orderDetailData = this.fundingService.getOrderDetailList(of_no); // 주문 상세 정보 조회
			totalPrice = this.fundingService.getTotalPriceOfOrderDetails(orderDetailData); // 주문 총포인트
			
			// 펀딩결과 포인트
			if(mPointVo.getMp_point() >= totalPrice) {
				
				resultPoint = mPointVo.getMp_point() - totalPrice;
			}

			// 전송 정보
			model.addAttribute("fundingData", fundingData);
			model.addAttribute("orderData", orderData);
			model.addAttribute("orderDetailData", orderDetailData);
			model.addAttribute("totalPrice", totalPrice);
			model.addAttribute("resultPoint", resultPoint);
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("funding_page.do")
	public String fundingPage(Model model, HttpSession session) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			ArrayList<HashMap<String, Object>> fundingList = this.fundingService.getFundingListByFounder((MemberVo)loginUser.get("memberVo"));
			
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			this.utilService.setLookupPoint(model, loginUser); // 상단 포인트 조회
			path = "user/mypage/funding_page";
			
			model.addAttribute("fundingList", fundingList);
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("read_funding_page.do")
	public String readFundingPage(Model model, HttpSession session, int mst_f_no) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			HashMap<String, Object> funding = this.fundingService.getFunding(mst_f_no); // 펀딩정보
			ArrayList<MSTFOptionVo> options = this.fundingService.getOptionList(mst_f_no); // 옵션정보
			ArrayList<HashMap<String, Object>> orders = this.fundingService.getOrderListByFundingNo(mst_f_no); // 주문정보
			int fundingPrice = 0;
			int percent = 0;
			
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			this.utilService.setLookupPoint(model, loginUser); // 상단 포인트 조회
			path = "user/mypage/read_funding_page";
			
			for(HashMap<String, Object> order: orders) {
				int totalPrice = Integer.parseInt((order.get("totalPrice")).toString());
				
				fundingPrice += totalPrice;
			}
			
			percent = this.fundingService.getPercent(fundingPrice, ((MSTFundingVo)funding.get("mstFundingVo")).getMst_f_goal());
			
			model.addAttribute("funding", funding);
			model.addAttribute("options", options);
			model.addAttribute("orders", orders);
			model.addAttribute("percent", percent);
			model.addAttribute("fundingPrice", fundingPrice);
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("join_page.do")
	public String joinPage(Model model, HttpSession session) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			ArrayList<HashMap<String, Object>> joinList = this.volunteerService.getJVList((MemberVo)loginUser.get("memberVo"));
			this.utilService.getProfile(model, loginUser);
			this.utilService.setLookupPoint(model, loginUser); // 상단 포인트 조회
			path = "user/mypage/join_page";
			
			model.addAttribute("joinList",joinList);
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("volunteer_page.do")
	public String volunteerPage(Model model, HttpSession session) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			ArrayList<HashMap<String, Object>> volunteerList = this.volunteerService.OpenedVolunteer((MemberVo)loginUser.get("memberVo"));
			this.utilService.getProfile(model, loginUser);
			this.utilService.setLookupPoint(model, loginUser); // 상단 포인트 조회
			path = "user/mypage/volunteer_page";
			
			model.addAttribute("volunteerList",volunteerList);
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("delete_joinv.do")
	public String deleteJoinDetail(Model model, HttpSession session, int jv_no)
	{
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) 
		{			
			this.utilService.getProfile(model, loginUser);
			this.utilService.setLookupPoint(model, loginUser); // 상단 포인트 조회
			
			this.volunteerService.deleteJoinv(jv_no);
			path = "redirect:../mypage/join_page.do";
		}
		else 
		{
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("read_volunteer_page.do")
	public String readJoinVList(Model model, HttpSession session, int mst_v_no) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			ArrayList<HashMap<String, Object>> detailList = this.volunteerService.detailList(mst_v_no);
			
			
			this.utilService.getProfile(model, loginUser);
			this.utilService.setLookupPoint(model, loginUser); // 상단 포인트 조회
			path = "user/mypage/read_volunteer_page";
			

			model.addAttribute("detailList",detailList);
			model.addAttribute("volunteerData", this.volunteerService.getVolunteer(mst_v_no));
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("update_status_pass.do")
	public String updateStautsP(Model model, int jv_no, HttpSession session)
	{
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) 
		{
			
			this.utilService.getProfile(model, loginUser);
			this.utilService.setLookupPoint(model, loginUser); // 상단 포인트 조회
			this.volunteerService.updateStatusP(jv_no);
			path = "redirect:../mypage/volunteer_page.do";
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("update_status_fail.do")
	public String updateStautsF(Model model, int jv_no, HttpSession session)
	{
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) 
		{
			
			this.utilService.getProfile(model, loginUser);
			this.utilService.setLookupPoint(model, loginUser); // 상단 포인트 조회
			this.volunteerService.updateStatusF(jv_no);
			path = "redirect:../mypage/volunteer_page.do";
			
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("delete_voluteer.do")
	public String deleteVolunteer(HttpSession session, int mst_v_no)
	{
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser != null)
		{
			this.volunteerService.deleteVolunteer(mst_v_no);
			
			path = "redirect:./volunteer_page.do";
			
		}	
		else
		{
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	@RequestMapping("delete_order_process.do")
	public String deleteOrderProcess(HttpSession session, int of_no) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			HashMap<String, Object> orderData = this.fundingService.getOrder(of_no);
			ArrayList<HashMap<String, Object>> orderDetailData = this.fundingService.getOrderDetailList(of_no); // 주문 상세 정보 조회
			HashMap<String, Object> funding = this.fundingService.getFunding(((OrderFVo)orderData.get("orderVo")).getMst_f_no()); // 펀딩 정보
			int totalPrice = this.fundingService.getTotalPriceOfOrderDetails(orderDetailData); // 주문 총포인트
			
			path = "redirect:../mypage/order_page.do";
			
			this.mypageService.deleteOrder(of_no);
			this.utilService.updateMPoint((MemberVo)loginUser.get("memberVo"), 3, ((MemberVo)funding.get("memberVo")).getM_nick(),
					totalPrice, "주문취소: " + ((MSTFundingVo)funding.get("mstFundingVo")).getMst_f_title());

			this.utilService.updateMPoint((MemberVo)funding.get("memberVo"), 3, ((MemberVo)loginUser.get("memberVo")).getM_nick(),
					-totalPrice, "주문취소: " + ((MSTFundingVo)funding.get("mstFundingVo")).getMst_f_title());
			
		}else {
		
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("update_order_process.do")
	public String updateOrderProcess(OrderFVo orderFvo) {
		String path = null;
		
		path = "redirect:../mypage/read_order_page.do?of_no=" + orderFvo.getOf_no();
		this.mypageService.updateOrder(orderFvo);
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("update_status_process.do")
	public String updateStatusProcess(int mst_f_no, int mst_fs_no) {
		String path = null;
		
		path = "redirect:../mypage/read_funding_page.do?mst_f_no=" + mst_f_no;
		
		this.mypageService.updateFundingStatus(mst_f_no, mst_fs_no);
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("update_delivery_process.do")
	public String updateDeliveryProcess(int of_no, int mst_f_no) {
		String path = null;
		
		path = "redirect:../mypage/read_order_page.do?of_no=" + of_no;
		this.mypageService.updateDeliveryStatus(of_no, mst_f_no);
		
		System.out.println("[Move] " + path);
		
		return path;
	}
}
