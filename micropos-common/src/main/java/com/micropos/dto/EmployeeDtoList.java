package com.micropos.dto;

import java.io.Serializable;
import java.util.List;

public class EmployeeDtoList implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String month;
	private List<EmployeeDto> recordList;
	private Double tot;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public List<EmployeeDto> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<EmployeeDto> recordList) {
		this.recordList = recordList;
	}
	public Double getTot() {
		return tot;
	}
	public void setTot(Double tot) {
		this.tot = tot;
	}



}
