package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class ReportRFVo {
	private int rerf_no;
	private int rf_no;
	private int m_no;
	private String rerf_content;
	private int rerf_flg;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rerf_date;
	
	public ReportRFVo() {
		super();
	}
	
	public ReportRFVo(int rerf_no, int rf_no, int m_no, String rerf_content, int rerf_flg, Date rerf_date) {
		super();
		this.rerf_no = rerf_no;
		this.rf_no = rf_no;
		this.m_no = m_no;
		this.rerf_content = rerf_content;
		this.rerf_flg = rerf_flg;
		this.rerf_date = rerf_date;
	}
	
	public int getRerf_no() {
		return rerf_no;
	}
	public void setRerf_no(int rerf_no) {
		this.rerf_no = rerf_no;
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
	public String getRerf_content() {
		return rerf_content;
	}
	public void setRerf_content(String rerf_content) {
		this.rerf_content = rerf_content;
	}
	public int getRerf_flg() {
		return rerf_flg;
	}
	public void setRerf_flg(int rerf_flg) {
		this.rerf_flg = rerf_flg;
	}
	public Date getRerf_date() {
		return rerf_date;
	}
	public void setRerf_date(Date rerf_date) {
		this.rerf_date = rerf_date;
	}
}
