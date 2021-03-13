package com.give.donagi.vo;

public class MSTFOptionVo {
	private int mst_fo_no;
	private int mst_f_no;
	private String mst_fo_name;
	private int mst_fo_price;
	
	public MSTFOptionVo() {
		super();
	}
	
	public MSTFOptionVo(int mst_fo_no, int mst_f_no, String mst_fo_name, int mst_fo_price) {
		super();
		this.mst_fo_no = mst_fo_no;
		this.mst_f_no = mst_f_no;
		this.mst_fo_name = mst_fo_name;
		this.mst_fo_price = mst_fo_price;
	}
	
	public int getMst_fo_no() {
		return mst_fo_no;
	}
	public void setMst_fo_no(int mst_fo_no) {
		this.mst_fo_no = mst_fo_no;
	}
	public int getMst_f_no() {
		return mst_f_no;
	}
	public void setMst_f_no(int mst_f_no) {
		this.mst_f_no = mst_f_no;
	}
	public String getMst_fo_name() {
		return mst_fo_name;
	}
	public void setMst_fo_name(String mst_fo_name) {
		this.mst_fo_name = mst_fo_name;
	}
	public int getMst_fo_price() {
		return mst_fo_price;
	}
	public void setMst_fo_price(int mst_fo_price) {
		this.mst_fo_price = mst_fo_price;
	}
}
