package com.give.donagi.volunteer.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.ReportRVVo;
import com.give.donagi.volunteer.service.UserVolunteerServiceImpl;

@Controller
@RequestMapping("/volunteer/*")
@ResponseBody
public class RESTfulUserVolunteerReportController {

	@Autowired
	private UserVolunteerServiceImpl volunteerService;

	@RequestMapping("report_process.do")
	public void reportReviewVolunteer(ReportRVVo param, HttpSession session) {
		
		System.out.println(param);
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");

		int m_no = ((MemberVo)loginUser.get("memberVo")).getM_no();
		param.setM_no(m_no);
		
		
		volunteerService.writeReport(param);
	}
}

