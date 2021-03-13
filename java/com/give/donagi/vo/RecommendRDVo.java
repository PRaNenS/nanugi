package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class RecommendRDVo {
	private int rrd_no;
	private int rd_no;
	private int m_no;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rrd_date;
	
	public RecommendRDVo() {
		super();
	}
	
	public RecommendRDVo(int rrd_no, int rd_no, int m_no, Date rrd_date) {
		super();
		this.rrd_no = rrd_no;
		this.rd_no = rd_no;
		this.m_no = m_no;
		this.rrd_date = rrd_date;
	}
	
	public int getRrd_no() {
		return rrd_no;
	}
	public void setRrd_no(int rrd_no) {
		this.rrd_no = rrd_no;
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
	public Date getRrd_date() {
		return rrd_date;
	}
	public void setRrd_date(Date rrd_date) {
		this.rrd_date = rrd_date;
	}
}
