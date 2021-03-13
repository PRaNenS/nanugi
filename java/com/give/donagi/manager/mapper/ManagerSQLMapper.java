package com.give.donagi.manager.mapper;

import java.util.ArrayList;

import com.give.donagi.vo.BoardFreeVo;
import com.give.donagi.vo.MLimitVo;
import com.give.donagi.vo.MSTMTypeVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.ReviewFundingVo;
import com.give.donagi.vo.ReviewVolunteerVo;

public interface ManagerSQLMapper 
{
	public BoardFreeVo selectfreeByBFNo(int bf_no);
	
	public ReviewVolunteerVo selectVReviewByRVNo(int rv_no);
	
	public ReviewFundingVo selectFunReviewByRERFNo(int rf_no);
	
	public void insetLimit(MLimitVo limitvo);
	
	public int LimitFlg(int m_no);
	
	public MLimitVo selectLimtByMNo(int m_no);
	
	public ArrayList<MLimitVo> selectLimitAll();
	
	public void deleteLimit(int m_no);
	
	public ArrayList<MemberVo> selectMember();
	
	public MSTMTypeVo memberType(int mst_mt_no);
	
	int  memberStatus(int m_no);
	
	int isPenalty(int m_no);
	
}
