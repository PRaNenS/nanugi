package com.give.donagi.vo;

public class MSTRDStatusVo {
	private int mst_rds_no;
	private String mst_rds_status;
	
	public MSTRDStatusVo() {
		super();
	}
	
	public MSTRDStatusVo(int mst_rds_no, String mst_rds_status) {
		super();
		this.mst_rds_no = mst_rds_no;
		this.mst_rds_status = mst_rds_status;
	}
	
	public int getMst_rds_no() {
		return mst_rds_no;
	}
	public void setMst_rds_no(int mst_rds_no) {
		this.mst_rds_no = mst_rds_no;
	}
	public String getMst_rds_status() {
		return mst_rds_status;
	}
	public void setMst_rds_status(String mst_rds_status) {
		this.mst_rds_status = mst_rds_status;
	}
}
