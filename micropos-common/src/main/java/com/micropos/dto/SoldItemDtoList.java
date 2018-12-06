package com.micropos.dto;

import java.util.Date;
import java.util.List;

public class SoldItemDtoList {
	List<SoldItemDto> soldItemDtos;
	Double fullTotal;
	Double paidAmount;
	String cusName;
	Double total;
	Double balance;
	String invoiceNo;
	String date;
Double amountToPay;
Boolean isAdvanced;

	public Boolean getIsAdvanced() {
	return isAdvanced;
}

public void setIsAdvanced(Boolean isAdvanced) {
	this.isAdvanced = isAdvanced;
}

	public Double getAmountToPay() {
	return amountToPay;
}

public void setAmountToPay(Double amountToPay) {
	this.amountToPay = amountToPay;
}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<SoldItemDto> getSoldItemDtos() {
		return soldItemDtos;
	}

	public void setSoldItemDtos(List<SoldItemDto> soldItemDtos) {
		this.soldItemDtos = soldItemDtos;
	}

	public Double getFullTotal() {
		return fullTotal;
	}

	public void setFullTotal(Double fullTotal) {
		this.fullTotal = fullTotal;
	}



}
