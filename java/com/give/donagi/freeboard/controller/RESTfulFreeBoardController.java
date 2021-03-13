package com.give.donagi.freeboard.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.give.donagi.freeboard.service.FreeBoardServiceImpl;
import com.give.donagi.vo.*;

@Controller
@RequestMapping("/board/*")
@ResponseBody
public class RESTfulFreeBoardController {

	@Autowired
	private FreeBoardServiceImpl freeBoardService;
	
	@RequestMapping("report_process.do")
	public void reportFreeBoard(ReportBFVo param, HttpSession session) {
		
		System.out.println(param);
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");

		int m_no = ((MemberVo)loginUser.get("memberVo")).getM_no();
		param.setM_no(m_no);
		
		
		freeBoardService.writeReport(param);
		
	}
	

	
	
}
