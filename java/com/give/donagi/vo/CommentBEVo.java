package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class CommentBEVo {
	private int cbe_no;
	private int be_no;
	private int m_no;
	private String cbe_content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cbe_write_date;
	
	public CommentBEVo() {
		super();
	}
	
	public CommentBEVo(int cbe_no, int be_no, int m_no, String cbe_content, Date cbe_write_date) {
		super();
		this.cbe_no = cbe_no;
		this.be_no = be_no;
		this.m_no = m_no;
		this.cbe_content = cbe_content;
		this.cbe_write_date = cbe_write_date;
	}
	
	public int getCbe_no() {
		return cbe_no;
	}
	public void setCbe_no(int cbe_no) {
		this.cbe_no = cbe_no;
	}
	public int getBe_no() {
		return be_no;
	}
	public void setBe_no(int be_no) {
		this.be_no = be_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getCbe_content() {
		return cbe_content;
	}
	public void setCbe_content(String cbe_content) {
		this.cbe_content = cbe_content;
	}
	public Date getCbe_write_date() {
		return cbe_write_date;
	}
	public void setCbe_write_date(Date cbe_write_date) {
		this.cbe_write_date = cbe_write_date;
	}
}
