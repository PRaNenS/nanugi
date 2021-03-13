package com.give.donagi.vo;

public class MSTFStatusVo {
	private int mst_fs_no;
	private String mst_fs_status;
	
	public MSTFStatusVo() {
		super();
	}
	
	public MSTFStatusVo(int mst_fs_no, String mst_fs_status) {
		super();
		this.mst_fs_no = mst_fs_no;
		this.mst_fs_status = mst_fs_status;
	}
	
	public int getMst_fs_no() {
		return mst_fs_no;
	}
	public void setMst_fs_no(int mst_fs_no) {
		this.mst_fs_no = mst_fs_no;
	}
	public String getMst_fs_status() {
		return mst_fs_status;
	}
	public void setMst_fs_status(String mst_fs_status) {
		this.mst_fs_status = mst_fs_status;
	}
}
