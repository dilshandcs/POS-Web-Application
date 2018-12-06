package com.micropos.dao;

import java.util.List;

import com.micropos.domain.Sales;

public interface InoviceDao extends BaseDao<Sales>{
	List<Sales> SearchAdvanceInvoices(String query);
	 List<Sales> SearchInvoices(String query);
	 List<Sales> getInvoice(String voidinvoiceId);
}
