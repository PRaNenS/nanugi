package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class OrderFVo {
	private int of_no;
	private int mst_f_no;
	private int m_no;
	private int mst_os_no;
	private String of_receiver;
	private String of_zip;
	private String of_address1;
	private String of_address2;
	private String of_request;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date of_order_date;
	
	public OrderFVo() {
		super();
	}
	
	public OrderFVo(int of_no, int mst_f_no, int m_no, int mst_os_no, String of_receiver, String of_zip,
			String of_address1, String of_address2, String of_request, Date of_order_date) {
		super();
		this.of_no = of_no;
		this.mst_f_no = mst_f_no;
		this.m_no = m_no;
		this.mst_os_no = mst_os_no;
		this.of_receiver = of_receiver;
		this.of_zip = of_zip;
		this.of_address1 = of_address1;
		this.of_address2 = of_address2;
		this.of_request = of_request;
		this.of_order_date = of_order_date;
	}
	
	public int getOf_no() {
		return of_no;
	}
	public void setOf_no(int of_no) {
		this.of_no = of_no;
	}
	public int getMst_f_no() {
		return mst_f_no;
	}
	public void setMst_f_no(int mst_f_no) {
		this.mst_f_no = mst_f_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public int getMst_os_no() {
		return mst_os_no;
	}
	public void setMst_os_no(int mst_os_no) {
		this.mst_os_no = mst_os_no;
	}
	public String getOf_receiver() {
		return of_receiver;
	}
	public void setOf_receiver(String of_receiver) {
		this.of_receiver = of_receiver;
	}
	public String getOf_zip() {
		return of_zip;
	}
	public void setOf_zip(String of_zip) {
		this.of_zip = of_zip;
	}
	public String getOf_address1() {
		return of_address1;
	}
	public void setOf_address1(String of_address1) {
		this.of_address1 = of_address1;
	}
	public String getOf_address2() {
		return of_address2;
	}
	public void setOf_address2(String of_address2) {
		this.of_address2 = of_address2;
	}
	public String getOf_request() {
		return of_request;
	}
	public void setOf_request(String of_request) {
		this.of_request = of_request;
	}
	public Date getOf_order_date() {
		return of_order_date;
	}
	public void setOf_order_date(Date of_order_date) {
		this.of_order_date = of_order_date;
	}
}
