package com.give.donagi.vo;

import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;

public class MRequestComVo {
	private int mrc_no;
	private int m_no;
	private String mrc_nick;
	private String mrc_name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mrc_birth;
	private String mrc_zip;
	private String mrc_address1;
	private String mrc_address2;
	private String mrc_tel;
	private String mrc_uniqnum;
	private int mrc_approved_flg;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mrc_request_date;
	
	public MRequestComVo() {
		super();
	}
	
	public MRequestComVo(int mrc_no, int m_no, String mrc_nick, String mrc_name, Date mrc_birth, String mrc_zip,
			String mrc_address1, String mrc_address2, String mrc_tel, String mrc_uniqnum, int mrc_approved_flg,
			Date mrc_request_date) {
		super();
		this.mrc_no = mrc_no;
		this.m_no = m_no;
		this.mrc_nick = mrc_nick;
		this.mrc_name = mrc_name;
		this.mrc_birth = mrc_birth;
		this.mrc_zip = mrc_zip;
		this.mrc_address1 = mrc_address1;
		this.mrc_address2 = mrc_address2;
		this.mrc_tel = mrc_tel;
		this.mrc_uniqnum = mrc_uniqnum;
		this.mrc_approved_flg = mrc_approved_flg;
		this.mrc_request_date = mrc_request_date;
	}
	
	public int getMrc_no() {
		return mrc_no;
	}
	public void setMrc_no(int mrc_no) {
		this.mrc_no = mrc_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getMrc_nick() {
		return mrc_nick;
	}
	public void setMrc_nick(String mrc_nick) {
		this.mrc_nick = mrc_nick;
	}
	public String getMrc_name() {
		return mrc_name;
	}
	public void setMrc_name(String mrc_name) {
		this.mrc_name = mrc_name;
	}
	public Date getMrc_birth() {
		return mrc_birth;
	}
	public void setMrc_birth(Date mrc_birth) {
		this.mrc_birth = mrc_birth;
	}
	public String getMrc_zip() {
		return mrc_zip;
	}
	public void setMrc_zip(String mrc_zip) {
		this.mrc_zip = mrc_zip;
	}
	public String getMrc_address1() {
		return mrc_address1;
	}
	public void setMrc_address1(String mrc_address1) {
		this.mrc_address1 = mrc_address1;
	}
	public String getMrc_address2() {
		return mrc_address2;
	}
	public void setMrc_address2(String mrc_address2) {
		this.mrc_address2 = mrc_address2;
	}
	public String getMrc_tel() {
		return mrc_tel;
	}
	public void setMrc_tel(String mrc_tel) {
		this.mrc_tel = mrc_tel;
	}
	public String getMrc_uniqnum() {
		return mrc_uniqnum;
	}
	public void setMrc_uniqnum(String mrc_uniqnum) {
		this.mrc_uniqnum = mrc_uniqnum;
	}
	public int getMrc_approved_flg() {
		return mrc_approved_flg;
	}
	public void setMrc_approved_flg(int mrc_approved_flg) {
		this.mrc_approved_flg = mrc_approved_flg;
	}
	public Date getMrc_request_date() {
		return mrc_request_date;
	}
	public void setMrc_request_date(Date mrc_request_date) {
		this.mrc_request_date = mrc_request_date;
	}
}
