package com.give.donagi.vo;

public class BEImgVo {
	private int bei_no;
	private int be_no;
	private String bei_img_link;
	
	public BEImgVo() {
		super();
	}
	
	public BEImgVo(int bei_no, int be_no, String bei_img_link) {
		super();
		this.bei_no = bei_no;
		this.be_no = be_no;
		this.bei_img_link = bei_img_link;
	}
	
	public int getBei_no() {
		return bei_no;
	}
	public void setBei_no(int bei_no) {
		this.bei_no = bei_no;
	}
	public int getBe_no() {
		return be_no;
	}
	public void setBe_no(int be_no) {
		this.be_no = be_no;
	}
	public String getBei_img_link() {
		return bei_img_link;
	}
	public void setBei_img_link(String bei_img_link) {
		this.bei_img_link = bei_img_link;
	}
}
