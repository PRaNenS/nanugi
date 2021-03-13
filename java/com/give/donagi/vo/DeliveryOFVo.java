package com.give.donagi.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class DeliveryOFVo {
	private int of_no;
	private int dof_flg;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dof_date;
	
	public DeliveryOFVo() {
		super();
	}
	
	public DeliveryOFVo(int of_no, int dof_flg, Date dof_date) {
		super();
		this.of_no = of_no;
		this.dof_flg = dof_flg;
		this.dof_date = dof_date;
	}
	
	public int getOf_no() {
		return of_no;
	}
	public void setOf_no(int of_no) {
		this.of_no = of_no;
	}
	public int getDof_flg() {
		return dof_flg;
	}
	public void setDof_flg(int dof_flg) {
		this.dof_flg = dof_flg;
	}
	public Date getDof_date() {
		return dof_date;
	}
	public void setDof_date(Date dof_date) {
		this.dof_date = dof_date;
	}
}
