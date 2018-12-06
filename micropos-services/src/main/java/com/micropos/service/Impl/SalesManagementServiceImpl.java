package com.micropos.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micropos.dao.PastSalesDao;
import com.micropos.dao.SalesDao;
import com.micropos.domain.Items;
import com.micropos.domain.Sales;
import com.micropos.domain.SoldItems;
import com.micropos.dto.SaleDto;
import com.micropos.dto.SoldItemDto;
import com.micropos.service.SalesManagementService;

@Service
public class SalesManagementServiceImpl implements SalesManagementService{
	@Autowired
	SalesDao salesDao;

	@Autowired
	PastSalesDao pastSalesDao;

	@Transactional
	public Integer getMaximumInvoiceNumber() {
		Integer maximumInvoiceNo=salesDao.maximumInvoiceNumber();
		return maximumInvoiceNo;
	}

	@Transactional
	public Double SaveFullTransaction(List<SoldItemDto> soldItemDtos, Double padiAmount, String cusName, Double total) {
		if(soldItemDtos==null) {
			throw new RuntimeException("There are no items to proceed the transaction");
		}


		if(padiAmount<=0 ||padiAmount==null||total>padiAmount) {
			throw new RuntimeException("You must pay the full payment");
		}

		try {

		Double balance=padiAmount-total;
		Sales sale= new Sales();

		sale.setId(null);
		sale.setDate(new Date());
		sale.setCusName(cusName);
		sale.setCashIn(total);
		sale.setIsAdvance(false);
		sale.setIsDelete(false);
		sale.setTotal(total);
		sale.setPaidAmount(padiAmount);
		sale.setBalance(balance);
		sale.setIsEmp(true);
		salesDao.saveSales(sale);
		String invoiceNo=String.format("M%05d", sale.getId());
		sale.setInvoiceNo(invoiceNo);
		sale.setId(sale.getId());
		salesDao.saveSales(sale);

		Iterator<SoldItemDto> soldeItemDtoIterator=soldItemDtos.iterator();

		while(soldeItemDtoIterator.hasNext()) {
			SoldItemDto soldItemDto=soldeItemDtoIterator.next();
			SoldItems soldItem=new SoldItems();
			soldItem.setId(null);
			soldItem.setItemDesc(soldItemDto.getItemDesc());
			Items item=new Items();
			item.setId(soldItemDto.getItemId());
			soldItem.setItems(item);
			soldItem.setPrice(soldItemDto.getPrice());
			soldItem.setQty(soldItemDto.getQty());
			soldItem.setSale(sale);
			soldItem.setTotal(soldItemDto.getTotal());
			salesDao.saveSoldItem(soldItem);
		}
		return balance;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Transactional
	public void SaveAdvanceTransaction(List<SoldItemDto> soldItemDtos, Double padiAmount, String cusName,
			Double total) {
		if(soldItemDtos==null) {
			throw new RuntimeException("There are no items to proceed the transaction");
		}

		if(padiAmount<=0 ||padiAmount==null) {
			throw new RuntimeException("Invalid transaction");
		}


		if(total<=padiAmount) {
			throw new RuntimeException("Paid amount is greater than total, Please use the full payment method");
		}

		try {

		Double balance=0.0;
		Sales sale= new Sales();

		sale.setId(null);
		sale.setDate(new Date());
		sale.setCusName(cusName);
		sale.setCashIn(padiAmount);
		sale.setIsAdvance(true);
		sale.setIsDelete(false);
		sale.setTotal(total);
		sale.setPaidAmount(padiAmount);
		sale.setBalance(balance);
		sale.setIsEmp(true);
		salesDao.saveSales(sale);
		String invoiceNo=String.format("M%05d", sale.getId());
		sale.setInvoiceNo(invoiceNo);
		sale.setId(sale.getId());
		salesDao.saveSales(sale);

		Iterator<SoldItemDto> soldeItemDtoIterator=soldItemDtos.iterator();

		while(soldeItemDtoIterator.hasNext()) {
			SoldItemDto soldItemDto=soldeItemDtoIterator.next();
			SoldItems soldItem=new SoldItems();
			soldItem.setId(null);
			soldItem.setItemDesc(soldItemDto.getItemDesc());
			Items item=new Items();
			item.setId(soldItemDto.getItemId());
			soldItem.setItems(item);
			soldItem.setPrice(soldItemDto.getPrice());
			soldItem.setQty(soldItemDto.getQty());
			soldItem.setSale(sale);
			soldItem.setTotal(soldItemDto.getTotal());
			salesDao.saveSoldItem(soldItem);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	@Transactional
	public void CompleteAdvance(Integer invoiceId) {
		Sales advanceSale=salesDao.getAdvanceSale(invoiceId);
		if(advanceSale==null) {
			throw new RuntimeException("Invalid Invoice Id, Please check again the invoice number");
		}
		try {
			Sales completedSale=new Sales();
			Double value=advanceSale.getTotal()-advanceSale.getCashIn();
			completedSale.setId(null);
			completedSale.setCashIn(value);
			completedSale.setCusName(advanceSale.getCusName());
			completedSale.setDate(new Date());
			completedSale.setInvoiceNo(advanceSale.getInvoiceNo());
			completedSale.setIsAdvance(false);
			completedSale.setIsDelete(false);
			completedSale.setPaidAmount(value);
			completedSale.setTotal(value);
			completedSale.setBalance(0.00);
			completedSale.setIsEmp(false);
			advanceSale.setIsAdvance(false);

			salesDao.saveSales(advanceSale);
			salesDao.saveSales(completedSale);

			List<SoldItems> soldItems=pastSalesDao.getAllPastSalesHistorySaleId(advanceSale.getId());
			for(SoldItems soldItemss:soldItems) {
				SoldItems soldItemsss=new SoldItems();
				soldItemsss.setItemDesc(soldItemss.getItemDesc());
				soldItemsss.setItems(soldItemss.getItems());
				soldItemsss.setPrice(soldItemss.getPrice());
				soldItemsss.setQty(soldItemss.getQty());
				soldItemsss.setSale(completedSale);
				soldItemsss.setTotal(soldItemss.getTotal());
				soldItemsss.setId(null);
				salesDao.saveSoldItem(soldItemsss);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public String SaveTransaction(List<SoldItemDto> soldItemDtos, Double padiAmount, String cusName, Double total) {
		if(soldItemDtos==null) {
			throw new RuntimeException("There are no items to proceed the transaction");
		}

		if(padiAmount<=0 ||padiAmount==null) {
			throw new RuntimeException("Invalid transaction");
		}


		if(total>padiAmount) {
			try {

				Double balance=0.0;
				Sales sale= new Sales();

				sale.setId(null);
				sale.setDate(new Date());
				sale.setCusName(cusName);
				sale.setCashIn(padiAmount);
				sale.setIsAdvance(true);
				sale.setIsDelete(true);
				sale.setTotal(total);
				sale.setPaidAmount(padiAmount);
				sale.setBalance(balance);
				sale.setIsEmp(false);
				salesDao.saveSales(sale);
				String invoiceNo=String.format("D%05d", sale.getId());
				sale.setInvoiceNo(invoiceNo);
				sale.setId(sale.getId());
				salesDao.saveSales(sale);

				Iterator<SoldItemDto> soldeItemDtoIterator=soldItemDtos.iterator();

				while(soldeItemDtoIterator.hasNext()) {
					SoldItemDto soldItemDto=soldeItemDtoIterator.next();
					SoldItems soldItem=new SoldItems();
					soldItem.setId(null);
					soldItem.setItemDesc(soldItemDto.getItemDesc());
					Items item=new Items();
					item.setId(soldItemDto.getItemId());
					soldItem.setItems(item);
					soldItem.setPrice(soldItemDto.getPrice());
					soldItem.setQty(soldItemDto.getQty());
					soldItem.setSale(sale);
					soldItem.setTotal(soldItemDto.getTotal());
					salesDao.saveSoldItem(soldItem);
				}
				return sale.getInvoiceNo();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
		}
		else if(total<=padiAmount){
		try {

			Double balance=padiAmount-total;
			Sales sale= new Sales();

			sale.setId(null);
			sale.setDate(new Date());
			sale.setCusName(cusName);
			sale.setCashIn(total);
			sale.setIsAdvance(false);
			sale.setIsDelete(true);
			sale.setTotal(total);
			sale.setPaidAmount(padiAmount);
			sale.setBalance(balance);
			sale.setIsEmp(false);

			salesDao.saveSales(sale);
			String invoiceNo=String.format("D%05d", sale.getId());
			sale.setInvoiceNo(invoiceNo);
			sale.setId(sale.getId());
			salesDao.saveSales(sale);

			Iterator<SoldItemDto> soldeItemDtoIterator=soldItemDtos.iterator();

			while(soldeItemDtoIterator.hasNext()) {
				SoldItemDto soldItemDto=soldeItemDtoIterator.next();
				SoldItems soldItem=new SoldItems();
				soldItem.setId(null);
				soldItem.setItemDesc(soldItemDto.getItemDesc());
				Items item=new Items();
				item.setId(soldItemDto.getItemId());
				soldItem.setItems(item);
				soldItem.setPrice(soldItemDto.getPrice());
				soldItem.setQty(soldItemDto.getQty());
				soldItem.setSale(sale);
				soldItem.setTotal(soldItemDto.getTotal());
				salesDao.saveSoldItem(soldItem);
			}
			return sale.getInvoiceNo();
			}
			catch(Exception e) {
				e.printStackTrace();

			}
	}
		return null;




	}



}
