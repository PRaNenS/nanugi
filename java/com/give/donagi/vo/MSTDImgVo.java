package com.give.donagi.vo;

public class MSTDImgVo {
	private int mst_di_no;
	private int mst_d_no;
	private String mst_di_img_link;
	
	public MSTDImgVo() {
		super();
	}
	
	public MSTDImgVo(int mst_di_no, int mst_d_no, String mst_di_img_link) {
		super();
		this.mst_di_no = mst_di_no;
		this.mst_d_no = mst_d_no;
		this.mst_di_img_link = mst_di_img_link;
	}
	
	public int getMst_di_no() {
		return mst_di_no;
	}
	public void setMst_di_no(int mst_di_no) {
		this.mst_di_no = mst_di_no;
	}
	public int getMst_d_no() {
		return mst_d_no;
	}
	public void setMst_d_no(int mst_d_no) {
		this.mst_d_no = mst_d_no;
	}
	public String getMst_di_img_link() {
		return mst_di_img_link;
	}
	public void setMst_di_img_link(String mst_di_img_link) {
		this.mst_di_img_link = mst_di_img_link;
	}
}
