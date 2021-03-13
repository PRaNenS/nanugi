package com.give.donagi.vo;

public class MSTMTypeVo {
	private int mst_mt_no;
	private String mst_mt_type;
	
	public MSTMTypeVo() {
		super();
	}
	
	public MSTMTypeVo(int mst_mt_no, String mst_mt_type) {
		super();
		this.mst_mt_no = mst_mt_no;
		this.mst_mt_type = mst_mt_type;
	}
	
	public int getMst_mt_no() {
		return mst_mt_no;
	}
	public void setMst_mt_no(int mst_mt_no) {
		this.mst_mt_no = mst_mt_no;
	}
	public String getMst_mt_type() {
		return mst_mt_type;
	}
	public void setMst_mt_type(String mst_mt_type) {
		this.mst_mt_type = mst_mt_type;
	}
}
