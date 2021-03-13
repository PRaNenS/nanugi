package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class ReviewDonateVo {
	private int rd_no;
	private int dd_no;
	private int m_no;
	private String rd_title;
	private String rd_content;
	private int rd_score;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rd_write_date;
	
	public ReviewDonateVo() {
		super();
	}
	
	public ReviewDonateVo(int rd_no, int dd_no, int m_no, String rd_title, String rd_content, int rd_score,
			Date rd_write_date) {
		super();
		this.rd_no = rd_no;
		this.dd_no = dd_no;
		this.m_no = m_no;
		this.rd_title = rd_title;
		this.rd_content = rd_content;
		this.rd_score = rd_score;
		this.rd_write_date = rd_write_date;
	}
	
	public int getRd_no() {
		return rd_no;
	}
	public void setRd_no(int rd_no) {
		this.rd_no = rd_no;
	}
	public int getDd_no() {
		return dd_no;
	}
	public void setDd_no(int dd_no) {
		this.dd_no = dd_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getRd_title() {
		return rd_title;
	}
	public void setRd_title(String rd_title) {
		this.rd_title = rd_title;
	}
	public String getRd_content() {
		return rd_content;
	}
	public void setRd_content(String rd_content) {
		this.rd_content = rd_content;
	}
	public int getRd_score() {
		return rd_score;
	}
	public void setRd_score(int rd_score) {
		this.rd_score = rd_score;
	}
	public Date getRd_write_date() {
		return rd_write_date;
	}
	public void setRd_write_date(Date rd_write_date) {
		this.rd_write_date = rd_write_date;
	}
}
