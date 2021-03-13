package com.give.donagi.member.controller;

import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.give.donagi.manager.service.ManagerServiceImpl;
import com.give.donagi.member.service.UserMemberServiceImpl;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.util.CommonsMessageDigest;
import com.give.donagi.vo.*;

@Controller
@RequestMapping("/member/*")
public class UserMemberController {
	@Autowired
	private UserMemberServiceImpl memberService;
	
	@Autowired
	private ManagerServiceImpl managerService;

	@RequestMapping("login_page.do")
	public String loginPage() {
		String path = "user/member/login_page";
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("login_process.do")
	public String loginProcess(HttpSession session, MemberVo memberParam, Model model) {
		
		
		String path = null;
		String password = CommonsMessageDigest.messageDigest(memberParam.getM_pw()); // 패스워드 암호화
		HashMap<String, Object> loginUserData = null;
		
		memberParam.setM_pw(password);
		loginUserData = this.memberService.login(memberParam);
		int mst_mt_no = ((MemberVo)loginUserData.get("memberVo")).getMst_mt_no();
		int m_no = ((MemberVo)loginUserData.get("memberVo")).getM_no();
		
		MLimitVo vo = this.managerService.selectlimit(m_no);
		
		CommonsDateConverter dateConvert = new CommonsDateConverter();
		int Flg = this.managerService.limitFlg(m_no);
	
			if(Flg==1)
			{
				path = "user/member/login_dismiss_page";
				
				model.addAttribute("userNick", memberParam.getM_nick()); // 닉네임 송신
				model.addAttribute("limitVo", vo);
				model.addAttribute("date",dateConvert.getDate(vo.getMl_date()));
				System.out.println("[Move] " + path);
				
				return path;
			}
			else
			{
				if(mst_mt_no !=1)
				{
					
					session.setAttribute("loginUser",loginUserData );
					path = "redirect:../main/main_page.do";

				}
				else
				{
					session.setAttribute("loginUser",loginUserData );
					path = "redirect:../manager/manager_page.do";
				}
			}
	
	
		System.out.println("[System] Login");
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("logout_process.do")
	public String logoutProcess(HttpSession session) {
		String path = "redirect:../member/login_page.do";
		
		session.invalidate();
		
		System.out.println("[System] Logout");
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("join_page.do")
	public String joinPage() {
		String path = "user/member/join_page";
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("join_process.do")
	public String joinProcess(Model model, MemberVo memberParam) {
		String path = "user/member/join_complete_page";
		String password = CommonsMessageDigest.messageDigest(memberParam.getM_pw()); // 비밀번호 암호화
		
		memberParam.setM_pw(password); // 비밀번호 재설정
		this.memberService.joinMember(memberParam); // 회원가입
		
		model.addAttribute("userNick", memberParam.getM_nick()); // 닉네임 송신
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("auth_member.do")
	public String authMember(String auth_key) {
		String path = "user/member/auth_complete";
		
		this.memberService.authKey(auth_key);
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("update_profile_process.do")
	public String updateProfileProcess(HttpSession session, MemberVo memberParam) {
		String path = "redirect:../mypage/update_user_page.do";
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if(loginUser != null) 
		{
			
			memberParam.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no());
			this.memberService.updateProfile(memberParam);
			
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	@RequestMapping("find_id_page.do")
	public String findIdPage() {
		String path = "user/member/find_id_page";
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("find_id_process.do")
	public String findIdProcess(Model model, String inp_name, String inp_tel) {
		String path = null;
		MemberVo memberVo = this.memberService.findId(inp_name, inp_tel);
		
		if(memberVo != null) {
			
			path = "user/member/result_id_page";
			model.addAttribute("memberVo", memberVo);
			
		}else {
			
			path = "user/member/warning_user_page";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("find_pw_page.do")
	public String findPwPage() {
		String path = "user/member/find_pw_page";
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("find_pw_process.do")
	public String findPwProcess(Model model, String inp_id, String inp_email) {
		String path = null;
		MemberVo memberVo = this.memberService.findPw(inp_id, inp_email);
		
		if(memberVo != null) {
			
			path = "user/member/reset_pw_page";
			model.addAttribute("memberVo", memberVo);
			
		}else {
			
			path = "user/member/warning_user_page";
		}
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
	@RequestMapping("reset_pw_process.do")
	public String resetPwProcess(Model model, MemberVo memberParam) {
		String path = "user/member/reset_complete_page";
		
		String password = CommonsMessageDigest.messageDigest(memberParam.getM_pw()); // 패스워드 암호화
		memberParam.setM_pw(password);
		
		this.memberService.updatePassword(memberParam);
		model.addAttribute("userNick", memberParam.getM_nick());
		
		System.out.println("[Move] " + path);
		
		return path;
	}
	
}
