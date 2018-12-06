package com.micropos.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micropos.dao.PaidOutDao;
import com.micropos.dao.PastSalesDao;
import com.micropos.dao.SalesDao;
import com.micropos.domain.PaidOut;
import com.micropos.domain.Sales;
import com.micropos.domain.SoldItems;
import com.micropos.dto.CashFLowDataLists;
import com.micropos.dto.CashFlowDto;
import com.micropos.dto.CashFlowDtoList;
import com.micropos.dto.PaidOutByMonthDto;
import com.micropos.dto.PastSalesFilterReportDto;
import com.micropos.dto.PastSalesIdInfo;
import com.micropos.dto.PastSalesInfoDto;
import com.micropos.dto.PastSalesItemInfo;
import com.micropos.dto.PastSalesReportDataDto;
import com.micropos.dto.PastSalesReportDto;
import com.micropos.service.PastSalesService;

@Service
public class PastSalesServiceImpl implements PastSalesService{

	@Autowired
	PastSalesDao pastSalesDao;

	@Autowired
	SalesDao salesDao;

	@Autowired
	PaidOutDao paidOutDao;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	@Transactional
	public PastSalesInfoDto getDataForSalesReport() {
		List<SoldItems> salesList=pastSalesDao.getAllSales();

		PastSalesInfoDto pastSalesInfoDto=new PastSalesInfoDto();

		List<PastSalesIdInfo> saleIdInfoList=new ArrayList<PastSalesIdInfo>();
		List<PastSalesItemInfo> itemInfoList=new ArrayList<PastSalesItemInfo>();

		Map<String, PastSalesIdInfo> tempMap1 = new HashMap<String, PastSalesIdInfo>();
		Map<String, PastSalesItemInfo> tempMap2 = new HashMap<String, PastSalesItemInfo>();

		for(SoldItems sale:salesList) {
			PastSalesIdInfo pastSalesIdInfo=new PastSalesIdInfo();
			if(!tempMap1.containsKey(sale.getSale().getId()+"")&&sale.getSale().getIsDelete()!=true) {
				pastSalesIdInfo.setSaleId(sale.getSale().getId());
				pastSalesIdInfo.setInvoiceNo(sale.getSale().getInvoiceNo());
				tempMap1.put(sale.getSale().getId()+"", pastSalesIdInfo);
				saleIdInfoList.add(pastSalesIdInfo);
			}

			PastSalesItemInfo  pastSalesItemInfo=new PastSalesItemInfo();
			if(!tempMap2.containsKey(sale.getItems().getId()+"")&&sale.getSale().getIsDelete()!=true) {
				pastSalesItemInfo.setItemId(sale.getItems().getId());
				pastSalesItemInfo.setItemNo(sale.getItems().getName());
				tempMap2.put(sale.getItems().getId()+"", pastSalesItemInfo);
				itemInfoList.add(pastSalesItemInfo);
			}

		}
		saleIdInfoList.sort(new Comparator<PastSalesIdInfo>() {
			public int compare(PastSalesIdInfo o1, PastSalesIdInfo o2) {
				return o1.getInvoiceNo().compareTo(o2.getInvoiceNo());
			}
		});
		itemInfoList.sort(new Comparator<PastSalesItemInfo>() {
			public int compare(PastSalesItemInfo o1, PastSalesItemInfo o2) {
				return o1.getItemNo().compareTo(o2.getItemNo());
			}
		});

		pastSalesInfoDto.setItemInfoList(itemInfoList);
		pastSalesInfoDto.setSaleIdInfoList(saleIdInfoList);

		return pastSalesInfoDto;
	}
	@Transactional
	public List<PastSalesReportDto> getPastSalesForReport(PastSalesFilterReportDto pastSalesReportFilterInfoDto) {
		List<SoldItems>  pastSalesHistoryList=new ArrayList<SoldItems>();
		Integer value;
		if(pastSalesReportFilterInfoDto.getSelectedType().equals("SALES")) {
			value=1;
		}else if(pastSalesReportFilterInfoDto.getSelectedType().equals("ITEMS")) {
			value=2;
		}
		else {
			value=0;
		}

		switch (value) {
		case 1:
			if (pastSalesReportFilterInfoDto.getSaleId().equals("ALL")) {
				pastSalesHistoryList = pastSalesDao.getAllPastSalesHistory();
			} else {
				pastSalesHistoryList = pastSalesDao.getAllPastSalesHistorySaleId(Integer.parseInt(pastSalesReportFilterInfoDto.getSaleId()));
				}

			break;
		case 2:
			if (pastSalesReportFilterInfoDto.getItemId().equals("ALL")) {
				pastSalesHistoryList = pastSalesDao.getAllPastSalesHistory();
				}else {
					pastSalesHistoryList = pastSalesDao.getAllPastSalesHistoryBiItemId(Integer.parseInt(pastSalesReportFilterInfoDto.getItemId()));
				}

			break;
			default:{break;}
	}

		try {
			Calendar c = Calendar.getInstance();

			Date fromDate;
			Date toDate;

			if(pastSalesReportFilterInfoDto.getFromDate()!=null && !pastSalesReportFilterInfoDto.getFromDate().isEmpty() && pastSalesReportFilterInfoDto.getToDate()!=null && !pastSalesReportFilterInfoDto.getToDate().isEmpty()) {
				c.setTime(sdf.parse(pastSalesReportFilterInfoDto.getFromDate()));
				fromDate = c.getTime();

				c.setTime(sdf.parse(pastSalesReportFilterInfoDto.getToDate()));
				toDate = c.getTime();

			}else {
				String[] from = pastSalesReportFilterInfoDto.getStartDate().split("/"); // get year and month from

				c.set(Integer.parseInt(from[0]), Integer.parseInt(from[1]) - 1, 1);
				 fromDate = c.getTime();


				toDate = new Date();
			}


			Map<Integer, List<PastSalesReportDataDto>> map=new HashMap<Integer, List<PastSalesReportDataDto>>();
			List<PastSalesReportDto> pastSalesReportDtos=new ArrayList<PastSalesReportDto>();
			for(SoldItems pastSalesHistory:pastSalesHistoryList){

				if (pastSalesHistory.getSale().getDate().getTime() < toDate.getTime()+86400000 && pastSalesHistory.getSale().getDate().getTime()>fromDate.getTime()){

					PastSalesReportDataDto pastSalesReportDataDto =new PastSalesReportDataDto();

					pastSalesReportDataDto.setItemDesc(pastSalesHistory.getItems().getName());
					pastSalesReportDataDto.setQty(pastSalesHistory.getQty());
					pastSalesReportDataDto.setPrice(pastSalesHistory.getPrice());
					pastSalesReportDataDto.setSubtotal(pastSalesHistory.getTotal());


					List<PastSalesReportDataDto> pastSalesReportDataDtoList=map.get(pastSalesHistory.getSale().getId());
					if(pastSalesReportDataDtoList==null){
						pastSalesReportDataDtoList =new ArrayList<PastSalesReportDataDto>();
						pastSalesReportDataDtoList.add(pastSalesReportDataDto);
						map.put(pastSalesHistory.getSale().getId(), pastSalesReportDataDtoList);
						PastSalesReportDto pastSalesReportDto=new PastSalesReportDto();
						pastSalesReportDto.setCusName(pastSalesHistory.getSale().getCusName());
						pastSalesReportDto.setDate(pastSalesHistory.getSale().getDate());
						pastSalesReportDto.setInvoiceNo(pastSalesHistory.getSale().getInvoiceNo());
						pastSalesReportDto.setIsAdvance(pastSalesHistory.getSale().getIsAdvance());
						pastSalesReportDto.setTotal(pastSalesHistory.getSale().getTotal());
						pastSalesReportDto.setCashIn(pastSalesHistory.getSale().getCashIn());
						pastSalesReportDtos.add(pastSalesReportDto);
					}
					else {
						pastSalesReportDataDtoList.add(pastSalesReportDataDto);
					}
				}

			}
			Map<Integer, List<PastSalesReportDataDto>> treeMap = new TreeMap<Integer, List<PastSalesReportDataDto>>(map);
			int n=0;
			for(Map.Entry<Integer, List<PastSalesReportDataDto>> entry : treeMap.entrySet()){
				List<PastSalesReportDataDto> dataDtos=map.get(entry.getKey());
				pastSalesReportDtos.get(n).setPastSalesDataDto(dataDtos);
				n++;
			}
			return pastSalesReportDtos;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;



}
	@Transactional
	public CashFLowDataLists getCashFlowData() {
		try {
		List<Sales> Allsales=salesDao.getAllSale();
		Map<Date,Double> cashInByDate=new HashMap<Date, Double>();
		for(Sales sale:Allsales) {
			Double totalCashInByDate=cashInByDate.get(sdf.parse(sdf.format(sale.getDate())));
			if(totalCashInByDate==null) {
				Double cashIn=sale.getCashIn();
				cashInByDate.put(sdf.parse(sdf.format(sale.getDate())), cashIn);
			}
			else
			{
				totalCashInByDate+=sale.getCashIn();
				cashInByDate.put(sdf.parse(sdf.format(sale.getDate())), totalCashInByDate);
			}
		}

		List<PaidOut> getAllPaidOuts=paidOutDao.getAllPaidOuts();
		Map<Date,Double> cashOutByDate=new HashMap<Date, Double>();
		for(PaidOut paidOut:getAllPaidOuts) {
			Double totalCashOutByDate=0.0;
			totalCashOutByDate=cashOutByDate.get(sdf.parse(sdf.format(paidOut.getDate())));
			if(totalCashOutByDate==null) {
				Double cashOut=paidOut.getAmount();
				cashOutByDate.put(sdf.parse(sdf.format(paidOut.getDate())), cashOut);
			}
			else
			{
				totalCashOutByDate+=paidOut.getAmount();
				cashOutByDate.put(sdf.parse(sdf.format(paidOut.getDate())), totalCashOutByDate);
			}
		}

		Map<String,Map<Integer,Double>> cashOutsByDate=new HashMap<String, Map<Integer, Double>>();


		for(PaidOut paidOut:getAllPaidOuts) {
			Map<Integer,Double> totalCashOutsByDate=null;

			Calendar cal=Calendar.getInstance();
			cal.setTime(paidOut.getDate());

			String monthh = "";
			switch(cal.get(Calendar.MONTH)+1) {
			case 1:
				monthh="Jan";
			break;
			case 2:
				monthh="Feb";
			break;
			case 3:
				monthh="Mar";
			break;
			case 4:
				monthh="Apr";
			break;
			case 5:
				monthh="May";
			break;
			case 6:
				monthh="Jun";
			break;
			case 7:
				monthh="Jul";
			break;
			case 8:
				monthh="Aug";
			break;
			case 9:
				monthh="Sep";
			break;
			case 10:
				monthh="Oct";
			break;
			case 11:
				monthh="Nov";
			break;
			case 12:
				monthh="Dec";
			break;
			}
			String date=monthh+"-"+cal.get(Calendar.YEAR);

			totalCashOutsByDate=cashOutsByDate.get(date);

			if(totalCashOutsByDate==null) {
				totalCashOutsByDate=new HashMap<Integer, Double>();
				totalCashOutsByDate.put(paidOut.getPaidoutItem(), paidOut.getAmount());
				cashOutsByDate.put(date,totalCashOutsByDate);
			}
			else {
				Double total=totalCashOutsByDate.get(paidOut.getPaidoutItem());
				if(total==null) {
				totalCashOutsByDate.put(paidOut.getPaidoutItem(), paidOut.getAmount());
				cashOutsByDate.put(date,totalCashOutsByDate);}
				else {
					total+=paidOut.getAmount();
					totalCashOutsByDate.put(paidOut.getPaidoutItem(), total);
					cashOutsByDate.put(date,totalCashOutsByDate);
				}
			}


		}
		List<PaidOutByMonthDto> paidOutByMonthDtoList=new ArrayList<PaidOutByMonthDto>();
		for(Map.Entry<String,Map<Integer,Double>> entry : cashOutsByDate.entrySet()){
			PaidOutByMonthDto paidOutByMonthDto=new PaidOutByMonthDto();
			paidOutByMonthDto.setDate(entry.getKey());
			paidOutByMonthDto.setItem1(entry.getValue().get(0));
			paidOutByMonthDto.setItem2(entry.getValue().get(1));
			paidOutByMonthDto.setItem3(entry.getValue().get(2));
			paidOutByMonthDto.setItem4(entry.getValue().get(3));
			paidOutByMonthDto.setItem5(entry.getValue().get(4));
			paidOutByMonthDtoList.add(paidOutByMonthDto);
		}

		for(Map.Entry<Date, Double> entry : cashInByDate.entrySet()){
			if(!cashOutByDate.containsKey(entry.getKey())) {
				cashOutByDate.put(entry.getKey(), 0.00);
			}
		}

		for(Map.Entry<Date, Double> entry : cashOutByDate.entrySet()){
			if(!cashInByDate.containsKey(entry.getKey())) {
				cashInByDate.put(entry.getKey(), 0.00);
			}
		}


		List<CashFlowDto> cashFlowDtoList=new ArrayList<CashFlowDto>();
		for(Map.Entry<Date, Double> entry : cashOutByDate.entrySet()){
			CashFlowDto cashFlowDto=new CashFlowDto();
			cashFlowDto.setCashIn(cashInByDate.get(entry.getKey()));
			cashFlowDto.setCashOut(entry.getValue());
			cashFlowDto.setDate(entry.getKey());
			cashFlowDto.setBalance(cashInByDate.get(entry.getKey())-entry.getValue());
			cashFlowDtoList.add(cashFlowDto);
		}

		Map<String,List<CashFlowDto>> categorizedMap=new HashMap<String, List<CashFlowDto>>();
		for(CashFlowDto cashFDto:cashFlowDtoList) {
			Calendar cal=Calendar.getInstance();
			cal.setTime(cashFDto.getDate());
			String monthh = "";
			switch(cal.get(Calendar.MONTH)+1) {
			case 1:
				monthh="Jan";
			break;
			case 2:
				monthh="Feb";
			break;
			case 3:
				monthh="Mar";
			break;
			case 4:
				monthh="Apr";
			break;
			case 5:
				monthh="May";
			break;
			case 6:
				monthh="Jun";
			break;
			case 7:
				monthh="Jul";
			break;
			case 8:
				monthh="Aug";
			break;
			case 9:
				monthh="Sep";
			break;
			case 10:
				monthh="Oct";
			break;
			case 11:
				monthh="Nov";
			break;
			case 12:
				monthh="Dec";
			break;
			}
			String date=monthh+"-"+cal.get(Calendar.YEAR);
			List<CashFlowDto> cashFDtoList=categorizedMap.get(date);

			if(cashFDtoList==null) {
				List<CashFlowDto> cashFDtoLists=new ArrayList<CashFlowDto>();
				cashFDtoLists.add(cashFDto);
				categorizedMap.put(date, cashFDtoLists);
			}
			else {
				cashFDtoList.add(cashFDto);
				categorizedMap.put(date, cashFDtoList);
			}
		}



		List<CashFlowDtoList> cashFlowDtoLists=new ArrayList<CashFlowDtoList>();
		CashFLowDataLists cashFLowDataLis=new CashFLowDataLists();
		for(Map.Entry<String, List<CashFlowDto>> entry : categorizedMap.entrySet()){
			CashFlowDtoList cashFlowDtoListss=new CashFlowDtoList();
			entry.getValue().sort(new Comparator<CashFlowDto>() {
				public int compare(CashFlowDto o2, CashFlowDto o1) {
					return o1.getDate().compareTo(o2.getDate());
				}
			});
			cashFlowDtoListss.setCashFlowDtos(entry.getValue());
			cashFlowDtoListss.setDate(entry.getKey());
			Double totalIn=0.00;
			Double totalOut=0.00;
			Double totalBalance=0.0;
			for(CashFlowDto cashInValue:entry.getValue()) {
				totalIn+=cashInValue.getCashIn();
				totalOut+=cashInValue.getCashOut();

			}
			totalBalance=totalIn-totalOut;
			cashFlowDtoListss.setTotalIn(totalIn);
			cashFlowDtoListss.setTotalOut(totalOut);
			cashFlowDtoListss.setTotalBalance(totalBalance);
			cashFlowDtoLists.add(cashFlowDtoListss);
		}
		cashFlowDtoLists.sort(new Comparator<CashFlowDtoList>() {
			public int compare(CashFlowDtoList o1, CashFlowDtoList o2) {
				return o2.getCashFlowDtos().get(0).getDate().compareTo(o1.getCashFlowDtos().get(0).getDate());
			}
		});

		cashFLowDataLis.setPaidOutByMonthDtoList(paidOutByMonthDtoList);
		cashFLowDataLis.setCashFlowDtoLists(cashFlowDtoLists);
		return cashFLowDataLis;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;

	}}
