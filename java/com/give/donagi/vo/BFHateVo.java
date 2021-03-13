package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class BFHateVo {
	private int bfh_no;
	private int bf_no;
	private int m_no;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bfh_date;
	
	public BFHateVo() {
		super();
	}
	
	public BFHateVo(int bfh_no, int bf_no, int m_no, Date bfh_date) {
		super();
		this.bfh_no = bfh_no;
		this.bf_no = bf_no;
		this.m_no = m_no;
		this.bfh_date = bfh_date;
	}
	
	public int getBfh_no() {
		return bfh_no;
	}
	public void setBfh_no(int bfh_no) {
		this.bfh_no = bfh_no;
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
	public Date getBfh_date() {
		return bfh_date;
	}
	public void setBfh_date(Date bfh_date) {
		this.bfh_date = bfh_date;
	}
}
