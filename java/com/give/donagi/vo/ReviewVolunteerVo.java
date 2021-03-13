package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class ReviewVolunteerVo {
	private int rv_no;
	private int jv_no;
	private int m_no;
	private String rv_title;
	private String rv_content;
	private int rv_score;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rv_write_date;
	
	public ReviewVolunteerVo() {
		super();
	}
	
	public ReviewVolunteerVo(int rv_no, int jv_no, int m_no, String rv_title, String rv_content, int rv_score,
			Date rv_write_date) {
		super();
		this.rv_no = rv_no;
		this.jv_no = jv_no;
		this.m_no = m_no;
		this.rv_title = rv_title;
		this.rv_content = rv_content;
		this.rv_score = rv_score;
		this.rv_write_date = rv_write_date;
	}
	
	public int getRv_no() {
		return rv_no;
	}
	public void setRv_no(int rv_no) {
		this.rv_no = rv_no;
	}
	public int getJv_no() {
		return jv_no;
	}
	public void setJv_no(int jv_no) {
		this.jv_no = jv_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getRv_title() {
		return rv_title;
	}
	public void setRv_title(String rv_title) {
		this.rv_title = rv_title;
	}
	public String getRv_content() {
		return rv_content;
	}
	public void setRv_content(String rv_content) {
		this.rv_content = rv_content;
	}
	public int getRv_score() {
		return rv_score;
	}
	public void setRv_score(int rv_score) {
		this.rv_score = rv_score;
	}
	public Date getRv_write_date() {
		return rv_write_date;
	}
	public void setRv_write_date(Date rv_write_date) {
		this.rv_write_date = rv_write_date;
	}
}
