package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class ReportRVVo {
	private int rerv_no;
	private int rv_no;
	private int m_no;
	private String rerv_content;
	private int rerv_flg;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rerv_date;
	
	public ReportRVVo() {
		super();
	}
	
	public ReportRVVo(int rerv_no, int rv_no, int m_no, String rerv_content, int rerv_flg, Date rerv_date) {
		super();
		this.rerv_no = rerv_no;
		this.rv_no = rv_no;
		this.m_no = m_no;
		this.rerv_content = rerv_content;
		this.rerv_flg = rerv_flg;
		this.rerv_date = rerv_date;
	}
	
	public int getRerv_no() {
		return rerv_no;
	}
	public void setRerv_no(int rerv_no) {
		this.rerv_no = rerv_no;
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
	public String getRerv_content() {
		return rerv_content;
	}
	public void setRerv_content(String rerv_content) {
		this.rerv_content = rerv_content;
	}
	public int getRerv_flg() {
		return rerv_flg;
	}
	public void setRerv_flg(int rerv_flg) {
		this.rerv_flg = rerv_flg;
	}
	public Date getRerv_date() {
		return rerv_date;
	}
	public void setRerv_date(Date rerv_date) {
		this.rerv_date = rerv_date;
	}
}
