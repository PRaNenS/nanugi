package com.give.donagi.vo;

public class MSTOStatusVo {
	private int mst_os_no;
	private String mst_os_status;
	
	public MSTOStatusVo() {
		super();
	}
	
	public MSTOStatusVo(int mst_os_no, String mst_os_status) {
		super();
		this.mst_os_no = mst_os_no;
		this.mst_os_status = mst_os_status;
	}
	
	public int getMst_os_no() {
		return mst_os_no;
	}
	public void setMst_os_no(int mst_os_no) {
		this.mst_os_no = mst_os_no;
	}
	public String getMst_os_status() {
		return mst_os_status;
	}
	public void setMst_os_status(String mst_os_status) {
		this.mst_os_status = mst_os_status;
	}
}
