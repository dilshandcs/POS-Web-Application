package com.micropos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micropos.dto.SoldItemDtoList;
import com.micropos.service.ReportService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportDataManager {
	@Autowired
	ReportService reportService;

	public Map<String, Object> getTransactionData(String invoiceId) throws Exception {

		SoldItemDtoList invoiceDataDto=reportService.TransactionData(invoiceId);
		List<SoldItemDtoList> list=new ArrayList<>();
		list.add(invoiceDataDto);
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(list);
        JRDataSource subDatasource1 =new JRBeanCollectionDataSource(invoiceDataDto.getSoldItemDtos());

        Map<String,Object> parameterMap = new HashMap<String,Object>();

        parameterMap.put("datasource", JRdataSource);
        parameterMap.put("JasperCustomSubReportDatasource1", subDatasource1);

		return parameterMap;


	}
}
