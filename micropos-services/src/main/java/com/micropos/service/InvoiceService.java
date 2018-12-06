package com.micropos.service;

import java.util.List;

import com.micropos.dto.SaleDto;


public interface InvoiceService {
	List<SaleDto> SearchAdvancedInvoice(String query);

	List<SaleDto> SearchInvoicees(String query);

	void voidInvoice(String voidinvoiceId);

}
