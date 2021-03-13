package com.give.donagi.vo;

public class MSTFCategoryVo {
	private int mst_fc_no;
	private String mst_fc_category;
	
	public MSTFCategoryVo() {
		super();
	}
	
	public MSTFCategoryVo(int mst_fc_no, String mst_fc_category) {
		super();
		this.mst_fc_no = mst_fc_no;
		this.mst_fc_category = mst_fc_category;
	}
	
	public int getMst_fc_no() {
		return mst_fc_no;
	}
	public void setMst_fc_no(int mst_fc_no) {
		this.mst_fc_no = mst_fc_no;
	}
	public String getMst_fc_category() {
		return mst_fc_category;
	}
	public void setMst_fc_category(String mst_fc_category) {
		this.mst_fc_category = mst_fc_category;
	}
}
