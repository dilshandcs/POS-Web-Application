package com.micropos.service;

import com.micropos.dto.SoldItemDtoList;
import com.micropos.dto.TransactionDataDto;

public interface ReportService {

	SoldItemDtoList TransactionData(String invoiceId);

}
