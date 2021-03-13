package com.give.donagi.vo;

public class MSTBFHeadlineVo {
	private int mst_bfh_no;
	private String mst_bfh_headline;
	
	public MSTBFHeadlineVo() {
		super();
	}
	
	public MSTBFHeadlineVo(int mst_bfh_no, String mst_bfh_headline) {
		super();
		this.mst_bfh_no = mst_bfh_no;
		this.mst_bfh_headline = mst_bfh_headline;
	}
	
	public int getMst_bfh_no() {
		return mst_bfh_no;
	}
	public void setMst_bfh_no(int mst_bfh_no) {
		this.mst_bfh_no = mst_bfh_no;
	}
	public String getMst_bfh_headline() {
		return mst_bfh_headline;
	}
	public void setMst_bfh_headline(String mst_bfh_headline) {
		this.mst_bfh_headline = mst_bfh_headline;
	}
}
