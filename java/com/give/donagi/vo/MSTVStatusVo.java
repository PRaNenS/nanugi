package com.give.donagi.vo;

public class MSTVStatusVo {
	private int mst_vs_no;
	private String mst_vs_status;
	
	public MSTVStatusVo() {
		super();
	}
	
	public MSTVStatusVo(int mst_vs_no, String mst_vs_status) {
		super();
		this.mst_vs_no = mst_vs_no;
		this.mst_vs_status = mst_vs_status;
	}
	
	public int getMst_vs_no() {
		return mst_vs_no;
	}
	public void setMst_vs_no(int mst_vs_no) {
		this.mst_vs_no = mst_vs_no;
	}
	public String getMst_vs_status() {
		return mst_vs_status;
	}
	public void setMst_vs_status(String mst_vs_status) {
		this.mst_vs_status = mst_vs_status;
	}
}
