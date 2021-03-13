package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class MSTVolunteerVo 
{
	private int mst_v_no;
	private int mst_vc_no;
	private int m_no;
	private String mst_v_title;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mst_v_from;	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mst_v_to;
	private int mst_v_goal;
	private String mst_v_place;
	private String mst_v_content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mst_v_create_date;
	
	public MSTVolunteerVo() {
		super();
	}

	public MSTVolunteerVo(int mst_v_no, int mst_vc_no, int m_no, String mst_v_title, Date mst_v_from, Date mst_v_to,
			int mst_v_goal, String mst_v_place, String mst_v_content, Date mst_v_create_date) {
		super();
		this.mst_v_no = mst_v_no;
		this.mst_vc_no = mst_vc_no;
		this.m_no = m_no;
		this.mst_v_title = mst_v_title;
		this.mst_v_from = mst_v_from;
		this.mst_v_to = mst_v_to;
		this.mst_v_goal = mst_v_goal;
		this.mst_v_place = mst_v_place;
		this.mst_v_content = mst_v_content;
		this.mst_v_create_date = mst_v_create_date;
	}

	public int getMst_v_no() {
		return mst_v_no;
	}

	public void setMst_v_no(int mst_v_no) {
		this.mst_v_no = mst_v_no;
	}

	public int getMst_vc_no() {
		return mst_vc_no;
	}

	public void setMst_vc_no(int mst_vc_no) {
		this.mst_vc_no = mst_vc_no;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public String getMst_v_title() {
		return mst_v_title;
	}

	public void setMst_v_title(String mst_v_title) {
		this.mst_v_title = mst_v_title;
	}

	public Date getmst_v_from() {
		return mst_v_from;
	}

	public void setmst_v_from(Date mst_v_from) {
		this.mst_v_from = mst_v_from;
	}

	public Date getmst_v_to() {
		return mst_v_to;
	}

	public void setmst_v_to(Date mst_v_to) {
		this.mst_v_to = mst_v_to;
	}

	public int getMst_v_goal() {
		return mst_v_goal;
	}

	public void setMst_v_goal(int mst_v_goal) {
		this.mst_v_goal = mst_v_goal;
	}

	public String getMst_v_place() {
		return mst_v_place;
	}

	public void setMst_v_place(String mst_v_place) {
		this.mst_v_place = mst_v_place;
	}

	public String getMst_v_content() {
		return mst_v_content;
	}

	public void setMst_v_content(String mst_v_content) {
		this.mst_v_content = mst_v_content;
	}

	public Date getMst_v_create_date() {
		return mst_v_create_date;
	}

	public void setMst_v_create_date(Date mst_v_create_date) {
		this.mst_v_create_date = mst_v_create_date;
	}

}
