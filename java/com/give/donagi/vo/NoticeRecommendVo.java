package com.give.donagi.vo;

public class NoticeRecommendVo 
{
	private int bnr_no;
	private int m_no;
	private int bn_no;
	
	public NoticeRecommendVo() 
	{
		super();
	}
	public NoticeRecommendVo(int bnr_no, int m_no, int bn_no) 
	{
		super();
		this.bnr_no = bnr_no;
		this.m_no = m_no;
		this.bn_no = bn_no;
	}
	public int getBnr_no() 
	{
		return bnr_no;
	}
	public void setBnr_no(int bnr_no) 
	{
		this.bnr_no = bnr_no;
	}
	public int getM_no() 
	{
		return m_no;
	}
	public void setM_no(int m_no) 
	{
		this.m_no = m_no;
	}
	public int getBn_no() 
	{
		return bn_no;
	}
	public void setBn_no(int bn_no) 
	{
		this.bn_no = bn_no;
	}
	
}
