package com.give.donagi.reviewFunding.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.give.donagi.reviewFunding.service.UserReviewFundingServicempl;
import com.give.donagi.util.service.CommonsUtilServiceImpl;
import com.give.donagi.vo.CommentRFVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.ReviewFundingVo;

@Controller
@RequestMapping("/review_funding/*")
public class UserReviewFundingController {

	@Autowired
	private UserReviewFundingServicempl userReviewFundingService;
	@Autowired
	private CommonsUtilServiceImpl utilService;
	
	@RequestMapping("list_page.do")
	public String listPage(Model model ,HttpSession session,String search_word , String search_type  ,@RequestParam(value="page_no",defaultValue = "1") int page_num ) {
		
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		
		int pageCount = userReviewFundingService.getPageCount();
		
		System.out.println(page_num);
		
		int currentPage = page_num; 
		int beginPage = ((currentPage - 1) / 5) * 5 + 1; 
		int endPage = ((currentPage - 1) / 5 + 1) * 5; 
		
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		if(loginUser != null) {
			
			ArrayList<HashMap<String, Object>> resultList =
					userReviewFundingService.getReviewFundingList(page_num,search_word,search_type);
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 조회
			
			
			model.addAttribute("pageCount", pageCount);	
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("beginPage", beginPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("resultList", resultList);
			
			this.utilService.getProfile(model, loginUser);
			
			
			path = "user/review_funding/list_page";
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		return path;
				
	}
	
	@RequestMapping("create_page.do")
	public String createReviewFunding(HttpSession session , ReviewFundingVo reviewFundingVo ,Model model) {
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		String path = null;
		
		if(loginUser != null) {
			
			this.utilService.getProfile(model, loginUser);
			
		path = "user/review_funding/create_page";
		}else {
			path = "redirect:../member/login_page.do";
			}
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("create_process.do")
	public String createProcess(HttpSession session, ReviewFundingVo reviewFundingVo) {
			
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
			
			int m_no = memberVo.getM_no();
			
			reviewFundingVo.setM_no(m_no);
			
			userReviewFundingService.createReviewFunding(reviewFundingVo);
			
			
			path = "redirect:../review_funding/list_page.do";
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		return path;
				
	}
	
	@RequestMapping("read_page.do")
	public String readPage( int rf_no, Model model, HttpSession session) {
			
			String path = null;
			
			HashMap<String, Object> loginUser = 
					(HashMap<String, Object>)session.getAttribute("loginUser");
			
			if(loginUser != null) {
			
				HashMap<String, Object> map  = userReviewFundingService.getReviewRead(rf_no, loginUser);
				ArrayList<HashMap<String,Object>> Commentmap  = userReviewFundingService.getCommentList(rf_no);
				this.utilService.getProfile(model, loginUser); // 프로필 이미지 조회
				

				String str = ((ReviewFundingVo)map.get("reviewFundingVo")).getRf_content();
				str = StringEscapeUtils.escapeHtml4(str);
				str = str.replaceAll("\n", "<br>");
				map.put("contentRf", str);
				
				
				
				this.utilService.getProfile(model, loginUser);		
				model.addAttribute("map", map);// 게시글
				model.addAttribute("Commentmap", Commentmap);//댓글
					
				this.utilService.getProfile(model, loginUser);
				
				
				
			path = "user/review_funding/read_page";
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("update_page.do")
	public String updatePage(HttpSession session, Model model , int rf_no) {
		
		String path = null;
		
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			HashMap<String, Object> map = userReviewFundingService.getReviewRead(rf_no, loginUser);
			
			this.utilService.getProfile(model, loginUser);
			model.addAttribute("result", map);
				
			path = "user/review_funding/update_page";
		}else {
			path = "redirect:../member/login_page.do";
		}
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("update_process.do")
	public String updateProcess(HttpSession session, ReviewFundingVo reviewFundingVo) {
		String path = null;
		
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			System.out.println(reviewFundingVo.getRf_title());
			
			int rf_no = reviewFundingVo.getRf_no();
			
			userReviewFundingService.ReviewUpdate(reviewFundingVo);
				
			path = "redirect:./read_page.do?rf_no=" + rf_no;
		}else {
			path = "redirect:../member/login_page.do";
		}
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	
	@RequestMapping("delete_process.do")
	public String deleteContentProcess(int rf_no, HttpSession session) {
		
		String path = null;
		
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
		userReviewFundingService.ReviewDelete(rf_no);
		
			path = "redirect:../review_funding/list_page.do";
		}else {
			path = "redirect:../member/login_page.do";
		}
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("delete_recommend_process.do")
	public String deleteRecommendprocess(int rf_no , HttpSession session) {
		
		String path = null;
		
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>)session.getAttribute("loginUser");
		
		
		if(loginUser != null) {
			userReviewFundingService.ReCommendDel(rf_no, ((MemberVo)loginUser.get("memberVo")).getM_no());
		
			path = "redirect:./read_page.do?rf_no=" + rf_no;
			
		}else {
			path = "redirect:../member/login_page.do";
		}
		System.out.println("[Move] " + path);
		
		return path;
	}
	@RequestMapping("insert_recommend_process.do")
	public String insertRecommendProcess(int rf_no , HttpSession session) {

		String path = null;
		
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>)session.getAttribute("loginUser");		
		
		if(loginUser != null) {
			userReviewFundingService.ReCommend(rf_no, ((MemberVo)loginUser.get("memberVo")).getM_no());
			
			path = "redirect:./read_page.do?rf_no=" + rf_no;
			
		}else {
			path = "redirect:../member/login_page.do";
		}
		System.out.println("[Move] " + path);
		
		return path;	
	}
	
	@RequestMapping("write_comment_process.do")
	public String WriteCommentProcess(Model model,CommentRFVo commentRFVo, HttpSession session) {
	
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		MemberVo memberVo = (MemberVo) loginUser.get("memberVo");
			
	if(loginUser != null) {	
		
		int rf_no = commentRFVo.getRf_no();
		int m_no = memberVo.getM_no();
		commentRFVo.setM_no(m_no);
		commentRFVo.setRf_no(rf_no);
		userReviewFundingService.CommentWrite(commentRFVo);
		path = "redirect:./read_page.do?rf_no=" + rf_no;
		
	}else {
		path = "redirect:../member/login_page.do";
	}
	System.out.println("[Move] " + path);
	
	return path;	

	}
	@RequestMapping("delete_comment_process.do")
	public String DeleteCommentProcess(Model model, CommentRFVo commentRFVo, int crf_no, HttpSession session) {
	
		String path = null;
		
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>)session.getAttribute("loginUser");		
			
	if(loginUser != null) {	
		
		int rf_no = commentRFVo.getRf_no();
		commentRFVo.setRf_no(rf_no);
		userReviewFundingService.CommentDelete(crf_no);
		
		path = "redirect:./read_page.do?rf_no=" + rf_no;
		
	}else {
		path = "redirect:../member/login_page.do";
	}
	System.out.println("[Move] " + path);
	
	return path;	

	}
	
	
	
	
	
	
	
}
