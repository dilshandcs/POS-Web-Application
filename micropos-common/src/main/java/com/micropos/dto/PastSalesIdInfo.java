package com.micropos.dto;

import java.io.Serializable;

public class PastSalesIdInfo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int saleId;
	private String invoiceNo;
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}



}
