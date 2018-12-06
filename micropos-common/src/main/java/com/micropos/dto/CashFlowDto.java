package com.micropos.dto;

import java.io.Serializable;
import java.util.Date;

public class CashFlowDto  implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private Double cashIn;
	private Double cashOut;
	private Double balance;
	private Double netSave;
	private Double cashForward;




	public Double getNetSave() {
		return netSave;
	}
	public void setNetSave(Double netSave) {
		this.netSave = netSave;
	}
	public Double getCashForward() {
		return cashForward;
	}
	public void setCashForward(Double cashForward) {
		this.cashForward = cashForward;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getCashIn() {
		return cashIn;
	}
	public void setCashIn(Double cashIn) {
		this.cashIn = cashIn;
	}
	public Double getCashOut() {
		return cashOut;
	}
	public void setCashOut(Double cashOut) {
		this.cashOut = cashOut;
	}


}
