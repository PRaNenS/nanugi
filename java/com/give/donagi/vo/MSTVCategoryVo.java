package com.give.donagi.vo;

public class MSTVCategoryVo 
{
	private int mst_vc_no;
	private String mst_vc_category;
	
	public MSTVCategoryVo() {
		super();
	}

	public MSTVCategoryVo(int mst_vc_no, String mst_vc_category) {
		super();
		this.mst_vc_no = mst_vc_no;
		this.mst_vc_category = mst_vc_category;
	}

	public int getMst_vc_no() {
		return mst_vc_no;
	}

	public void setMst_vc_no(int mst_vc_no) {
		this.mst_vc_no = mst_vc_no;
	}

	public String getMst_vc_category() {
		return mst_vc_category;
	}

	public void setMst_vc_category(String mst_vc_category) {
		this.mst_vc_category = mst_vc_category;
	}
	
}
