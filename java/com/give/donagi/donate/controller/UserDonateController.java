package com.give.donagi.donate.controller;

import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.give.donagi.vo.*;
import com.give.donagi.donate.service.UserDonateServiceImpl;
import com.give.donagi.util.service.CommonsUtilServiceImpl;

@Controller
@RequestMapping("/donate/*")
public class UserDonateController {
	@Autowired
	private CommonsUtilServiceImpl utilService;
	@Autowired
	private UserDonateServiceImpl donateService;
	
	@RequestMapping("list_page.do")
	public String listPage(Model model, HttpSession session, @RequestParam(value="page_num", defaultValue="1") int page_num, 
			@RequestParam(value="search_status", defaultValue="2") int status, @RequestParam(value="categoryNo", defaultValue="0") int categoryNo) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			ArrayList<HashMap<String, Object>> donateList = null; // 기부리스트
			HashMap<String, Object> searchMap = new HashMap<String, Object>(); // 검색 조건
			int currentPage = page_num; // 현재 페이지
			int beginPage = ((currentPage - 1) / 5) * 5 + 1; // 시작 페이지
			int endPage = ((currentPage - 1) / 5 + 1) * 5; // 끝 페이지
			int pageCount = 0; // 최대 페이지
			
			path = "user/donate/list_page";
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			this.utilService.getBanner(model);

			// 검색 조건 설정
			searchMap.put("currentPage", currentPage); // 페이지 조건
			searchMap.put("status", status);
			
			if(categoryNo > 0) {
				searchMap.put("categoryNo", categoryNo);
			}
			
			donateList = this.donateService.getDonateList(searchMap); // 검색조건의 기부리스트 출력
			
			// 페이지 처리
			pageCount = this.donateService.pageCount(searchMap);
			
			if(endPage > pageCount) {
				endPage = pageCount;
			}

			// 페이지에 기부정보 전송
			model.addAttribute("donateList", donateList);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("beginPage", beginPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("searchStatus", status);
			model.addAttribute("categoryNo", categoryNo);
			
			model.addAttribute("categoryList", this.donateService.getCategoryList()); // 카테고리 표시

			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("create_page.do")
	public String createDonate(Model model, HttpSession session) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			path = "user/donate/create_page";
			
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			
			model.addAttribute("categoryList", this.donateService.getCategoryList()); // 카테고리리스트 정보 전송
			
		}else {
			
			path = "redirect:../member/login_page.do";
			
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("create_process.do")
	public String createProcess(HttpSession session, MSTDonateVo mstDonateParam, MSTDImgVo mstDImgParam) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			path = "redirect:./list_page.do";

			this.donateService.createDonate(loginUser, mstDonateParam, mstDImgParam); // 기부정보 입력후 기부번호 받기
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("read_page.do")
	public String readPage(Model model, HttpSession session, int mst_d_no) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			path = "user/donate/read_page";
			
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 설정
			this.utilService.setLookupPoint(model, loginUser); // 포인트 조회
			
			model.addAttribute("donateData", this.donateService.getDonate(mst_d_no)); // 기부 정보
			model.addAttribute("donatorList", this.donateService.getDonator(mst_d_no)); // 기부자 정보
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("donate_process.do")
	public String donateProcess(DonateDVo donateParam, HttpSession session) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser"); // 기부자
		HashMap<String, Object> donateData = this.donateService.getDonate(donateParam.getMst_d_no()); // 기부 정보
		MemberVo awardee = (MemberVo)donateData.get("memberVo"); // 수여자
		
		if(loginUser != null) {
			
			path = "redirect:../donate/list_page.do";
			
			donateParam.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no());
			this.donateService.donate(donateParam);
			
			this.utilService.updateMPoint((MemberVo)loginUser.get("memberVo"), 2, awardee.getM_nick(), -donateParam.getDd_donate_point(), 
					"기부: "+((MSTDonateVo)donateData.get("mstDonateVo")).getMst_d_title()); // 포인트 처리(기부자)
			this.utilService.updateMPoint(awardee, 2, ((MemberVo)loginUser.get("memberVo")).getM_nick(), donateParam.getDd_donate_point(), 
					"기부: "+((MSTDonateVo)donateData.get("mstDonateVo")).getMst_d_title()); // 포인트 처리(수여자)
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
}

