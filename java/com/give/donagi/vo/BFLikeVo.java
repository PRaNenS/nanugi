package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class BFLikeVo {
	private int bfl_no;
	private int bf_no;
	private int m_no;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bfl_date;
	
	public BFLikeVo() {
		super();
	}
	
	public BFLikeVo(int bfl_no, int bf_no, int m_no, Date bfl_date) {
		super();
		this.bfl_no = bfl_no;
		this.bf_no = bf_no;
		this.m_no = m_no;
		this.bfl_date = bfl_date;
	}
	
	public int getBfl_no() {
		return bfl_no;
	}
	public void setBfl_no(int bfl_no) {
		this.bfl_no = bfl_no;
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
	public Date getBfl_date() {
		return bfl_date;
	}
	public void setBfl_date(Date bfl_date) {
		this.bfl_date = bfl_date;
	}
}
