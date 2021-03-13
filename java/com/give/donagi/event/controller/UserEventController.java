package com.give.donagi.event.controller;

import java.util.*;
import javax.servlet.http.HttpSession;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.give.donagi.event.service.UserEventServiceImpl;
import com.give.donagi.util.service.CommonsUtilServiceImpl;
import com.give.donagi.vo.*;

@Controller
@RequestMapping("/event/*")
public class UserEventController {
	@Autowired
	CommonsUtilServiceImpl utilService;
	@Autowired
	UserEventServiceImpl eventService;

	// 이벤트 리스트 페이지
	@RequestMapping("list_page.do")
	public String listPage(Model model, HttpSession session, @RequestParam(value="page_num", defaultValue="1") int page_num, 
			@RequestParam(value="search_status", defaultValue="2") int status) {
		String path = null; // 화면 전환 경로
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser"); // 로그인 유저 정보
		this.utilService.getBanner(model);
		if(loginUser != null) { // 로그인 시
			ArrayList<HashMap<String, Object>> eventListData = null; // 이벤트리스트
			HashMap<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
			int pageCount = 0;
			int currentPage = page_num;
			int beginPage = ((currentPage - 1) / 5) * 5 + 1;
			int endPage = ((currentPage - 1) / 5 + 1) * 5;
		
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 조회
			
			// 검색 조건 설정
			searchMap.put("currentPage", currentPage); // 페이지 조건
			searchMap.put("status", status);
						
			eventListData = this.eventService.getBoard(searchMap); // 검색조건의 이벤트리스트 출력

			// 페이지 처리
			pageCount = this.eventService.getPageCount(searchMap); // 페이지 정보
			
			if(endPage > pageCount) {
				endPage = pageCount;
			}
						
			model.addAttribute("pageCount", pageCount); // 페이지 정보 전송
			model.addAttribute("beginPage", beginPage); // 시작 페이지 전송
			model.addAttribute("endPage", endPage); // 끝 페이지 전송
			model.addAttribute("currentPage", currentPage); // 현재 페이지 전송
			model.addAttribute("searchStatus", status); // 검색 상태
			model.addAttribute("eventListData", eventListData); // 이벤트 리스트 정보 전송
			
			path = "user/event/list_page";
			
		}else { // 그 외
			path = "redirect:../member/login_page.do";
			
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	// 이벤트 개설 페이지
	@RequestMapping("create_page.do")
	public String createEvent(Model model, HttpSession session) {
		String path = null; // 화면 전환 경로
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser"); // 로그인 유저 정보
		
		// 화면 처리
		if(loginUser != null) { // 로그인 시
			path = "user/event/create_page";
			
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 조회
			
		}else { // 그 외
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	// 이벤트 개설 처리
	@RequestMapping("create_process.do")
	public String createProcess(HttpSession session, BEventVo bEventVoParam, BEImgVo beImgParam) {
		String path = null; // 화면 전환 경로
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser"); // 로그인 유저 정보
		
		// 이벤트 개설 처리
		if(loginUser != null) {
			
			path = "redirect:./list_page.do";

			this.eventService.insertEvent(bEventVoParam, beImgParam, loginUser); // 이벤트 입력 정보 전송
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[System] createProcess Success");
		
		return path;
	}
	
	// 이벤트 상세 페이지
	@RequestMapping("read_page.do")
	public String readEvent(Model model, HttpSession session, int be_no) {
		String path = null; // 화면 전환 경로
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser"); // 로그인 유저 정보
		
		// 화면 처리
		if(loginUser != null) {
			HashMap<String, Object> eventData = this.eventService.getEvent(be_no); // 이벤트 정보
			
			path = "user/event/read_page";
			
			this.utilService.getProfile(model, loginUser); // 로그인 프로필 조회
			String htmlEscapeContent = StringEscapeUtils.escapeHtml4(((BEventVo)eventData.get("bEventVo")).getBe_content());
			eventData.put("htmlEscapeContent", htmlEscapeContent.replaceAll("\n", "<br>"));			
			
			model.addAttribute("eventData", eventData); // 이벤트 정보 전송
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("delete_process.do")
	public String deleteProcess(int be_no) {
		String path = "redirect:../event/list_page.do";
		
		this.eventService.deleteEvent(be_no);
		
		System.out.println("[Move] " + path);
		
		return path;
	}
}
