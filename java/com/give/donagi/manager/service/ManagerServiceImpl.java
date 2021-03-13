package com.give.donagi.manager.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.aspectj.weaver.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.give.donagi.manager.mapper.ManagerSQLMapper;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.vo.BoardFreeVo;
import com.give.donagi.vo.MLimitVo;
import com.give.donagi.vo.MRequestComVo;
import com.give.donagi.vo.MSTMTypeVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.ReviewFundingVo;
import com.give.donagi.vo.ReviewVolunteerVo;

@Service
public class ManagerServiceImpl 
{
	@Autowired
	private UserMemberSQLMapper memberSQLMapper;
	
	@Autowired
	private ManagerSQLMapper managerSQLMapper;
	
	public ArrayList<HashMap<String, Object>> UpdateUser()
	{
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		
		ArrayList<MRequestComVo> updateList = memberSQLMapper.selectUpdateUser();
		
		for(MRequestComVo mrequestvo : updateList)
		{
			int m_no = mrequestvo.getM_no();
			MemberVo membervo = memberSQLMapper.selectByNo(m_no);
			
			CommonsDateConverter dateConvert = new CommonsDateConverter();
		 	 
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("request_date", dateConvert.getDate(mrequestvo.getMrc_request_date()));
			map.put("membervo", membervo); 
		 	map.put("mrequestvo", mrequestvo);
		 	
		 	resultList.add(map);
		}
		
		System.out.println("System log [getUpdateList]");
		
		return resultList;
	}
	
	public void deleteRequest(int mrc_no)
	{
		this.memberSQLMapper.deleteRequest(mrc_no);
	}
	
	public void approvedRequest(int m_no)
	{
		MRequestComVo requestvo = memberSQLMapper.selectRequstByBNo(m_no);
		MemberVo vo =  new MemberVo();
		
		vo.setM_no(m_no);
		vo.setMst_mt_no(3);
		vo.setM_uniqnum(requestvo.getMrc_uniqnum());
		vo.setM_tel(requestvo.getMrc_tel());
		vo.setM_nick(requestvo.getMrc_nick());
		vo.setM_name(requestvo.getMrc_name());
		vo.setM_birth(requestvo.getMrc_birth());
		vo.setM_address1(requestvo.getMrc_address1());
		vo.setM_address2(requestvo.getMrc_address2());
		vo.setM_zip(requestvo.getMrc_zip());
		
		this.memberSQLMapper.updateRMember(vo);
		
		this.memberSQLMapper.updateApprovedFlg(m_no);
	}
	
	public void FpenaltyProcess(int bf_no)
	{
		BoardFreeVo freeboardvo = this.managerSQLMapper.selectfreeByBFNo(bf_no);
		
		int m_no = freeboardvo.getM_no();
		
		MLimitVo limitvo = new MLimitVo();
		limitvo.setM_no(m_no);
		
		this.managerSQLMapper.insetLimit(limitvo);
	}
	
	public void VpenaltyProcess(int rv_no)
	{
		ReviewVolunteerVo volunteervo = this.managerSQLMapper.selectVReviewByRVNo(rv_no);
		
		int m_no = volunteervo.getM_no();
		
		MLimitVo limitvo = new MLimitVo();
		limitvo.setM_no(m_no);
		
		this.managerSQLMapper.insetLimit(limitvo);
	}
	
	public int limitFlg(int m_no) // 제제 가한 유저 로그인 방지 
	{
		return this.managerSQLMapper.LimitFlg(m_no);
	}
	public MLimitVo selectlimit(int m_no)
	{
		return this.managerSQLMapper.selectLimtByMNo(m_no);
	}
	
	public void FunpenaltyProcess(int rf_no)
	{
		ReviewFundingVo fundingvo = this.managerSQLMapper.selectFunReviewByRERFNo(rf_no);
		
		int m_no = fundingvo.getM_no();
		
		MLimitVo limitvo = new MLimitVo();
		limitvo.setM_no(m_no);
		
		this.managerSQLMapper.insetLimit(limitvo);
	}
	
	public ArrayList<HashMap<String, Object>> MemberInfo()
	{
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		ArrayList<MemberVo> memberList =  this.managerSQLMapper.selectMember();
		
		CommonsDateConverter dateConvert = new CommonsDateConverter();
		for(MemberVo member : memberList)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			int mst_mt_no = member.getMst_mt_no();
			
			MSTMTypeVo typevo = managerSQLMapper.memberType(mst_mt_no);
			
			int m_no = member.getM_no();
			
			int limit = managerSQLMapper.memberStatus(m_no);
			
			map.put("member", member);
			map.put("typevo", typevo);
			map.put("limit", limit);
			map.put("date", dateConvert.getDate(member.getM_create_date()));
			resultList.add(map);
		}
		
		return resultList;
			
	}
}
