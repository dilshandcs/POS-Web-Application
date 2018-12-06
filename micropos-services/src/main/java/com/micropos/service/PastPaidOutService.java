package com.micropos.service;

import java.util.List;

import com.micropos.dto.CashFLowDataLists;
import com.micropos.dto.PaidOutDto;
import com.micropos.dto.PastPaidOutsDtoList;
import com.micropos.dto.PastPaidOutsFilterReportDto;

public interface PastPaidOutService {
	List<PaidOutDto> getDataForPaidOutsReport();

	List<PastPaidOutsDtoList> getPaidOutsForReport(PastPaidOutsFilterReportDto pastPaidOutsFilterReportDto);

	CashFLowDataLists getCashFlowData();
}
