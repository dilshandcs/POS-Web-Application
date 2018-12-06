package com.micropos.service.Impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micropos.dao.InoviceDao;
import com.micropos.dao.PastSalesDao;
import com.micropos.dao.SalesDao;
import com.micropos.domain.Sales;
import com.micropos.domain.SoldItems;
import com.micropos.dto.PastSalesReportDataDto;
import com.micropos.dto.SoldItemDto;
import com.micropos.dto.SoldItemDtoList;
import com.micropos.dto.TransactionDataDto;
import com.micropos.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	SalesDao salesDao;

	@Autowired
	PastSalesDao PastSalesDao;

	@Transactional
	public SoldItemDtoList TransactionData(String invoiceId) {
		List<Sales> sale=salesDao.getSale(invoiceId);
		SoldItemDtoList transactionDataDto=new SoldItemDtoList();
		List<SoldItems> soldItems=PastSalesDao.getAllPastSalesHistorySaleId(sale.get(0).getId());
		if(sale.size()==1) {
			transactionDataDto.setCusName(sale.get(0).getCusName());


Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String s = formatter.format(sale.get(0).getDate());


			transactionDataDto.setDate(s);
			transactionDataDto.setTotal(sale.get(0).getTotal());
			Double totaal=sale.get(0).getTotal();
			Double paidaamount=sale.get(0).getPaidAmount();
			if(totaal>paidaamount) {
				transactionDataDto.setBalance(0.00);
				transactionDataDto.setAmountToPay(totaal-paidaamount);
				transactionDataDto.setIsAdvanced(true);
			}
			else
			{
				transactionDataDto.setBalance(paidaamount-totaal);
				transactionDataDto.setAmountToPay(0.00);
				transactionDataDto.setIsAdvanced(false);
			}


			transactionDataDto.setPaidAmount(sale.get(0).getPaidAmount());
			transactionDataDto.setInvoiceNo(invoiceId);
			List<SoldItemDto> pastSalesReportDtos=new ArrayList<SoldItemDto>();

			for(SoldItems soldItemss:soldItems) {
				SoldItemDto pastSalesReportDto=new SoldItemDto();
				pastSalesReportDto.setItemDesc(soldItemss.getItemDesc());
				pastSalesReportDto.setPrice(soldItemss.getPrice());
				pastSalesReportDto.setQty(soldItemss.getQty());
				pastSalesReportDto.setTotal(soldItemss.getTotal());
				pastSalesReportDtos.add(pastSalesReportDto);
			}
			transactionDataDto.setSoldItemDtos(pastSalesReportDtos);
		}
		else {
			transactionDataDto.setCusName(sale.get(0).getCusName());


			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s = formatter.format(sale.get(1).getDate());


						transactionDataDto.setDate(s);
						transactionDataDto.setTotal(sale.get(0).getTotal());

							transactionDataDto.setBalance(0.00);
							transactionDataDto.setAmountToPay(0.00);

						transactionDataDto.setPaidAmount(sale.get(0).getTotal());
						transactionDataDto.setInvoiceNo(invoiceId);
						List<SoldItemDto> pastSalesReportDtos=new ArrayList<SoldItemDto>();

						for(SoldItems soldItemss:soldItems) {
							SoldItemDto pastSalesReportDto=new SoldItemDto();
							pastSalesReportDto.setItemDesc(soldItemss.getItemDesc());
							pastSalesReportDto.setPrice(soldItemss.getPrice());
							pastSalesReportDto.setQty(soldItemss.getQty());
							pastSalesReportDto.setTotal(soldItemss.getTotal());
							pastSalesReportDtos.add(pastSalesReportDto);
						}
						transactionDataDto.setSoldItemDtos(pastSalesReportDtos);
		}

		return transactionDataDto;
	}

}
