package com.give.donagi.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class JoinVVo {
	private int jv_no;
	private int mst_vd_no;
	private int mst_v_no;
	private int m_no;
	private int mst_vs_no;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jv_submit_date;
	
	public JoinVVo() {
		super();
	}
	
	public JoinVVo(int jv_no, int mst_vd_no,int mst_v_no, int m_no, int mst_vs_no, Date jv_submit_date) {
		super();
		this.jv_no = jv_no;
		this.mst_vd_no = mst_vd_no;
		this.mst_v_no = mst_v_no;
		this.m_no = m_no;
		this.mst_vs_no = mst_vs_no;
		this.jv_submit_date = jv_submit_date;
	}
	
	public int getjv_no() {
		return jv_no;
	}
	public void setjv_no(int jv_no) {
		this.jv_no = jv_no;
	}
	public int getMst_vd_no() {
		return mst_vd_no;
	}
	public void setMst_vd_no(int mst_vd_no) {
		this.mst_vd_no = mst_vd_no;
	}
	public int getMst_v_no(){
		return mst_v_no;
	}
	public void setMst_v_no(int mst_v_no){
		this.mst_v_no = mst_v_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public int getMst_vs_no() {
		return mst_vs_no;
	}
	public void setMst_vs_no(int mst_vs_no) {
		this.mst_vs_no = mst_vs_no;
	}
	public Date getJv_submit_date() {
		return jv_submit_date;
	}
	public void setJv_submit_date(Date jv_submit_date) {
		this.jv_submit_date = jv_submit_date;
	}
}
