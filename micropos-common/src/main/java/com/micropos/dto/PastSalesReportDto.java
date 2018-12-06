package com.micropos.dto;

import java.util.Date;
import java.util.List;

public class PastSalesReportDto {
	private Double cashIn;
	private Boolean isAdvance;
	private String cusName;
	private String invoiceNo;
	private Date date;
	private Double total;
	List<PastSalesReportDataDto> pastSalesDataDto;
	public Double getCashIn() {
		return cashIn;
	}
	public void setCashIn(Double cashIn) {
		this.cashIn = cashIn;
	}
	public Boolean getIsAdvance() {
		return isAdvance;
	}
	public void setIsAdvance(Boolean isAdvance) {
		this.isAdvance = isAdvance;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public List<PastSalesReportDataDto> getPastSalesDataDto() {
		return pastSalesDataDto;
	}
	public void setPastSalesDataDto(List<PastSalesReportDataDto> pastSalesDataDto) {
		this.pastSalesDataDto = pastSalesDataDto;
	}


}
