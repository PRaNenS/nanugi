package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class MSTFundingVo {
	private int mst_f_no;
	private int m_no;
	private int mst_fc_no;
	private int mst_fs_no;
	private String mst_f_title;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mst_f_date_from;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mst_f_date_to;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mst_f_date_end;
	private int mst_f_goal;
	private String mst_f_content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mst_f_create_date;
	
	public MSTFundingVo() {
		super();
	}
	
	public MSTFundingVo(int mst_f_no, int m_no, int mst_fc_no, int mst_fs_no, String mst_f_title, Date mst_f_date_from,
			Date mst_f_date_to, Date mst_f_date_end, int mst_f_goal, String mst_f_content, Date mst_f_create_date) {
		super();
		this.mst_f_no = mst_f_no;
		this.m_no = m_no;
		this.mst_fc_no = mst_fc_no;
		this.mst_fs_no = mst_fs_no;
		this.mst_f_title = mst_f_title;
		this.mst_f_date_from = mst_f_date_from;
		this.mst_f_date_to = mst_f_date_to;
		this.mst_f_date_end = mst_f_date_end;
		this.mst_f_goal = mst_f_goal;
		this.mst_f_content = mst_f_content;
		this.mst_f_create_date = mst_f_create_date;
	}
	
	public int getMst_f_no() {
		return mst_f_no;
	}
	public void setMst_f_no(int mst_f_no) {
		this.mst_f_no = mst_f_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public int getMst_fc_no() {
		return mst_fc_no;
	}
	public void setMst_fc_no(int mst_fc_no) {
		this.mst_fc_no = mst_fc_no;
	}
	public int getMst_fs_no() {
		return mst_fs_no;
	}
	public void setMst_fs_no(int mst_fs_no) {
		this.mst_fs_no = mst_fs_no;
	}
	public String getMst_f_title() {
		return mst_f_title;
	}
	public void setMst_f_title(String mst_f_title) {
		this.mst_f_title = mst_f_title;
	}
	public Date getMst_f_date_from() {
		return mst_f_date_from;
	}
	public void setMst_f_date_from(Date mst_f_date_from) {
		this.mst_f_date_from = mst_f_date_from;
	}
	public Date getMst_f_date_to() {
		return mst_f_date_to;
	}
	public void setMst_f_date_to(Date mst_f_date_to) {
		this.mst_f_date_to = mst_f_date_to;
	}
	public Date getMst_f_date_end() {
		return mst_f_date_end;
	}
	public void setMst_f_date_end(Date mst_f_date_end) {
		this.mst_f_date_end = mst_f_date_end;
	}
	public int getMst_f_goal() {
		return mst_f_goal;
	}
	public void setMst_f_goal(int mst_f_goal) {
		this.mst_f_goal = mst_f_goal;
	}
	public String getMst_f_content() {
		return mst_f_content;
	}
	public void setMst_f_content(String mst_f_content) {
		this.mst_f_content = mst_f_content;
	}
	public Date getMst_f_create_date() {
		return mst_f_create_date;
	}
	public void setMst_f_create_date(Date mst_f_create_date) {
		this.mst_f_create_date = mst_f_create_date;
	}
}
