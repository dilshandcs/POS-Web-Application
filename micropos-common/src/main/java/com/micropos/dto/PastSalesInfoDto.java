package com.micropos.dto;

import java.io.Serializable;
import java.util.List;

public class PastSalesInfoDto implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	private List<PastSalesIdInfo> saleIdInfoList;
	private List<PastSalesItemInfo> itemInfoList;
	public List<PastSalesIdInfo> getSaleIdInfoList() {
		return saleIdInfoList;
	}
	public void setSaleIdInfoList(List<PastSalesIdInfo> saleIdInfoList) {
		this.saleIdInfoList = saleIdInfoList;
	}
	public List<PastSalesItemInfo> getItemInfoList() {
		return itemInfoList;
	}
	public void setItemInfoList(List<PastSalesItemInfo> itemInfoList) {
		this.itemInfoList = itemInfoList;
	}


}
