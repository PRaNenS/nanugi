package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class QuestionVo {
	private int q_no;
	private int m_no;
	private String q_title;
	private String q_content;
	private int q_flg;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date q_date;
	
	public QuestionVo() {
		super();
	}
	
	public QuestionVo(int q_no, int m_no, String q_title, String q_content, int q_flg, Date q_date) {
		super();
		this.q_no = q_no;
		this.m_no = m_no;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_flg = q_flg;
		this.q_date = q_date;
	}
	
	public int getQ_no() {
		return q_no;
	}
	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getQ_title() {
		return q_title;
	}
	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}
	public String getQ_content() {
		return q_content;
	}
	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}
	public int getQ_flg() {
		return q_flg;
	}
	public void setQ_flg(int q_flg) {
		this.q_flg = q_flg;
	}
	public Date getQ_date() {
		return q_date;
	}
	public void setQ_date(Date q_date) {
		this.q_date = q_date;
	}
}
