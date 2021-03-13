package com.give.donagi.vo;

import java.util.Date;

public class ReportBFVo {

	private int rbf_no;
	private int bf_no;
	private int m_no;
	private String rbf_content;
	private Date rbf_regdate;
	
	
	public ReportBFVo() {
		super();
	}


	public ReportBFVo(int rbf_no, int bf_no, int m_no, String rbf_content, Date rbf_regdate) {
		super();
		this.rbf_no = rbf_no;
		this.bf_no = bf_no;
		this.m_no = m_no;
		this.rbf_content = rbf_content;
		this.rbf_regdate = rbf_regdate;
	}


	public int getRbf_no() {
		return rbf_no;
	}


	public void setRbf_no(int rbf_no) {
		this.rbf_no = rbf_no;
	}


	public int getBf_no() {
		return bf_no;
	}


	public void setBf_no(int bf_no) {
		this.bf_no = bf_no;
	}


	public int getM_no() {
		return m_no;
	}


	public void setM_no(int m_no) {
		this.m_no = m_no;
	}


	public String getRbf_content() {
		return rbf_content;
	}


	public void setRbf_content(String rbf_content) {
		this.rbf_content = rbf_content;
	}


	public Date getRbf_regdate() {
		return rbf_regdate;
	}


	public void setRbf_regdate(Date rbf_reg_date) {
		this.rbf_regdate = rbf_reg_date;
	}
	
	
	
}
