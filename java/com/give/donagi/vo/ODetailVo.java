package com.give.donagi.vo;

public class ODetailVo {
	private int od_no;
	private int of_no;
	private int mst_fo_no;
	private int od_quantity;
	
	public ODetailVo() {
		super();
	}
	
	public ODetailVo(int od_no, int of_no, int mst_fo_no, int od_quantity) {
		super();
		this.od_no = od_no;
		this.of_no = of_no;
		this.mst_fo_no = mst_fo_no;
		this.od_quantity = od_quantity;
	}
	
	public int getOd_no() {
		return od_no;
	}
	public void setOd_no(int od_no) {
		this.od_no = od_no;
	}
	public int getOf_no() {
		return of_no;
	}
	public void setOf_no(int of_no) {
		this.of_no = of_no;
	}
	public int getMst_fo_no() {
		return mst_fo_no;
	}
	public void setMst_fo_no(int mst_fo_no) {
		this.mst_fo_no = mst_fo_no;
	}
	public int getOd_quantity() {
		return od_quantity;
	}
	public void setOd_quantity(int od_quantity) {
		this.od_quantity = od_quantity;
	}
}
