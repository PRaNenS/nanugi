package com.give.donagi.vo;

public class MSTFImgVo {
	private int mst_fi_no;
	private int mst_f_no;
	private String mst_fi_img_link;
	
	public MSTFImgVo() {
		super();
	}
	
	public MSTFImgVo(int mst_fi_no, int mst_f_no, String mst_fi_img_link) {
		super();
		this.mst_fi_no = mst_fi_no;
		this.mst_f_no = mst_f_no;
		this.mst_fi_img_link = mst_fi_img_link;
	}
	
	public int getMst_fi_no() {
		return mst_fi_no;
	}
	public void setMst_fi_no(int mst_fi_no) {
		this.mst_fi_no = mst_fi_no;
	}
	public int getMst_f_no() {
		return mst_f_no;
	}
	public void setMst_f_no(int mst_f_no) {
		this.mst_f_no = mst_f_no;
	}
	public String getMst_fi_img_link() {
		return mst_fi_img_link;
	}
	public void setMst_fi_img_link(String mst_fi_img_link) {
		this.mst_fi_img_link = mst_fi_img_link;
	}
}
