package com.micropos.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SaleDto implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date date;
	private Double total;
	private Double paidAmount;
	private Double balance;
	private Double cashIn;
	private Boolean isAdvance;
	private String cusName;
	private Boolean isDelete;
	private List<SoldItemDto> soldItems;
	private Integer advanceInvoiceNumber;
	private String invoiceDisplayValue;
	private String invoiceNo;
	private String allInvoiceIdDisplayValues;
	private String allInvoiceNumbers;


	public String getAllInvoiceIdDisplayValues() {
		return allInvoiceIdDisplayValues;
	}
	public void setAllInvoiceIdDisplayValues(String allInvoiceIdDisplayValues) {
		this.allInvoiceIdDisplayValues = allInvoiceIdDisplayValues;
	}
	public String getAllInvoiceNumbers() {
		return allInvoiceNumbers;
	}
	public void setAllInvoiceNumbers(String allInvoiceNumbers) {
		this.allInvoiceNumbers = allInvoiceNumbers;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Integer getAdvanceInvoiceNumber() {
		return advanceInvoiceNumber;
	}
	public void setAdvanceInvoiceNumber(Integer advanceInvoiceNumber) {
		this.advanceInvoiceNumber = advanceInvoiceNumber;
	}
	public String getInvoiceDisplayValue() {
		return invoiceDisplayValue;
	}
	public void setInvoiceDisplayValue(String invoiceDisplayValue) {
		this.invoiceDisplayValue = invoiceDisplayValue;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
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
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public List<SoldItemDto> getSoldItems() {
		return soldItems;
	}
	public void setSoldItems(List<SoldItemDto> soldItems) {
		this.soldItems = soldItems;
	}



}
