package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class MAuthVo {
	private int m_no;
	private String ma_auth_key;
	private int ma_auth_flg;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ma_auth_date;
	
	public MAuthVo() {
		super();
	}
	
	public MAuthVo(int m_no, String ma_auth_key, int ma_auth_flg, Date ma_auth_date) {
		super();
		this.m_no = m_no;
		this.ma_auth_key = ma_auth_key;
		this.ma_auth_flg = ma_auth_flg;
		this.ma_auth_date = ma_auth_date;
	}
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getMa_auth_key() {
		return ma_auth_key;
	}
	public void setMa_auth_key(String ma_auth_key) {
		this.ma_auth_key = ma_auth_key;
	}
	public int getMa_auth_flg() {
		return ma_auth_flg;
	}
	public void setMa_auth_flg(int ma_auth_flg) {
		this.ma_auth_flg = ma_auth_flg;
	}
	public Date getMa_auth_date() {
		return ma_auth_date;
	}
	public void setMa_auth_date(Date ma_auth_date) {
		this.ma_auth_date = ma_auth_date;
	}
}
