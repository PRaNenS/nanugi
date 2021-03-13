package com.give.donagi.main.controller;

import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.give.donagi.funding.service.UserFundingServiceImpl;
import com.give.donagi.main.service.UserMainServiceImpl;
import com.give.donagi.util.service.CommonsUtilServiceImpl;
import com.give.donagi.vo.*;

@Controller
@RequestMapping("/main/*")
public class UserMainController {
	@Autowired
	private CommonsUtilServiceImpl utilService;
	@Autowired
	private UserMainServiceImpl mainService;;
	
	@RequestMapping("main_page.do")
	public String mainPage(Model model, HttpSession session) {
		String path = "user/main_page";
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		ArrayList<HashMap<String, Object>> fundingList = null; // 펀딩리스트
		ArrayList<HashMap<String, Object>> volunteerList = null;
		HashMap<String, Object> searchMap = new HashMap<String, Object>(); // 펀딩 검색 조건
		
		this.utilService.getBanner(model); // 배너 조회
		
		if(loginUser != null) {
			this.utilService.getProfile(model, loginUser);
		}
		
		searchMap.put("status", 2);
		fundingList = this.mainService.getFundingList(searchMap); // 검색조건의 펀딩리스트 출력 
		volunteerList = this.mainService.getVolunteerList(searchMap);
		
		model.addAttribute("volunteerList", volunteerList);
		model.addAttribute("fundingList", fundingList);
		model.addAttribute("fundingStatistics", this.mainService.getFundingStatistics());
		model.addAttribute("volunteerStatistics",this.mainService.getVolunteerStatistics());
		
		model.addAttribute("donateList", this.mainService.getDonateList()); // 기부 리스트
		model.addAttribute("donateStatistics", this.mainService.getDonateStatistics()); // 기부 통계
		
		System.out.println("[Move] " + path);
		
		return path;
	}
}