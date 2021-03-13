package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class MSTDonateVo {
	private int mst_d_no;
	private int mst_dc_no;
	private int m_no;
	private String mst_d_title;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mst_d_date_from;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mst_d_date_to;
	private int mst_d_goal;
	private String mst_d_content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mst_d_create_date;
	
	public MSTDonateVo() {
		super();
	}
	
	public MSTDonateVo(int mst_d_no, int mst_dc_no, int m_no, String mst_d_title, Date mst_d_date_from,
			Date mst_d_date_to, int mst_d_goal, String mst_d_content, Date mst_d_create_date) {
		super();
		this.mst_d_no = mst_d_no;
		this.mst_dc_no = mst_dc_no;
		this.m_no = m_no;
		this.mst_d_title = mst_d_title;
		this.mst_d_date_from = mst_d_date_from;
		this.mst_d_date_to = mst_d_date_to;
		this.mst_d_goal = mst_d_goal;
		this.mst_d_content = mst_d_content;
		this.mst_d_create_date = mst_d_create_date;
	}
	
	public int getMst_d_no() {
		return mst_d_no;
	}
	public void setMst_d_no(int mst_d_no) {
		this.mst_d_no = mst_d_no;
	}
	public int getMst_dc_no() {
		return mst_dc_no;
	}
	public void setMst_dc_no(int mst_dc_no) {
		this.mst_dc_no = mst_dc_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getMst_d_title() {
		return mst_d_title;
	}
	public void setMst_d_title(String mst_d_title) {
		this.mst_d_title = mst_d_title;
	}
	public Date getMst_d_date_from() {
		return mst_d_date_from;
	}
	public void setMst_d_date_from(Date mst_d_date_from) {
		this.mst_d_date_from = mst_d_date_from;
	}
	public Date getMst_d_date_to() {
		return mst_d_date_to;
	}
	public void setMst_d_date_to(Date mst_d_date_to) {
		this.mst_d_date_to = mst_d_date_to;
	}
	public int getMst_d_goal() {
		return mst_d_goal;
	}
	public void setMst_d_goal(int mst_d_goal) {
		this.mst_d_goal = mst_d_goal;
	}
	public String getMst_d_content() {
		return mst_d_content;
	}
	public void setMst_d_content(String mst_d_content) {
		this.mst_d_content = mst_d_content;
	}
	public Date getMst_d_create_date() {
		return mst_d_create_date;
	}
	public void setMst_d_create_date(Date mst_d_create_date) {
		this.mst_d_create_date = mst_d_create_date;
	}
}
