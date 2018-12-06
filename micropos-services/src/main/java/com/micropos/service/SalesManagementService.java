package com.micropos.service;

import java.util.List;

import com.micropos.dto.SaleDto;
import com.micropos.dto.SoldItemDto;

public interface SalesManagementService {
	Integer getMaximumInvoiceNumber();
	Double SaveFullTransaction(List<SoldItemDto> soldItemDtos, Double padiAmount, String cusName, Double total);
	void SaveAdvanceTransaction(List<SoldItemDto> soldItemDtos, Double padiAmount, String cusName, Double total);
	void CompleteAdvance(Integer invoiceId);
	String SaveTransaction(List<SoldItemDto> soldItemDtos, Double paidAmount, String cusName, Double total);
}
