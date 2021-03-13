package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class MemberVo {
	private int m_no;
	private int mst_mt_no;
	private String m_id;
	private String m_pw;
	private String m_nick;
	private String m_name;
	private String m_zip;
	private String m_address1;
	private String m_address2;
	private String m_email;
	private String m_tel;
	private String m_gender;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date m_birth;
	private String m_uniqnum;
	private String m_profile_img_link;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date m_create_date;
	
	public MemberVo() {
		super();
	}
	
	public MemberVo(int m_no, int mst_mt_no, String m_id, String m_pw, String m_nick, String m_name, String m_zip,
			String m_address1, String m_address2, String m_email, String m_tel, String m_gender, Date m_birth,
			String m_uniqnum, String m_profile_img_link, Date m_create_date) {
		super();
		this.m_no = m_no;
		this.mst_mt_no = mst_mt_no;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_nick = m_nick;
		this.m_name = m_name;
		this.m_zip = m_zip;
		this.m_address1 = m_address1;
		this.m_address2 = m_address2;
		this.m_email = m_email;
		this.m_tel = m_tel;
		this.m_gender = m_gender;
		this.m_birth = m_birth;
		this.m_uniqnum = m_uniqnum;
		this.m_profile_img_link = m_profile_img_link;
		this.m_create_date = m_create_date;
	}
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public int getMst_mt_no() {
		return mst_mt_no;
	}
	public void setMst_mt_no(int mst_mt_no) {
		this.mst_mt_no = mst_mt_no;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_zip() {
		return m_zip;
	}
	public void setM_zip(String m_zip) {
		this.m_zip = m_zip;
	}
	public String getM_address1() {
		return m_address1;
	}
	public void setM_address1(String m_address1) {
		this.m_address1 = m_address1;
	}
	public String getM_address2() {
		return m_address2;
	}
	public void setM_address2(String m_address2) {
		this.m_address2 = m_address2;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_tel() {
		return m_tel;
	}
	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}
	public String getM_gender() {
		return m_gender;
	}
	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}
	public Date getM_birth() {
		return m_birth;
	}
	public void setM_birth(Date m_birth) {
		this.m_birth = m_birth;
	}
	public String getM_uniqnum() {
		return m_uniqnum;
	}
	public void setM_uniqnum(String m_uniqnum) {
		this.m_uniqnum = m_uniqnum;
	}
	public String getM_profile_img_link() {
		return m_profile_img_link;
	}
	public void setM_profile_img_link(String m_profile_img_link) {
		this.m_profile_img_link = m_profile_img_link;
	}
	public Date getM_create_date() {
		return m_create_date;
	}
	public void setM_create_date(Date m_create_date) {
		this.m_create_date = m_create_date;
	}
}
