package com.micropos.dao;

import java.util.List;

import com.micropos.domain.Sales;
import com.micropos.domain.SoldItems;

public interface SalesDao {
	Integer maximumInvoiceNumber();
	void saveSales(Sales sale);
	void saveSoldItem(SoldItems soldItem);
	Sales getAdvanceSale(Integer invoiceId);
	List<Sales> getSale(String invoiceId);
	List<Sales> getAllSale();
	Sales getById(Integer invoiceId);
	List<Sales> lastTenEmployeeRecords();
}
