package com.give.donagi.reviewFunding.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.give.donagi.reviewFunding.service.UserReviewFundingServicempl;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.ReportRFVo;
import com.give.donagi.vo.ReviewFundingVo;

@Controller
@RequestMapping("/review_funding/*")
@ResponseBody
public class RESTfulUserReportController {

	@Autowired
	private UserReviewFundingServicempl userReviewFundingService;
	
	@RequestMapping("report_process.do")
	public void reportProcess(HttpSession session, ReportRFVo reportRFVo, ReviewFundingVo reviewFundingVo) {
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
	
		reportRFVo.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no());
		reportRFVo.setRf_no(reviewFundingVo.getRf_no());
		
		userReviewFundingService.report(reportRFVo);
		
	}
	
}
