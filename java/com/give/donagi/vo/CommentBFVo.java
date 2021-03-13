package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class CommentBFVo {
	private int cbf_no;
	private int bf_no;
	private int m_no;
	private String cbf_content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cbf_write_date;
	
	public CommentBFVo() {
		super();
	}
	
	public CommentBFVo(int cbf_no, int bf_no, int m_no, String cbf_content, Date cbf_write_date) {
		super();
		this.cbf_no = cbf_no;
		this.bf_no = bf_no;
		this.m_no = m_no;
		this.cbf_content = cbf_content;
		this.cbf_write_date = cbf_write_date;
	}
	
	public int getCbf_no() {
		return cbf_no;
	}
	public void setCbf_no(int cbf_no) {
		this.cbf_no = cbf_no;
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
	public String getCbf_content() {
		return cbf_content;
	}
	public void setCbf_content(String cbf_content) {
		this.cbf_content = cbf_content;
	}
	public Date getCbf_write_date() {
		return cbf_write_date;
	}
	public void setCbf_write_date(Date cbf_write_date) {
		this.cbf_write_date = cbf_write_date;
	}
}
