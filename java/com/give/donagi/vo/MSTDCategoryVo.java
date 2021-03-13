package com.give.donagi.vo;

public class MSTDCategoryVo {
	private int mst_dc_no;
	private String mst_dc_category;
	
	public MSTDCategoryVo() {
		super();
	}
	
	public MSTDCategoryVo(int mst_dc_no, String mst_dc_category) {
		super();
		this.mst_dc_no = mst_dc_no;
		this.mst_dc_category = mst_dc_category;
	}
	
	public int getMst_dc_no() {
		return mst_dc_no;
	}
	public void setMst_dc_no(int mst_dc_no) {
		this.mst_dc_no = mst_dc_no;
	}
	public String getMst_dc_category() {
		return mst_dc_category;
	}
	public void setMst_dc_category(String mst_dc_category) {
		this.mst_dc_category = mst_dc_category;
	}
}
