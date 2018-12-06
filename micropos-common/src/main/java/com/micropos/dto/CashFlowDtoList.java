package com.micropos.dto;

import java.io.Serializable;
import java.util.List;

public class CashFlowDtoList  implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private List<CashFlowDto> cashFlowDtos;
	private Double totalIn;
	private Double totalOut;
	private Double totalBalance;

	public Double getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}
	public Double getTotalIn() {
		return totalIn;
	}
	public void setTotalIn(Double totalIn) {
		this.totalIn = totalIn;
	}
	public Double getTotalOut() {
		return totalOut;
	}
	public void setTotalOut(Double totalOut) {
		this.totalOut = totalOut;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<CashFlowDto> getCashFlowDtos() {
		return cashFlowDtos;
	}
	public void setCashFlowDtos(List<CashFlowDto> cashFlowDtos) {
		this.cashFlowDtos = cashFlowDtos;
	}



}
