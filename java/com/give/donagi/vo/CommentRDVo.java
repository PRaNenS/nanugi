package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class CommentRDVo {
	private int crd_no;
	private int rd_no;
	private int m_no;
	private String crd_content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date crd_write_date;
	
	public CommentRDVo() {
		super();
	}
	
	public CommentRDVo(int crd_no, int rd_no, int m_no, String crd_content, Date crd_write_date) {
		super();
		this.crd_no = crd_no;
		this.rd_no = rd_no;
		this.m_no = m_no;
		this.crd_content = crd_content;
		this.crd_write_date = crd_write_date;
	}
	
	public int getCrd_no() {
		return crd_no;
	}
	public void setCrd_no(int crd_no) {
		this.crd_no = crd_no;
	}
	public int getRd_no() {
		return rd_no;
	}
	public void setRd_no(int rd_no) {
		this.rd_no = rd_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getCrd_content() {
		return crd_content;
	}
	public void setCrd_content(String crd_content) {
		this.crd_content = crd_content;
	}
	public Date getCrd_write_date() {
		return crd_write_date;
	}
	public void setCrd_write_date(Date crd_write_date) {
		this.crd_write_date = crd_write_date;
	}
}
