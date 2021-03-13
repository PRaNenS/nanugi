package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class CommentRFVo {
	private int crf_no;
	private int rf_no;
	private int m_no;
	private String crf_content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date crf_write_date;
	
	public CommentRFVo() {
		super();
	}
	
	public CommentRFVo(int crf_no, int rf_no, int m_no, String crf_content, Date crf_write_date) {
		super();
		this.crf_no = crf_no;
		this.rf_no = rf_no;
		this.m_no = m_no;
		this.crf_content = crf_content;
		this.crf_write_date = crf_write_date;
	}
	
	public int getCrf_no() {
		return crf_no;
	}
	public void setCrf_no(int crf_no) {
		this.crf_no = crf_no;
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
	public String getCrf_content() {
		return crf_content;
	}
	public void setCrf_content(String crf_content) {
		this.crf_content = crf_content;
	}
	public Date getCrf_write_date() {
		return crf_write_date;
	}
	public void setCrf_write_date(Date crf_write_date) {
		this.crf_write_date = crf_write_date;
	}
}
