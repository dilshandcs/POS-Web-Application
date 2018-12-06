package com.micropos.dto;

public class PastSalesFilterReportDto {
	/** The selected group by. */
	private String selectedGroupBy;

	/** The selected type. */
	private String selectedType;

	/** The asset id. */
	private String saleId;

	/** The fleet id. */
	private String itemId;

	/** The start date. */
	private String startDate;

	/** The end date. */
	private String endDate;

	/** The from date. */
	private String fromDate;

	/** The to date. */
	private String toDate;

	public String getSelectedGroupBy() {
		return selectedGroupBy;
	}

	public void setSelectedGroupBy(String selectedGroupBy) {
		this.selectedGroupBy = selectedGroupBy;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


}
