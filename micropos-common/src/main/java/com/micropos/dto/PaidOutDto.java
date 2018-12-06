package com.micropos.dto;

import java.io.Serializable;
import java.util.Date;

public class PaidOutDto implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descriptionP;
	private Double amountP;
	private Date date;
	private String dates;
	private Integer paidOutItem;

	public Integer getPaidOutItem() {
		return paidOutItem;
	}
	public void setPaidOutItem(Integer paidOutItem) {
		this.paidOutItem = paidOutItem;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescriptionP() {
		return descriptionP;
	}
	public void setDescriptionP(String descriptionP) {
		this.descriptionP = descriptionP;
	}
	public Double getAmountP() {
		return amountP;
	}
	public void setAmountP(Double amountP) {
		this.amountP = amountP;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}



}
