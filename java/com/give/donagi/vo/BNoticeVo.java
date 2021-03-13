package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class BNoticeVo {
	private int bn_no;
	private int m_no;
	private String bn_title;
	private String bn_content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bn_write_date;
	
	public BNoticeVo() {
		super();
	}
	
	public BNoticeVo(int bn_no, int m_no, String bn_title, String bn_content, Date bn_write_date) {
		super();
		this.bn_no = bn_no;
		this.m_no = m_no;
		this.bn_title = bn_title;
		this.bn_content = bn_content;
		this.bn_write_date = bn_write_date;
	}
	
	public int getBn_no() {
		return bn_no;
	}
	public void setBn_no(int bn_no) {
		this.bn_no = bn_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getBn_title() {
		return bn_title;
	}
	public void setBn_title(String bn_title) {
		this.bn_title = bn_title;
	}
	public String getBn_content() {
		return bn_content;
	}
	public void setBn_content(String bn_content) {
		this.bn_content = bn_content;
	}
	public Date getBn_write_date() {
		return bn_write_date;
	}
	public void setBn_write_date(Date bn_write_date) {
		this.bn_write_date = bn_write_date;
	}
}
