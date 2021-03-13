package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class ReportRDVo {
	private int rerd_no;
	private int rd_no;
	private int m_no;
	private String rerd_content;
	private int rerd_flg;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rerd_date;
	
	public ReportRDVo() {
		super();
	}
	
	public ReportRDVo(int rerd_no, int rd_no, int m_no, String rerd_content, int rerd_flg, Date rerd_date) {
		super();
		this.rerd_no = rerd_no;
		this.rd_no = rd_no;
		this.m_no = m_no;
		this.rerd_content = rerd_content;
		this.rerd_flg = rerd_flg;
		this.rerd_date = rerd_date;
	}
	
	public int getRerd_no() {
		return rerd_no;
	}
	public void setRerd_no(int rerd_no) {
		this.rerd_no = rerd_no;
	}
	public int getRd_no() {
		return rd_no;
	}
	public void setRd_no(int rd_no) {
		this.rd_no = rd_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getRerd_content() {
		return rerd_content;
	}
	public void setRerd_content(String rerd_content) {
		this.rerd_content = rerd_content;
	}
	public int getRerd_flg() {
		return rerd_flg;
	}
	public void setRerd_flg(int rerd_flg) {
		this.rerd_flg = rerd_flg;
	}
	public Date getRerd_date() {
		return rerd_date;
	}
	public void setRerd_date(Date rerd_date) {
		this.rerd_date = rerd_date;
	}
}
