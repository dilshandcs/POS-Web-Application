package com.micropos.dto;

import java.io.Serializable;
import java.util.Date;

public class SalesEmployeeDto implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

//	private Date date;
	private String invoiceNo;
	private String employeeName;
	private String date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



}
