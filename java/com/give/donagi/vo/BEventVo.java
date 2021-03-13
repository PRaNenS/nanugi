package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class BEventVo {
	private int be_no;
	private int m_no;
	private String be_title;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date be_date_from;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date be_date_to;
	private String be_content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date be_create_date;
	
	public BEventVo() {
		super();
	}
	
	public BEventVo(int be_no, int m_no, String be_title, Date be_date_from, Date be_date_to, String be_content,
			Date be_create_date) {
		super();
		this.be_no = be_no;
		this.m_no = m_no;
		this.be_title = be_title;
		this.be_date_from = be_date_from;
		this.be_date_to = be_date_to;
		this.be_content = be_content;
		this.be_create_date = be_create_date;
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
	public String getBe_title() {
		return be_title;
	}
	public void setBe_title(String be_title) {
		this.be_title = be_title;
	}
	public Date getBe_date_from() {
		return be_date_from;
	}
	public void setBe_date_from(Date be_date_from) {
		this.be_date_from = be_date_from;
	}
	public Date getBe_date_to() {
		return be_date_to;
	}
	public void setBe_date_to(Date be_date_to) {
		this.be_date_to = be_date_to;
	}
	public String getBe_content() {
		return be_content;
	}
	public void setBe_content(String be_content) {
		this.be_content = be_content;
	}
	public Date getBe_create_date() {
		return be_create_date;
	}
	public void setBe_create_date(Date be_create_date) {
		this.be_create_date = be_create_date;
	}
}
