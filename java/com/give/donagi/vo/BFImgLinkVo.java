package com.give.donagi.vo;

public class BFImgLinkVo {
	private int bfil_no;
	private int bf_no;
	private String bfil_path;
	
	public BFImgLinkVo() {
		super();
	}
	
	public BFImgLinkVo(int bfil_no, int bf_no, String bfil_path) {
		super();
		this.bfil_no = bfil_no;
		this.bf_no = bf_no;
		this.bfil_path = bfil_path;
	}
	
	public int getBfil_no() {
		return bfil_no;
	}
	public void setBfil_no(int bfil_no) {
		this.bfil_no = bfil_no;
	}
	public int getBf_no() {
		return bf_no;
	}
	public void setBf_no(int bf_no) {
		this.bf_no = bf_no;
	}
	public String getBfil_path() {
		return bfil_path;
	}
	public void setBfil_path(String bfil_path) {
		this.bfil_path = bfil_path;
	}
}
