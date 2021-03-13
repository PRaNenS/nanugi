package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class DonateDVo {
	private int dd_no;
	private int mst_d_no;
	private int m_no;
	private int dd_donate_point;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dd_donate_date;
	
	public DonateDVo() {
		super();
	}
	
	public DonateDVo(int dd_no, int mst_d_no, int m_no, int dd_donate_point, Date dd_donate_date) {
		super();
		this.dd_no = dd_no;
		this.mst_d_no = mst_d_no;
		this.m_no = m_no;
		this.dd_donate_point = dd_donate_point;
		this.dd_donate_date = dd_donate_date;
	}
	
	public int getDd_no() {
		return dd_no;
	}
	public void setDd_no(int dd_no) {
		this.dd_no = dd_no;
	}
	public int getMst_d_no() {
		return mst_d_no;
	}
	public void setMst_d_no(int mst_d_no) {
		this.mst_d_no = mst_d_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public int getDd_donate_point() {
		return dd_donate_point;
	}
	public void setDd_donate_point(int dd_donate_point) {
		this.dd_donate_point = dd_donate_point;
	}
	public Date getDd_donate_date() {
		return dd_donate_date;
	}
	public void setDd_donate_date(Date dd_donate_date) {
		this.dd_donate_date = dd_donate_date;
	}
}
