package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class RecommendRVVo {
	private int rrv_no;
	private int rv_no;
	private int m_no;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rrv_date;
	
	public RecommendRVVo() {
		super();
	}
	
	public RecommendRVVo(int rrv_no, int rv_no, int m_no, Date rrv_date) {
		super();
		this.rrv_no = rrv_no;
		this.rv_no = rv_no;
		this.m_no = m_no;
		this.rrv_date = rrv_date;
	}
	
	public int getRrv_no() {
		return rrv_no;
	}
	public void setRrv_no(int rrv_no) {
		this.rrv_no = rrv_no;
	}
	public int getRv_no() {
		return rv_no;
	}
	public void setRv_no(int rv_no) {
		this.rv_no = rv_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public Date getRrv_date() {
		return rrv_date;
	}
	public void setRrv_date(Date rrv_date) {
		this.rrv_date = rrv_date;
	}
}
