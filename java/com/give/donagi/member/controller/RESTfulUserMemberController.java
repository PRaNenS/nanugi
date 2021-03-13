package com.give.donagi.member.controller;

import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.give.donagi.member.service.UserMemberServiceImpl;
import com.give.donagi.util.CommonsMessageDigest;
import com.give.donagi.vo.*;

@Controller
@ResponseBody
@RequestMapping("/member/*")
public class RESTfulUserMemberController {
	@Autowired
	UserMemberServiceImpl memberService;

	@RequestMapping("exist_id.do")
	public String checkId(String check_id) {
		String result = null;
		
		if(this.memberService.existId(check_id)) {
			
			result = "true";
			
		}else {
			
			result = "false";
		}
		
		System.out.println("[System] existId: " + result);
		
		return result;
	}
	
	@RequestMapping("check_login.do")
	public String checkLogin(String check_id, String check_pw) {
		String result = null;
		String password = CommonsMessageDigest.messageDigest(check_pw); // 비밀번호 암호화
		MemberVo checkMember = this.memberService.checkLogin(check_id, password);
		
		if(checkMember == null) {
			
			result = "true"; // 로그인 불가능
			
		}else {
			
			result = "false"; // 로그인 가능
		}
		
		System.out.println("[System] checkLogin: " + result);
		
		return result;
	}
	
	@RequestMapping("update_pw_process.do")
	public void updatePwProcess(HttpSession session, MemberVo memberParam) {
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		String password = CommonsMessageDigest.messageDigest(memberParam.getM_pw());
		
		if(loginUser != null) {
			
			System.out.println("[Get] updatePwProcess");
			
			memberParam.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no());
			memberParam.setM_pw(password);
			this.memberService.updatePassword(memberParam);
		}
	}
	
	@RequestMapping("update_user_process.do")
	public void updateUserProcess(HttpSession session, MemberVo memberParam) {
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");

		if(loginUser != null) {
			
			System.out.println("[Get] updateUserProcess");
			
			memberParam.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no());
			this.memberService.updateUserData(memberParam);
		}
	}
}
