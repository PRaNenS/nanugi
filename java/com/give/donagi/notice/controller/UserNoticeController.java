package com.give.donagi.notice.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.give.donagi.notice.service.UserNoticeServiceImpl;
import com.give.donagi.util.service.CommonsUtilServiceImpl;

@Controller
@RequestMapping("/notice/*")
public class UserNoticeController 
{
	@Autowired
	private UserNoticeServiceImpl UserNoticeService;
	
	@Autowired
	private CommonsUtilServiceImpl utilService;
	
	@RequestMapping("list_page.do")
	public String listPage(Model model, @RequestParam(value="page_num",defaultValue="1") int page_num, HttpSession session)
	{
		ArrayList<HashMap<String, Object>> resultList = UserNoticeService.getNoticeList(page_num);
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		this.utilService.getBanner(model);
		String path = null;
		
		if(loginUser != null)
		{
			this.utilService.getProfile(model, loginUser);
			
			
			int pageCount = UserNoticeService.getPageCount(); //페이지 count
			int currentPage = page_num; // 현재 페이지 
			int beginPage = ((currentPage-1)/5)*5+1; //페이징
			int endPage =  ((currentPage-1)/5+1)*5;
			
			if(endPage > pageCount)
			{
				endPage = pageCount;
			}
			
			model.addAttribute("resultList",resultList); 
			model.addAttribute("currentPage",currentPage);
			model.addAttribute("beginPage",beginPage);
			model.addAttribute("endPage",endPage);
			model.addAttribute("pageCount",pageCount);			
			
			path = "user/notice/list_page";
			System.out.println("[Move]" + path);
		}
		else
		{
			path = "redirect:../member/login_page.do";
		}
		
		return path;
	}
	
	@RequestMapping("read_page.do")
	public String readContentPage(Model model, int bn_no,HttpSession session) // 공지사항 읽기
	{
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		this.utilService.getProfile(model, loginUser);
		if(loginUser != null)
		{
			HashMap<String, Object> map = null;
			path = "user/notice/read_page";
			
			map = UserNoticeService.getContent(bn_no, loginUser);
			model.addAttribute("result",map);	
		}
		else
		{
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move]" + path);	
		
		return path;
	}
}
