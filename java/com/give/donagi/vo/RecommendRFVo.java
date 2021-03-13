package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class RecommendRFVo {
	private int rrf_no;
	private int rf_no;
	private int m_no;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rrf_date;
	
	public RecommendRFVo() {
		super();
	}
	
	public RecommendRFVo(int rrf_no, int rf_no, int m_no, Date rrf_date) {
		super();
		this.rrf_no = rrf_no;
		this.rf_no = rf_no;
		this.m_no = m_no;
		this.rrf_date = rrf_date;
	}
	
	public int getRrf_no() {
		return rrf_no;
	}
	public void setRrf_no(int rrf_no) {
		this.rrf_no = rrf_no;
	}
	public int getRf_no() {
		return rf_no;
	}
	public void setRf_no(int rf_no) {
		this.rf_no = rf_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public Date getRrf_date() {
		return rrf_date;
	}
	public void setRrf_date(Date rrf_date) {
		this.rrf_date = rrf_date;
	}
}
