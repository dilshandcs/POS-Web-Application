package com.micropos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sales")
public class Sales implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false, unique=true)
	private Integer id;

	@Column(name="date")
	private Date date;

	@Column(name="total")
	private Double total;

	@Column(name="paidAmount")
	private Double paidAmount;

	@Column(name="balance")
	private Double balance;

	@Column(name="cashIn")
	private Double cashIn;

	@Column(name="isAdvance")
	private Boolean isAdvance;

	@Column(name="cusName")
	private String cusName;

	@Column(name="isDelete")
	private Boolean isDelete;

	@Column(name="emp")
	private Boolean isEmp;

	@Column(name="invoiceNo")
	private String invoiceNo;



	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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

	public Boolean getIsEmp() {
		return isEmp;
	}

	public void setIsEmp(Boolean isEmp) {
		this.isEmp = isEmp;
	}




}
