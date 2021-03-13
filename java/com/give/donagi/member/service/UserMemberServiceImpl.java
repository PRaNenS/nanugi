package com.give.donagi.member.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.util.*;
import com.give.donagi.vo.*;

@Service
public class UserMemberServiceImpl {
	@Autowired
	private UserMemberSQLMapper memberSQLMapper;
	@Autowired
	private JavaMailSender mailSender;
	
	// ID 중복 확인
	public boolean existId(String check_id) {
		MemberVo memberVo = this.memberSQLMapper.existId(check_id); // ID 중복 확인
		boolean result = false; // ID 중복 확인 결과
		
		if(memberVo != null) {
			result = true;
			
		}
		
		return result;
	}
	
	// 회원가입
	public void joinMember(MemberVo memberParam) {
		int memberNo = this.memberSQLMapper.createMNo();
		
		memberParam.setM_no(memberNo); // 유저정보에 유저번호 설정

		this.memberSQLMapper.joinMember(memberParam); // 유저정보 생성
		this.memberSQLMapper.joinMPoint(memberNo); // 유저 포인트 생성
		
		// 인증키
		String authKey = UUID.randomUUID().toString();
		
		MAuthVo mAuthVo = new MAuthVo();
		mAuthVo.setM_no(memberNo);
		mAuthVo.setMa_auth_key(authKey);
		
		this.memberSQLMapper.insertMAuth(mAuthVo);
		
		// 인증 이메일
		MailSendThread mailSendThread = new MailSendThread(authKey, memberParam.getM_id(), this.mailSender);
		mailSendThread.start();

		System.out.println("[System] joinMember");
	}
	
	// 인증완료
	public void authKey(String auth_key) {
		
		this.memberSQLMapper.updateMAuth(auth_key);
	}
	
	// 로그인 전 확인
	public MemberVo checkLogin(String checkId, String checkPw) {
		MemberVo checkParam = new MemberVo();
		MemberVo result = null;
		
		checkParam.setM_id(checkId);
		checkParam.setM_pw(checkPw);
		
		result = this.memberSQLMapper.loginMember(checkParam);
		
		return result;
	}

	// 로그인
	public HashMap<String, Object> login(MemberVo memberParam) {
		MemberVo memberVo = null;
		HashMap<String, Object> loginUserData = null; // 로그인 유저 데이터
		
		memberVo = this.memberSQLMapper.loginMember(memberParam); //로그인 시도
		
		if(memberVo != null) {
			MSTMTypeVo mstMTypeVo = this.memberSQLMapper.selectUserType(memberVo.getMst_mt_no()); // 회원상태 정보
			
			// 회원 정보 넣기
			loginUserData = new HashMap<String, Object>();
			loginUserData.put("memberVo", memberVo);
			loginUserData.put("mstMTypeVo", mstMTypeVo);
		}
		
		System.out.println("[System] Select LoginUser");
		
		return loginUserData;
	}
	
	// 닉네임 검색
	public int searchMemberNo(String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int result = 0;
		
		map.put("keyword", keyword);
		Integer tempResult = this.memberSQLMapper.searchNoByNick(map);
		
		if(tempResult != null) {
			result = tempResult.intValue();
		}
		
		System.out.println("[System] searchMemberNo");
		
		return result;
	}
	
	// 회원번호로 유저정보 조회
	public MemberVo selectMemberByNo(int m_no) {
		
		System.out.println("[System] selectMemberByNo");
		
		return this.memberSQLMapper.selectByNo(m_no);
	}
	
	// 유저 비밀번호 변경
	public void updatePassword(MemberVo memberVo) {
		
		this.memberSQLMapper.updatePassword(memberVo);
		
		System.out.println("[System] updatePassword");
	}
	
	// 유저 정보 변경
	public void updateUserData(MemberVo memberVo) {
		
		this.memberSQLMapper.updateUser(memberVo);
		
		System.out.println("[System] updateUserData");
	}
	
	public void updateProfile(MemberVo memberVo) {
		
		this.memberSQLMapper.updateProfile(memberVo);
		
		System.out.println("[System] updateProfile");
	}
	
	// 아이디 찾기
	public MemberVo findId(String inpName, String inpTel) {
		
		MemberVo memberVo = new MemberVo();
		
		memberVo.setM_name(inpName);
		memberVo.setM_tel(inpTel);
		
		System.out.println("[System] findId");
		
		return this.memberSQLMapper.selectUserForId(memberVo);
	}
	
	// 비밀번호 찾기
	public MemberVo findPw(String inpId, String inpEmail) {
		
		MemberVo memberVo = new MemberVo();
		
		memberVo.setM_id(inpId);
		memberVo.setM_email(inpEmail);
		
		System.out.println("[System] findPw");
		
		return this.memberSQLMapper.selectUserForPw(memberVo);
	}
}
