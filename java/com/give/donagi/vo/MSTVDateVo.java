package com.give.donagi.vo;

public class MSTVDateVo {
	private int mst_vd_no;
	private int mst_v_no;
	private String mst_vd_date;
	private String mst_vd_time;
	
	public MSTVDateVo() {
		super();
	}
	
	public MSTVDateVo(int mst_vd_no, int mst_v_no, String mst_vd_date, String mst_vd_time) {
		super();
		this.mst_vd_no = mst_vd_no;
		this.mst_v_no = mst_v_no;
		this.mst_vd_date = mst_vd_date;
		this.mst_vd_time = mst_vd_time;
	}
	
	public int getMst_vd_no() {
		return mst_vd_no;
	}
	public void setMst_vd_no(int mst_vd_no) {
		this.mst_vd_no = mst_vd_no;
	}
	public int getMst_v_no() {
		return mst_v_no;
	}
	public void setMst_v_no(int mst_v_no) {
		this.mst_v_no = mst_v_no;
	}
	public String getMst_vd_date() {
		return mst_vd_date;
	}
	public void setMst_vd_date(String mst_vd_date) {
		this.mst_vd_date = mst_vd_date;
	}
	public String getMst_vd_time() {
		return mst_vd_time;
	}
	public void setMst_vd_time(String mst_vd_time) {
		this.mst_vd_time = mst_vd_time;
	}
}
