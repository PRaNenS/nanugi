package com.give.donagi.volunteer.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.give.donagi.vo.MSTVDateVo;
import com.give.donagi.vo.MSTVImgVo;
import com.give.donagi.volunteer.service.UserVolunteerServiceImpl;

@Controller
@ResponseBody
@RequestMapping("/volunteer/*")
public class RESTfulUserVolunteerController 
{
	@Autowired
	private UserVolunteerServiceImpl volunteerService;
	
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
	
	public ArrayList<MSTVDateVo> getDateList(int mst_v_no)
	{
		System.out.println("[Send] DateList");
		
		return this.volunteerService.getDateList(mst_v_no);
	}
}
