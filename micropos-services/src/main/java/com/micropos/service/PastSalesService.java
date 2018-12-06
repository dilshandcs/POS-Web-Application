package com.micropos.service;

import java.util.List;

import com.micropos.dto.CashFLowDataLists;
import com.micropos.dto.CashFlowDto;
import com.micropos.dto.CashFlowDtoList;
import com.micropos.dto.PastSalesFilterReportDto;
import com.micropos.dto.PastSalesInfoDto;
import com.micropos.dto.PastSalesReportDto;

public interface PastSalesService {
	PastSalesInfoDto getDataForSalesReport();

	List<PastSalesReportDto> getPastSalesForReport(PastSalesFilterReportDto pastSalesReportFilterInfoDto);

	CashFLowDataLists getCashFlowData();
}
