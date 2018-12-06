package com.micropos.dao;

import java.util.List;

import com.micropos.domain.Sales;
import com.micropos.domain.SoldItems;

public interface PastSalesDao extends BaseDao<SoldItems>{
	List<SoldItems> getAllSales();

	List<SoldItems> getAllPastSalesHistory();

	List<SoldItems> getAllPastSalesHistorySaleId(int parseInt);

	List<SoldItems> getAllPastSalesHistoryBiItemId(int parseInt);
}
