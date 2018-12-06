package com.micropos.dto;

import java.io.Serializable;
import java.util.List;
public class CashFLowDataLists implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<PaidOutByMonthDto> paidOutByMonthDtoList;
	private List<CashFlowDtoList> cashFlowDtoLists;
	public List<PaidOutByMonthDto> getPaidOutByMonthDtoList() {
		return paidOutByMonthDtoList;
	}
	public void setPaidOutByMonthDtoList(List<PaidOutByMonthDto> paidOutByMonthDtoList) {
		this.paidOutByMonthDtoList = paidOutByMonthDtoList;
	}
	public List<CashFlowDtoList> getCashFlowDtoLists() {
		return cashFlowDtoLists;
	}
	public void setCashFlowDtoLists(List<CashFlowDtoList> cashFlowDtoLists) {
		this.cashFlowDtoLists = cashFlowDtoLists;
	}



}
