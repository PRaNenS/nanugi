package com.give.donagi.vo;

public class MPointVo {
	private int m_no;
	private int mp_point;
	
	public MPointVo() {
		super();
	}
	
	public MPointVo(int m_no, int mp_point) {
		super();
		this.m_no = m_no;
		this.mp_point = mp_point;
	}
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public int getMp_point() {
		return mp_point;
	}
	public void setMp_point(int mp_point) {
		this.mp_point = mp_point;
	}
}
