package com.give.donagi.mypage.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.give.donagi.mypage.service.UserMypageServiceImpl;
import com.give.donagi.util.service.CommonsUtilServiceImpl;
import com.give.donagi.vo.*;

@Controller
@ResponseBody
@RequestMapping("/mypage/*")
public class RESTfulUserMypageController {
	@Autowired
	private UserMypageServiceImpl mypageService;
	@Autowired
	private CommonsUtilServiceImpl utilService;
	
	@RequestMapping("get_upload_profile.do")
	public HashMap<String, Object> getUploadProfile(MultipartFile file) {
		HashMap<String, Object> map = new HashMap<String, Object>(); // 전송 데이터
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
		
		map.put("link", "/upload/" + uploadSubRoot + "/" + newFileName);
		
		System.out.println("[Send] getUploadProfile");
		
		return map;
	}
	
	// 기관유저 신청
	@RequestMapping("request_com_process.do")
	public void requestComProcess(HttpSession session, MRequestComVo mRequestComParam) {
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		System.out.println("[Get] requestComProcess");
		
		this.mypageService.requestCom(mRequestComParam, loginUser);
		
		System.out.println("[Send] requestComProcess");
	}
}
