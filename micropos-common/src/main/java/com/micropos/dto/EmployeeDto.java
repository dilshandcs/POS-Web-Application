package com.micropos.dto;

import java.io.Serializable;

public class EmployeeDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	String date;
	Double total;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}



}
