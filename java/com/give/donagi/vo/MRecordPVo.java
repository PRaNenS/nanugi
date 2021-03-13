package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class MRecordPVo {
	private int m_rp_no;
	private int m_no;
	private int m_rp_type;
	private String m_rp_object;
	private int m_rp_point;
	private String m_rp_record;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date m_rp_record_date;
	
	public MRecordPVo() {
		super();
	}
	
	public MRecordPVo(int m_rp_no, int m_no, int m_rp_type, String m_rp_object, int m_rp_point, String m_rp_record,
			Date m_rp_record_date) {
		super();
		this.m_rp_no = m_rp_no;
		this.m_no = m_no;
		this.m_rp_type = m_rp_type;
		this.m_rp_object = m_rp_object;
		this.m_rp_point = m_rp_point;
		this.m_rp_record = m_rp_record;
		this.m_rp_record_date = m_rp_record_date;
	}
	
	public int getM_rp_no() {
		return m_rp_no;
	}
	public void setM_rp_no(int m_rp_no) {
		this.m_rp_no = m_rp_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public int getM_rp_type() {
		return m_rp_type;
	}
	public void setM_rp_type(int m_rp_type) {
		this.m_rp_type = m_rp_type;
	}
	public String getM_rp_object() {
		return m_rp_object;
	}
	public void setM_rp_object(String m_rp_object) {
		this.m_rp_object = m_rp_object;
	}
	public int getM_rp_point() {
		return m_rp_point;
	}
	public void setM_rp_point(int m_rp_point) {
		this.m_rp_point = m_rp_point;
	}
	public String getM_rp_record() {
		return m_rp_record;
	}
	public void setM_rp_record(String m_rp_record) {
		this.m_rp_record = m_rp_record;
	}
	public Date getM_rp_record_date() {
		return m_rp_record_date;
	}
	public void setM_rp_record_date(Date m_rp_record_date) {
		this.m_rp_record_date = m_rp_record_date;
	}
}
