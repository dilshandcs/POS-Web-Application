package com.micropos.dto;

import java.io.Serializable;
import java.util.List;

public class PastPaidOutsDtoList implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer paidoutitemId;
	private String paidoutItemName;
	private String date;
	private List<PaidOutDto> paidOutDtoList;
	public Integer getPaidoutitemId() {
		return paidoutitemId;
	}
	public void setPaidoutitemId(Integer paidoutitemId) {
		this.paidoutitemId = paidoutitemId;
	}
	public String getPaidoutItemName() {
		return paidoutItemName;
	}
	public void setPaidoutItemName(String paidoutItemName) {
		this.paidoutItemName = paidoutItemName;
	}
	public List<PaidOutDto> getPaidOutDtoList() {
		return paidOutDtoList;
	}
	public void setPaidOutDtoList(List<PaidOutDto> paidOutDtoList) {
		this.paidOutDtoList = paidOutDtoList;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}



}
