package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class BoardFreeVo {
	private int bf_no;
	private int m_no;
	private int mst_bfh_no;
	private String bf_title;
	private String bf_content;
	private int bf_viewcount;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bf_write_date;
	
	public BoardFreeVo() {
		super();
	}
	
	public BoardFreeVo(int bf_no, int m_no, int mst_bfh_no, String bf_title, String bf_content, int bf_viewcount, Date bf_write_date) {
		super();
		this.bf_no = bf_no;
		this.m_no = m_no;
		this.mst_bfh_no = mst_bfh_no;
		this.bf_title = bf_title;
		this.bf_content = bf_content;
		this.bf_viewcount = bf_viewcount;
		this.bf_write_date = bf_write_date;
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
	public int getMst_bfh_no() {
		return mst_bfh_no;
	}
	public void setMst_bfh_no(int mst_bfh_no) {
		this.mst_bfh_no = mst_bfh_no;
	}
	public String getBf_title() {
		return bf_title;
	}
	public void setBf_title(String bf_title) {
		this.bf_title = bf_title;
	}
	public String getBf_content() {
		return bf_content;
	}
	public void setBf_content(String bf_content) {
		this.bf_content = bf_content;
	}
	public int getBf_viewcount() {
		return bf_viewcount;
	}
	public void setBf_viewcount(int bf_viewcount) {
		this.bf_viewcount = bf_viewcount;
	}
	public Date getBf_write_date() {
		return bf_write_date;
	}
	public void setBf_write_date(Date bf_write_date) {
		this.bf_write_date = bf_write_date;
	}
}
