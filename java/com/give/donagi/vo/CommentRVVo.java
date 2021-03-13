package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class CommentRVVo {
	private int crv_no;
	private int rv_no;
	private int m_no;
	private String crv_content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date crv_write_date;
	
	public CommentRVVo() {
		super();
	}
	
	public CommentRVVo(int crv_no, int rv_no, int m_no, String crv_content, Date crv_write_date) {
		super();
		this.crv_no = crv_no;
		this.rv_no = rv_no;
		this.m_no = m_no;
		this.crv_content = crv_content;
		this.crv_write_date = crv_write_date;
	}
	
	public int getCrv_no() {
		return crv_no;
	}
	public void setCrv_no(int crv_no) {
		this.crv_no = crv_no;
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
	public String getCrv_content() {
		return crv_content;
	}
	public void setCrv_content(String crv_content) {
		this.crv_content = crv_content;
	}
	public Date getCrv_write_date() {
		return crv_write_date;
	}
	public void setCrv_write_date(Date crv_write_date) {
		this.crv_write_date = crv_write_date;
	}
}
