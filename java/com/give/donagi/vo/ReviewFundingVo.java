package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class ReviewFundingVo {
	private int rf_no;
	private int m_no;
	private int of_no;
	private String rf_title;
	private String rf_content;
	private int rf_score;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rf_write_date;
	
	public ReviewFundingVo() {
		super();
	}
	
	public ReviewFundingVo(int rf_no, int m_no, int of_no, String rf_title, String rf_content, int rf_score,
			Date rf_write_date) {
		super();
		this.rf_no = rf_no;
		this.m_no = m_no;
		this.of_no = of_no;
		this.rf_title = rf_title;
		this.rf_content = rf_content;
		this.rf_score = rf_score;
		this.rf_write_date = rf_write_date;
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
	public int getOf_no() {
		return of_no;
	}
	public void setOf_no(int of_no) {
		this.of_no = of_no;
	}
	public String getRf_title() {
		return rf_title;
	}
	public void setRf_title(String rf_title) {
		this.rf_title = rf_title;
	}
	public String getRf_content() {
		return rf_content;
	}
	public void setRf_content(String rf_content) {
		this.rf_content = rf_content;
	}
	public int getRf_score() {
		return rf_score;
	}
	public void setRf_score(int rf_score) {
		this.rf_score = rf_score;
	}
	public Date getRf_write_date() {
		return rf_write_date;
	}
	public void setRf_write_date(Date rf_write_date) {
		this.rf_write_date = rf_write_date;
	}
}
