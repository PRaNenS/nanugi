package com.give.donagi.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.give.donagi.freeboard.service.FreeBoardCommentServiceImpl;
import com.give.donagi.freeboard.service.FreeBoardServiceImpl;
import com.give.donagi.main.service.UserMainServiceImpl;
import com.give.donagi.manager.service.ManagerServiceImpl;
import com.give.donagi.notice.service.UserNoticeServiceImpl;
import com.give.donagi.reviewFunding.service.UserReviewFundingServicempl;
import com.give.donagi.util.service.CommonsUtilServiceImpl;
import com.give.donagi.vo.BNoticeVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.volunteer.service.UserVolunteerServiceImpl;

@Controller
@RequestMapping("/manager/*")
public class ManagerController 
{
	@Autowired
	private UserNoticeServiceImpl UserNoticeService;
	
	@Autowired
	private UserReviewFundingServicempl userReviewFundingService;
	
	@Autowired
	private ManagerServiceImpl managerService;
	
	@Autowired
	private FreeBoardServiceImpl freeBoardService;
	
	@Autowired
	private UserVolunteerServiceImpl volunteerService;
	
	@Autowired
	private CommonsUtilServiceImpl utilService;
	
	@Autowired
	private UserMainServiceImpl mainService;;
	
	@RequestMapping("manager_page.do")
	public String managerPage(Model model, HttpSession session)
	{
		String path = "manager/manager_page";
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		ArrayList<HashMap<String, Object>> fundingList = null; // 펀딩리스트
		ArrayList<HashMap<String, Object>> volunteerList = null;
		HashMap<String, Object> searchMap = new HashMap<String, Object>(); // 펀딩 검색 조건
		this.utilService.getBanner(model);
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
	
	@RequestMapping("manager_notice_page.do") 
	public String noticePage(Model model, @RequestParam(value="page_num",defaultValue="1") int page_num, HttpSession session) 
	{
		ArrayList<HashMap<String, Object>> resultList = UserNoticeService.getNoticeList(page_num);
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		this.utilService.getProfile(model, loginUser);
		int pageCount = UserNoticeService.getPageCount(); 
		
		int currentPage = page_num; 
		int beginPage = ((currentPage-1)/5)*5+1; 
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
		
		String path = "manager/manager_notice_page";
		System.out.println("[Move]" + path);
		
		return path;
	}
	@RequestMapping("manager_read_notice.do")
	public String readContentPage(Model model, int bn_no,HttpSession session) // 怨듭��궗�빆 �씫湲�
	{
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		HashMap<String, Object> map = UserNoticeService.getContent(bn_no, loginUser);
		this.utilService.getProfile(model, loginUser);
		model.addAttribute("result",map);	

		String path = "manager/manager_read_notice";
		System.out.println("[Move]" + path);	
		
		return path;
	}
	
	@RequestMapping("delete_content_process.do")
	public String deleteContentProcess(int bn_no)
	{
		UserNoticeService.deleteContent(bn_no);
		
		return "redirect:./manager_notice_page.do";
	}
	
	@RequestMapping("manager_notice_write.do")
	public String writeNotice(Model model, HttpSession session)
	{
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		String path = "manager/manager_notice_write";
		System.out.println("[Move]" + path);
		this.utilService.getProfile(model, loginUser);
		
		return path;
	}
	@RequestMapping("write_notice_process.do")
	public String writeNoticeProcess(HttpSession session, BNoticeVo param)
	{
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		int m_no = ((MemberVo)loginUser.get("memberVo")).getM_no();	
		
		param.setM_no(m_no);
		
		UserNoticeService.writeContent(param);
		
		String path = "redirect:./manager_notice_page.do";
		System.out.println("[Move]" + path);	
		
		return path;
	}
	
	@RequestMapping("manager_update_notice.do")
	public String updateNotice(Model model, int bn_no,HttpSession session)
	{
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		HashMap<String, Object> map = UserNoticeService.getContent(bn_no, loginUser);
		this.utilService.getProfile(model, loginUser);
		model.addAttribute("result",map);
		
		String path = "manager/manager_update_notice";
		System.out.println("[Move]" + path);	
		
		return path;
	}
	
	@RequestMapping("update_notice_process.do")
	public String updateNoticeProcess(HttpSession session, BNoticeVo param)
	{
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		MemberVo memberVo = (MemberVo) loginUser.get("memberVo");
		int m_no = memberVo.getM_no();
		
		param.setM_no(m_no);
		
		UserNoticeService.updateContent(param);
		
		String path = "redirect:./manager_notice_page.do";
		System.out.println("[Move]" + path);	
		
		return path;	
	}
	
	@RequestMapping("manager_update_user.do")
	public String updateUser(HttpSession session, Model model)
	{
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		ArrayList<HashMap<String, Object>> resultList = managerService.UpdateUser();
		this.utilService.getProfile(model, loginUser);
		model.addAttribute("resultList",resultList); 
		
		path = "manager/manager_update_user";
		System.out.println("[Move]" + path);
		
		return path;
	}
	
	@RequestMapping("delete_update_request.do")
	public String deleteRequest(int mrc_no)
	{
		String path = null;
		this.managerService.deleteRequest(mrc_no);
		
		path = "redirect:./manager_update_user.do";
		System.out.println("[Move]" + path);
		
		return path;
	}
	
	@RequestMapping("approve_request.do")
	public String approveRequest(int m_no)
	{
		String path = null;
		this.managerService.approvedRequest(m_no);
		
		path = "redirect:./manager_update_user.do";
		System.out.println("[Move]" + path);
		
		return path;
	}
	
	@RequestMapping("freeboard_list.do")
	public String freeBoardReportPage(Model model, HttpSession session, @RequestParam(value="page_no", defaultValue="1") int page_no) {
		
		ArrayList<HashMap<String, Object>> resultList = freeBoardService.getReportList(page_no);
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		this.utilService.getProfile(model, loginUser);
		int pageCount = freeBoardService.getReportPageCount();
		int currentPage = page_no;
		int beginPage = ((currentPage-1)/5)*5 + 1;
		int endPage = ((currentPage-1)/5 + 1)*(5);
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		model.addAttribute("resultList", resultList);
		
		String path = "manager/report/freeboard/freeboard_list";
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("free_penalty_process.do")
	public String FpenaltyProcess(int bf_no)
	{
		String path = null;
		this.managerService.FpenaltyProcess(bf_no);
		
		path = "redirect:./freeboard_list.do";
		System.out.println("[Move]" + path);
		
		return path;
	}
			
		// 봉사게시판 신고 main page
		@RequestMapping("volunteer_report_page.do")
		public String rvReportPage(Model model, HttpSession session, @RequestParam(value="page_no", defaultValue="1") int page_no) {
			
			ArrayList<HashMap<String, Object>> resultList = volunteerService.getReportList(page_no);
			HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
			this.utilService.getProfile(model, loginUser);
			int pageCount = freeBoardService.getReportPageCount();
			int currentPage = page_no;
			int beginPage = ((currentPage-1)/5)*5 + 1;
			int endPage = ((currentPage-1)/5 + 1)*(5);
			
			if(endPage > pageCount) {
				endPage = pageCount;
			}
			
			model.addAttribute("resultList", resultList);
			
			String path = "manager/report/volunteer/volunteer_report_page";
			
			System.out.println("[Move] " + path);
			
			return path;
			
		}
		
		@RequestMapping("volunteer_penalty_process.do")
		public String VpenatlypenaltyProcess(int rv_no)
		{
			String path = null;
			this.managerService.VpenaltyProcess(rv_no);
			
			path = "redirect:./volunteer_report_page.do";
			System.out.println("[Move]" + path);
			
			return path;
		}
		
		@RequestMapping("report_page.do")
		public String ReportListPage(HttpSession session, Model model) {
			
			
			String path = null;
			
			HashMap<String, Object> loginUser = 
					(HashMap<String, Object>)session.getAttribute("loginUser");		
				
			this.utilService.getProfile(model, loginUser);
			if(loginUser != null) {	
				ArrayList<HashMap<String, Object>> resultList = userReviewFundingService.getReportList();
				
				model.addAttribute("resultList", resultList);
				
			path = "/manager/report/funding/report_page";
			
			}else {
				path = "redirect:../member/login_page.do";
			}
			System.out.println("[Move] " + path);
			
			return path;	

		}
		
		@RequestMapping("fun_penalty_process.do")
		public String FunpenatlypenaltyProcess(int rf_no)
		{
			String path = null;
			this.managerService.FunpenaltyProcess(rf_no);
			
			path = "redirect:./report_page.do";
			System.out.println("[Move]" + path);
			
			return path;

		}
		
		@RequestMapping("member_list.do")
		public String MemberList(HttpSession session, Model model)
		{
			String path = null;
			
			HashMap<String, Object> loginUser = 
					(HashMap<String, Object>)session.getAttribute("loginUser");
			this.utilService.getProfile(model, loginUser);
			
			if(loginUser != null) {	
				ArrayList<HashMap<String, Object>> resultList = managerService.MemberInfo();
				
				model.addAttribute("resultList", resultList);
				
			path = "/manager/member_list";
			
			}else {
				path = "redirect:../member/login_page.do";
			}
			System.out.println("[Move] " + path);
			
			return path;
		}
	}
