package com.give.donagi.vo;

public class MSTVImgVo 
{
	private int mst_vi_no;
	private int mst_v_no;
	private String mst_vi_img_link;
	
	public MSTVImgVo() {
		super();
	}

	public MSTVImgVo(int mst_vi_no, int mst_v_no, String mst_vi_img_link) {
		super();
		this.mst_vi_no = mst_vi_no;
		this.mst_v_no = mst_v_no;
		this.mst_vi_img_link = mst_vi_img_link;
	}

	public int getMst_vi_no() {
		return mst_vi_no;
	}

	public void setMst_vi_no(int mst_vi_no) {
		this.mst_vi_no = mst_vi_no;
	}

	public int getMst_v_no() {
		return mst_v_no;
	}

	public void setMst_v_no(int mst_v_no) {
		this.mst_v_no = mst_v_no;
	}

	public String getMst_vi_img_link() {
		return mst_vi_img_link;
	}

	public void setMst_vi_img_link(String mst_vi_img_link) {
		this.mst_vi_img_link = mst_vi_img_link;
	}
	
}
