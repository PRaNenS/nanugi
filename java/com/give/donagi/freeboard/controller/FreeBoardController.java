package com.give.donagi.freeboard.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.give.donagi.freeboard.service.FreeBoardCommentServiceImpl;
import com.give.donagi.freeboard.service.FreeBoardHateServiceImpl;
import com.give.donagi.freeboard.service.FreeBoardLikeServiceImpl;
import com.give.donagi.freeboard.service.FreeBoardServiceImpl;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.util.service.CommonsUtilServiceImpl;
import com.give.donagi.vo.BFHateVo;
import com.give.donagi.vo.BFImgLinkVo;
import com.give.donagi.vo.BFLikeVo;
import com.give.donagi.vo.BoardFreeVo;
import com.give.donagi.vo.CommentBFVo;
import com.give.donagi.vo.MemberVo;


@Controller
@RequestMapping("/board/*")
public class FreeBoardController {
	
	@Autowired
	private FreeBoardServiceImpl freeBoardService;
	
	@Autowired
	private FreeBoardCommentServiceImpl freeBoardCommentService;
	
	@Autowired
	private FreeBoardLikeServiceImpl freeBoardLikeService;
	
	@Autowired
	private FreeBoardHateServiceImpl freeBoardHateService;
	
	@Autowired
	private CommonsUtilServiceImpl utilService;
	
	
	// 자유게시판 main
	@RequestMapping("freeboard_page.do")
	public String freeBoardPage(Model model, String search_word, String search_type, @RequestParam(value="page_no", defaultValue="1") int page_no, HttpSession session) {
		
		String path = null;
		ArrayList<HashMap<String, Object>> resultList = freeBoardService.getFreeBoardList(search_word, search_type, page_no);
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		int pageCount = freeBoardService.getFreeBoardPageCount();
		int currentPage = page_no;
		int beginPage = ((currentPage-1)/5)*5 + 1;
		int endPage = ((currentPage-1)/5 + 1)*(5);
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		if(loginUser != null) {
			path = "user/board/freeboard_page";
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 조회
			
			model.addAttribute("resultList", resultList);
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
	
	
	// 글 작성
	@RequestMapping("freeboard_write_page.do")
	public String freeBoardWrite(HttpSession session) {
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			path = "user/board/freeboard_write_page";
			
		}else {
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	
	// 글 작성 process
	@RequestMapping("freeboard_write_process.do")
	public String freeBoardWriteProcess(HttpSession session, BoardFreeVo param, MultipartFile [] files) {
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
		
		// 이미지
		ArrayList<BFImgLinkVo> imgList = new ArrayList<BFImgLinkVo>();
	
		for(MultipartFile file : files) {
			
			if(file.getSize() <= 0) {
				continue;
			}
			
			// 날짜별 폴더 만들기
			String uploadRootFolderName = "/upload_files/";
				
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String todayFolder = sdf.format(today);
				
			String uploadFolderName = uploadRootFolderName + todayFolder;
				
			File uploadFolder = new File(uploadFolderName);
			
			if(!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}
		
			String originalFileName = file.getOriginalFilename();
				
			String randomName = UUID.randomUUID().toString();
			long currentTime = System.currentTimeMillis();
				
			randomName = randomName + "_" + currentTime;
				
			String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
				
			randomName += ext;
			
			try {
				file.transferTo(new File(uploadFolderName + "/" + randomName));
			}catch(Exception e) {
				e.printStackTrace();
				}
				
			// imageVo 객체 생성
			BFImgLinkVo imageVo = new BFImgLinkVo();
			String link = "/upload/" + todayFolder + "/" + randomName;
			
			imageVo.setBfil_path(link);	
			imgList.add(imageVo);
				
		}
		
		if(loginUser != null) {
			path = "redirect:/board/freeboard_page.do";
			
			int m_no = memberVo.getM_no();
			param.setM_no(m_no);
			freeBoardService.writeFreeBoard(param, imgList);
			
			int no = param.getMst_bfh_no();
			
		}else {
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
		
	}
	
	// 페이지 상세보기
	@RequestMapping("freeboard_read_page.do")
	public String readFreeBoard(Model model, int bf_no, HttpSession session) {
		
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			path = "user/board/freeboard_read_page";
			
			HashMap<String, Object> result = freeBoardService.getFreeBoard(bf_no, loginUser);
			this.utilService.getProfile(model, loginUser); // 프로필 이미지 조회
			// 글 띄어쓰기, 엔터키 적용
			String str = ((BoardFreeVo)result.get("freeBoardVo")).getBf_content();
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
		

	
	// 글 삭제
	@RequestMapping("freeboard_delete_process.do")
	public String deleteFreeBoardProcess(int bf_no, HttpSession session) {
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
		freeBoardService.deleteFreeBoard(bf_no);
		
		path = "redirect:/board/freeboard_page.do";
		
		}else {
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		return path;
	}
	
	// 글 수정
	@RequestMapping("freeboard_update_page.do")
	public String updateFreeBoardPage(Model model, int bf_no, HttpSession session) {
		
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
		
			HashMap<String, Object> map = freeBoardService.getFreeBoard(bf_no, loginUser);
			model.addAttribute("result", map);

			path = "user/board/freeboard_update_page";

		}else {
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[System] update");
		
		
		System.out.println("[Move]" + path);
		return path;
	}
	
	// 글 수정 process
	@RequestMapping("freeboard_update_process.do")
	public String updateFreeBoardProcess(BoardFreeVo param, HttpSession session) {
		String path = null;
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			int bf_no = param.getBf_no();
			freeBoardService.updateFreeBoard(param);
			System.out.println("[System] update processing");
			
			path = "redirect:/board/freeboard_read_page.do?bf_no=" + bf_no;
			
		}else {
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[System]" + path);
	
		return path;
	}
	
	
	// 댓글 불러오기
	@RequestMapping("freeboard_comment_page.do")
	private ArrayList<HashMap<String, Object>> getCommentList(int bf_no){
		
		ArrayList<HashMap<String, Object>> result = freeBoardCommentService.getCommentList(bf_no);
		
		return result;
	}
	
	
	// 댓글 작성 process
	@RequestMapping("freeboard_comment_process.do")
	public String writeFreeBoardCommentProcess(CommentBFVo param, HttpSession session) {
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
		
		if(loginUser != null) {
			int bf_no = param.getBf_no();
			
			int m_no = memberVo.getM_no();
			param.setM_no(m_no);
			
			freeBoardCommentService.writeComment(param);
			
			System.out.println("[Move] freeboard comment write success ->> redirect");
			
			path = "redirect:./freeboard_read_page.do?bf_no=" + bf_no;
		
		
		}else {
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move]" + path);
			
		return path;
		
			
	}
	
	// 댓글 삭제
	@RequestMapping("freeboard_comment_delete_process.do")
	public String deleteComment(int cbf_no, HttpSession session, BoardFreeVo vo) {
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			
			int bf_no = vo.getBf_no();
			
			freeBoardCommentService.deleteComment(cbf_no);
		
			path = "redirect:/board/freeboard_read_page.do?bf_no=" + bf_no;
		
		}else {
			path = "redirect:../member/login_page.do";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	
	// 추천
	@RequestMapping("freeboard_like_process.do")
	public String boardLike(BFLikeVo vo, HttpSession session, int bf_no) {
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
		
		if(loginUser != null) {
			path = "redirect:./freeboard_read_page.do?bf_no=" + bf_no;
			
			int m_no = memberVo.getM_no();
			vo.setBf_no(bf_no);			
			vo.setM_no(m_no);

			
			freeBoardLikeService.like(vo);
	
			
		}else {
			
			path = "redirect:../member/login_page.do";
		
		}
		
		System.out.println("[System] " + path);
		
		return path;
	}
	
	// 추천 취소
	@RequestMapping("freeboard_like_cancel_process.do")
	public String boardCancelLike(BFLikeVo vo, HttpSession session, int bf_no) {
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
		
		if(loginUser != null) {
			path = "redirect:./freeboard_read_page.do?bf_no=" + bf_no;
			
			int m_no = memberVo.getM_no();
			vo.setBf_no(bf_no);
			vo.setM_no(m_no);
			
			freeBoardLikeService.cancelLike(vo);
		
		}else {
			
			path = "redirect:../member/login_page.do";
		
		}
		
		System.out.println("[System] " + path);
		
		return path;
	}
	
	
	
	// 비추천
	@RequestMapping("freeboard_hate_process.do")
	public String boardHate(BFHateVo vo, HttpSession session, int bf_no) {
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
		
		if(loginUser != null) {
			path = "redirect:./freeboard_read_page.do?bf_no=" + bf_no;
			
			int m_no = memberVo.getM_no();
			vo.setBf_no(bf_no);
			vo.setM_no(m_no);
			
			freeBoardHateService.hate(vo);
			
		}else {
			
			path = "redirect:../member/login_page.do";
		}
		
		return path;
		
	}
	
	
	// 비추천 취소
	@RequestMapping("freeboard_hate_cancel_process.do")
	public String cancelHate(BFHateVo vo, HttpSession session, int bf_no) {
		String path = null;
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		MemberVo memberVo = (MemberVo)loginUser.get("memberVo");
		
		if(loginUser != null) {
			path = "redirect:./freeboard_read_page.do?bf_no=" + bf_no;
			
			int m_no = memberVo.getM_no();
			vo.setBf_no(bf_no);
			vo.setM_no(m_no);
			
			freeBoardHateService.cancelHate(vo);
		
		}else {
			
			path = "redirect:../member/login_page.do";
		
		}
		
		System.out.println("[System] " + path);
		
		return path;
	}

}
	
		
	
	

