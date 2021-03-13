package com.give.donagi.event.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.give.donagi.event.service.UserEventServiceImpl;
import com.give.donagi.vo.*;

@Controller
@ResponseBody
@RequestMapping("/event/*")
public class RESTfulUserEventController {
	@Autowired
	UserEventServiceImpl eventService;
	
	// 업로드 썸네일 받기
	@RequestMapping("get_upload_thumbnail.do")
	public HashMap<String, Object> getUploadThumbnail(MultipartFile file) {
		String uploadSubRoot = null; // 업로드 하위 경로
		String newFileName = null; // 파일이름 생성
		
		//파일 업로드 처리
		if(file != null) {
			
			if(!file.getOriginalFilename().isEmpty()) {
				String uploadRoot = null;  // 업로드 경로
				File uploadDir = null; // 파일 업로드 경로
				String ext = null; // 원래 파일 이름
				
				uploadRoot = "/upload_files/";
				uploadSubRoot = new SimpleDateFormat("yyyy/MM/dd").format(new Date()); // 업로드 하위 경로
				uploadDir = new File(uploadRoot + uploadSubRoot); // 파일 업로드 경로
				ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")); // 파일 확장자
				newFileName = UUID.randomUUID().toString() + "_" + Long.toString(System.currentTimeMillis()) + ext; // 파일이름 생성
				
				if(!uploadDir.exists()) { // 파일 업로드 루트가 없을 시
					uploadDir.mkdirs(); // 파일 업로드 루트 생성
				}
				
				try {
					file.transferTo(new File(uploadDir + "/" + newFileName)); // 파일 업로드
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("link", "/upload/" + uploadSubRoot + "/" + newFileName);
		
		System.out.println("[Send] get_upload_thumbnail.do");
		
		return map;
	}
	
	// 댓글 작성 처리
	@RequestMapping("write_comment_process.do")
	public void writeCommentProcess(HttpSession session, CommentBEVo commentBEVo) {
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser"); // 로그인 유저 정보
		
		commentBEVo.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no()); // 작성자 설정
		this.eventService.insertComment(commentBEVo); // 댓글 작성
	}
	
	// 댓글 삭제 처리
	@RequestMapping("delete_comment_process.do")
	public void deleteCommentProcess(int cbe_no) {
		
		this.eventService.deleteComment(cbe_no);
	}
	
	// 댓글 가져오기
	@RequestMapping("get_comment_list.do")
	public ArrayList<HashMap<String, Object>> getCommentList(int be_no) {

		return this.eventService.getCommentList(be_no);
	}
}
