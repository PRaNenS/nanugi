package com.give.donagi.volunteer.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.give.donagi.member.service.UserMemberServiceImpl;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.util.service.CommonsUtilServiceImpl;
import com.give.donagi.vo.CommentRVVo;
import com.give.donagi.vo.JoinVVo;
import com.give.donagi.vo.MSTVolunteerVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.RecommendRVVo;
import com.give.donagi.vo.ReviewVolunteerVo;
import com.give.donagi.volunteer.service.UserVolunteerServiceImpl;

@Controller
@RequestMapping("/volunteer/*")
public class UserVolunteerController 
{
	@Autowired
	private CommonsUtilServiceImpl utilService;
	
	@Autowired
	private UserVolunteerServiceImpl volunteerService; 
	
	@Autowired
	private UserMemberServiceImpl memberService;

	@RequestMapping("list_page.do")
	public String listPage(Model model,HttpSession session,@RequestParam(value="page_num", defaultValue="1") int page_num, 
			@RequestParam(value="search_status", defaultValue="2") int status, @RequestParam(value="categoryNo", defaultValue="0") int categoryNo)
	{
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
	
		this.utilService.getBanner(model);
		
		if(loginUser != null)
		{
			ArrayList<HashMap<String, Object>> volunteerList = null; // 펀딩리스트
			HashMap<String, Object> searchMap = new HashMap<String, Object>(); // 검색 조건
			this.utilService.getBanner(model);
			
			this.utilService.getProfile(model, loginUser);
			
			int currentPage = page_num; // 현재 페이지
			int beginPage = ((currentPage - 1) / 5) * 5 + 1; // 시작 페이지
			int endPage = ((currentPage - 1) / 5 + 1) * 5; // 끝 페이지
			int pageCount = 0; // 최대 페이지
			
			path = "user/volunteer/list_page";
			
			
			searchMap.put("currentPage", currentPage); // 페이지 조건
			searchMap.put("status", status);
			
			if(categoryNo > 0) 
			{
				searchMap.put("categoryNo", categoryNo);
			}
			
			volunteerList = this.volunteerService.getVolunteerList(searchMap);
			
			pageCount = this.volunteerService.getPageCount(searchMap);
			
			if(endPage > pageCount) {
				endPage = pageCount;
			}
			
			model.addAttribute("volunteerList",volunteerList);
			model.addAttribute("categoryList",this.volunteerService.getCategoryList());
			model.addAttribute("currentPage",currentPage);
			model.addAttribute("beginPage",beginPage);
			model.addAttribute("endPage",endPage);
			model.addAttribute("pageCount",pageCount);
			model.addAttribute("categoryNo", categoryNo);
			model.addAttribute("searchStatus", status);
	
		}
		else 
		{
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("create_page.do")
	public String createPage(Model model, HttpSession session)
	{
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser != null)
		{
			path = "user/volunteer/create_page";
			
			this.utilService.getProfile(model, loginUser);
			model.addAttribute("categoryList",this.volunteerService.getCategoryList());
		}
		else 
		{
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("create_process.do")
	public String createVolunteerProcess(HttpSession session, MSTVolunteerVo mstVolunteerVo, String mst_vi_img_link, @RequestParam("mst_vd_date") String[] mstVdates, @RequestParam("mst_vd_time") String[] mstVdtimes)
	{
		System.out.println(mst_vi_img_link);
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		System.out.println("here1");
		
		if(loginUser != null)
		{
			path = "redirect:./list_page.do";
			
			int mstVNo = 0;
			
			mstVNo = this.volunteerService.createVolunteer(loginUser, mstVolunteerVo);
			
			System.out.println(mstVNo);
			this.volunteerService.createVolunteerDate(mstVNo, mstVdates, mstVdtimes);
			this.volunteerService.createVolunteerImg(mstVNo, mst_vi_img_link);
		}
		
		else
		{
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("read_page.do")
	public String readPage(Model model , HttpSession session, int mst_v_no)
	{
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser != null)
		{
			ArrayList<HashMap<String, Object>> reviewVolList = 
					volunteerService.getTapReviewList(mst_v_no);
			
			path = "user/volunteer/read_page";
			
			this.utilService.getProfile(model, loginUser);
			
			model.addAttribute("volunteerData", this.volunteerService.getVolunteer(mst_v_no));
			model.addAttribute("DateList",this.volunteerService.getDateList(mst_v_no));
			model.addAttribute("reviewVolList",reviewVolList);
			
		}
		
		else
		{
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	

	@RequestMapping("join_page.do")
	public String joinPage(HttpSession session, Model model,int mst_v_no,@RequestParam("mst_vd_no") int[] mstVdNo) 
	{
		String path = null;
		

		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser != null)
		{
			
			CommonsDateConverter dateconvert = new CommonsDateConverter();
			HashMap<String, Object> volunteerData = null;
			ArrayList<HashMap<String, Object>> dateList = null;
			
			path = "user/volunteer/join_page";
			
			this.utilService.getProfile(model, loginUser);
			
			volunteerData = this.volunteerService.getVolunteer(mst_v_no);
			dateList = this.volunteerService.setVDate(mstVdNo);
			
			MemberVo vo = (MemberVo) loginUser.get("memberVo");
			
			
			model.addAttribute(loginUser);
			model.addAttribute("birth",dateconvert.getDate(vo.getM_birth()));
			model.addAttribute("volunteerData",volunteerData);
			model.addAttribute("dateList",dateList);
		}
		
		else
		{
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("join_process.do")
	public String joinProcess(HttpSession session,int mst_v_no, @RequestParam("mst_vd_no")int[] VDNo, JoinVVo joinParam)
	{
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser != null)
		{
			MemberVo joinUser = new MemberVo(); 
			MemberVo VolunteerUser = new MemberVo(); 
			HashMap<String, Object> volunteerData = null; 
			
			path = "redirect:./list_page.do";
			
			this.volunteerService.insertJv(loginUser, joinParam, VDNo,mst_v_no);
		}
		
		else
		{
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("delete_process.do")
	public String deleteProcess(HttpSession session, int mst_v_no)
	{
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser != null)
		{
			this.volunteerService.deleteVolunteer(mst_v_no);
			
			path = "redirect:./list_page.do";
			
		}	
		else
		{
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	// 후기 작성
			@RequestMapping("review_write_page.do")
			public String volWrite(HttpSession session, Model model) {
				String path = null;
				
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				this.utilService.getProfile(model, loginUser);
				
				if(loginUser != null) {
					path = "user/volunteer/review_write_page";
				
				}else {
					path = "redirect:../member/login_page.do";
				}
				
				System.out.println("[Move] " + path);
				
				return path;
			}
			
			// 후기 작성 process
			@RequestMapping("review_write_process.do")
			public String volWriteProcess(HttpSession session, ReviewVolunteerVo param) {
				String path = null;
				
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
				
				if(loginUser != null) {
					path = "redirect:/volunteer/list_page.do";
					
					int m_no = memberVo.getM_no();
					param.setM_no(m_no);
		
					this.volunteerService.writeRV(param);
				}
				
				return path;
			}
			
			// 봉사 후기 메인
			@RequestMapping("review_page.do")
			public String reviewVolPage(Model model, HttpSession session, String search_word, String search_type, @RequestParam(value = "page_no", defaultValue = "1")int page_no) {
				String path = null;
				
				ArrayList<HashMap<String, Object>> reviewList = volunteerService.getReviewVolList(search_word, search_type, page_no);
			
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				this.utilService.getProfile(model, loginUser);
				
				// 페이징
				int pageCount = volunteerService.PageCount();
				int currentPage = page_no;
				int beginPage = ((currentPage-1)/5) * 5 + 1;
				int endPage = ((currentPage-1)/5 + 1) * (5);
				
				if(endPage > pageCount) {
					endPage = pageCount;
				}
				
				if(loginUser != null) {
					path = "user/volunteer/review_page";
					
					model.addAttribute("reviewList", reviewList);
					model.addAttribute("currentPage", currentPage);
					model.addAttribute("beginPage", beginPage);
					model.addAttribute("endPage", endPage);
					model.addAttribute("pageCount", pageCount);
					
				}else {
					path = "redirect:../member/login_page.do";
				}
				
				System.out.println("[Move] " + path);
				
				return path;
				
			}
			
			// 리뷰 상세
			@RequestMapping("review_read_page.do")
			public String readRV(Model model, int rv_no, HttpSession session) {
				String path = null;
				
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				
				if(loginUser != null) {
					path = "user/volunteer/review_read_page";
					this.utilService.getProfile(model, loginUser); // 프로필 이미지 조회
					
					HashMap<String, Object> result = volunteerService.getReviewVol(rv_no, loginUser);
					// 글 띄어쓰기, 엔터키 적용
					String str = ((ReviewVolunteerVo)result.get("reviewVolunteerVo")).getRv_content();
					str = StringEscapeUtils.escapeHtml4(str);
					str = str.replaceAll("\n", "<br>");
					result.put("content", str);
					
					model.addAttribute("result", result);
					
				}else {
					path = "redirect:../member/login_page.do";
				}
				
				System.out.println("[Move] " + path);
				
				return path;
			}
			
			// 후기 삭제
			@RequestMapping("review_delete_process.do")
			public String deleteReviewVol(HttpSession session, int rv_no) {
				String path = null;
				
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				
				if(loginUser != null) {
					volunteerService.deleteReviewVol(rv_no);
					
					path = "redirect:/volunteer/review_page.do";
				
				}else {
					path = "redirect:../member/login_page.do";
				}
				
				System.out.println("[Move] " + path);
				
				return path;
			}
			
			// 후기 수정
			@RequestMapping("review_update_page.do")
			public String updateReviewVol(Model model, int rv_no, HttpSession session) {
				String path = null;
				
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				this.utilService.getProfile(model, loginUser);
				
				if(loginUser != null) {
					
					HashMap<String, Object> map = volunteerService.getReviewVol(rv_no, loginUser);
					model.addAttribute("result", map);
					
					path = "user/volunteer/review_update_page";
				
				}else {
					path = "redirect:../member/login_page.do";
				}
				
				System.out.println("[Move] " + path);
				
				return path;
			}
			
			// 후기 수정 process
			@RequestMapping("review_update_process.do")
			public String updateReviewVolProcess(ReviewVolunteerVo param, HttpSession session) {
				String path = null;
		
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				
				if(loginUser != null) {
					
					int rv_no = param.getRv_no();
					volunteerService.updateReviewVol(param);
					
					path = "redirect:/volunteer/review_read_page.do?rv_no=" + rv_no;
				
				}else {
					path = "redirect:../member/login_page.do";
				}
				
				System.out.println("[Move] "+ path);
				
				return path;
			}
			
			// 후기 댓글 작성
			@RequestMapping("review_comment_process.do")
			public String writeReviewVolComment(CommentRVVo param, HttpSession session) {
				String path = null;
				
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
				
				if(loginUser != null) {
					int rv_no = param.getRv_no();
					
					int m_no = memberVo.getM_no();
					param.setM_no(m_no);
					
					volunteerService.writeReviewVolComment(param);
					
					path = "redirect:/volunteer/review_read_page.do?rv_no=" + rv_no;
				}
				
				System.out.println("[Move] " + path);
				
				return path;
			}
			
			// 후기 댓글 삭제
			@RequestMapping("review_comment_delete_process.do")
			public String deleteReviewVolComment(int crv_no, ReviewVolunteerVo vo, HttpSession session) {
				String path = null;
				
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				
				if(loginUser != null) {
					
					int rv_no = vo.getRv_no();
					
					volunteerService.deleteReviewVolComment(crv_no);
					
					path = "redirect:/volunteer/review_read_page.do?rv_no=" + rv_no;
					
				}else {
					path = "redirect:../member/login_page.do";
				}
				
				System.out.println("[Move] " + path);
				
				return path;
			}
			
			// 후기 추천
			@RequestMapping("review_recommend_process.do")
			public String RVRecommend(RecommendRVVo vo, HttpSession session, int rv_no) {
				String path = null;
				
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
				
				if(loginUser != null) {
					path = "redirect:/volunteer/review_read_page.do?rv_no=" + rv_no;
					
					int m_no = memberVo.getM_no();
					vo.setRv_no(rv_no);
					vo.setM_no(m_no);
					
					volunteerService.recommend(vo);
				
				}
				System.out.println("[System] " + path);
			
				return path;
		
			}
			
			// 추천 취소
			@RequestMapping("review_recommend_cancel_process.do")
			public String RVRecommendCancel(RecommendRVVo vo, HttpSession session, int rv_no) {
				String path = null;
				
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
				
				if(loginUser != null) {
					path = "redirect:/volunteer/review_read_page.do?rv_no=" + rv_no;
					
					int m_no = memberVo.getM_no();
					vo.setM_no(m_no);
					vo.setRv_no(rv_no);
					
					volunteerService.cancelRecommend(vo);
					
				}
				
				System.out.println("[System] " + path);
				
				return path;
			}

}
