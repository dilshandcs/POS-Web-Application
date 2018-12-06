package com.micropos.dto;

import java.io.Serializable;

public class PastSalesItemInfo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int itemId;
	private String itemNo;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}


}
