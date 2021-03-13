package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class MLimitVo {
	private int m_no;
	private String ml_limit_flg;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ml_date;
	
	public MLimitVo() {
		super();
	}
	
	public MLimitVo(int m_no, String ml_limit_flg, Date ml_date) {
		super();
		this.m_no = m_no;
		this.ml_limit_flg = ml_limit_flg;
		this.ml_date = ml_date;
	}
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getMl_limit_flg() {
		return ml_limit_flg;
	}
	public void setMl_limit_flg(String ml_limit_flg) {
		this.ml_limit_flg = ml_limit_flg;
	}
	public Date getMl_date() {
		return ml_date;
	}
	public void setMl_date(Date ml_date) {
		this.ml_date = ml_date;
	}
}
