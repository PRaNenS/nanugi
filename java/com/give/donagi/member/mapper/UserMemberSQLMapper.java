package com.give.donagi.member.mapper;

import java.util.*;
import com.give.donagi.vo.*;

public interface UserMemberSQLMapper {

	public int createMNo(); // m_no 생성
	public MemberVo existId(String m_id); // ID 중복 확인
	public void joinMember(MemberVo memberVo); // 회원가입
	public void insertMAuth(MAuthVo mAuthVo); // 인증키 생성
	public void updateMAuth(String auth_key); // 인증완료
	public void joinMPoint(int m_no); // 회원가입시 포인트 초기화
	public MemberVo loginMember(MemberVo memberVo); // 로그인
	public MPointVo selectPoint(int m_no); // 포인트 조회
	public MemberVo selectByNo(int m_no); // 회원번호로 회원정보 조회
	public MSTMTypeVo selectUserType(int mst_mt_no); // 유저 타입 조회
	public Integer searchNoByNick(HashMap<String, Object> map); // 닉네임으로 유저번호 조회
	public void updatePassword(MemberVo memberVo); // 비밀번호 변경
	public void updateUser(MemberVo memberVo); // 회원정보 변경
	public void updateProfile(MemberVo memberVo); // 프로필 변경
	public ArrayList<MRequestComVo> selectUpdateUser();
	public void deleteRequest(int mrc_no); //기관유저승인 요청 삭제
	public void updateApprovedFlg(int m_no); //승인 flg 변경
	public MRequestComVo selectRequstByBNo(int m_no);
	public void updateRMember(MemberVo memberVo);
	public MemberVo selectUserForId(MemberVo memberVo); // 아이디찾기
	public MemberVo selectUserForPw(MemberVo memberVo); // 비밀번호 찾기
}
