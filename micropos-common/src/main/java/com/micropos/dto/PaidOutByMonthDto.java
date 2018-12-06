package com.micropos.dto;

import java.io.Serializable;
import java.util.Date;

public class PaidOutByMonthDto implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private Double item1;
	private Double item2;
	private Double item3;
	private Double item4;
	private Double item5;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getItem1() {
		return item1;
	}
	public void setItem1(Double item1) {
		this.item1 = item1;
	}
	public Double getItem2() {
		return item2;
	}
	public void setItem2(Double item2) {
		this.item2 = item2;
	}
	public Double getItem3() {
		return item3;
	}
	public void setItem3(Double item3) {
		this.item3 = item3;
	}
	public Double getItem4() {
		return item4;
	}
	public void setItem4(Double item4) {
		this.item4 = item4;
	}
	public Double getItem5() {
		return item5;
	}
	public void setItem5(Double item5) {
		this.item5 = item5;
	}


}
