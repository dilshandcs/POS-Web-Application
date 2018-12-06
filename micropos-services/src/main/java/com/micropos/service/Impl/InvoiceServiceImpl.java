package com.micropos.service.Impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.micropos.dao.EmployeeDao;
import com.micropos.dao.InoviceDao;
import com.micropos.dao.UserDao;
import com.micropos.domain.Employee;
import com.micropos.domain.Sales;
import com.micropos.domain.SoldItems;
import com.micropos.domain.User;
import com.micropos.dto.SaleDto;
import com.micropos.email.dto.EmailMetaData;
import com.micropos.email.publisher.EventPublisher;
import com.micropos.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	InoviceDao inoviceDao;

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	UserDao userDao;


	@Autowired
	EventPublisher eventPublisher;

	@Value("${void.email.subject}")
	private String voidEmailSubject;

	@Value("${void.email.template}")
	private String voidTemplateFile;

	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	@Transactional
	public List<SaleDto> SearchAdvancedInvoice(String query) {
		List<SaleDto> resultList=new ArrayList<SaleDto>();
		List<Sales> listOfAdvanceSales = inoviceDao.SearchAdvanceInvoices(query);

		Iterator<Sales> advanceSalesIterator=listOfAdvanceSales.iterator();
		while(advanceSalesIterator.hasNext()) {
			Sales advanceSales=advanceSalesIterator.next();
			SaleDto saleDto=new SaleDto();
			saleDto.setId(advanceSales.getId());
			saleDto.setInvoiceNo(advanceSales.getInvoiceNo());
			saleDto.setInvoiceDisplayValue(advanceSales.getInvoiceNo());
			saleDto.setAdvanceInvoiceNumber(advanceSales.getId());

			resultList.add(saleDto);
		}
		return 	resultList;
	}

	@Transactional
	public List<SaleDto> SearchInvoicees(String query) {
		List<Sales> listOfAdvanceSales = inoviceDao.SearchInvoices(query);
		List<SaleDto> resultList=new ArrayList<SaleDto>();
		Map<String,SaleDto> mapSales=new HashMap<String, SaleDto>();
		for(Sales sale:listOfAdvanceSales) {

			if(!mapSales.containsKey(sale.getInvoiceNo())) {
				SaleDto saleDto=new SaleDto();
				saleDto.setId(sale.getId());
				saleDto.setInvoiceNo(sale.getInvoiceNo());
				saleDto.setAllInvoiceIdDisplayValues(sale.getInvoiceNo());
				saleDto.setAllInvoiceNumbers(sale.getInvoiceNo());
				mapSales.put(sale.getInvoiceNo(), saleDto);
				resultList.add(saleDto);
			}
		}
		return 	resultList;
	}


	@Transactional
	public void voidInvoice(String voidinvoiceId) {
		List<Sales> sales=inoviceDao.getInvoice(voidinvoiceId);
		if(sales==null) {
			throw new RuntimeException("Error. Please contact adminitrator");
		}

		try {
			for(Sales sale:sales) {
			sale.setIsDelete(true);
			}

			List<Employee> emp=employeeDao.getEmployeeBySale(voidinvoiceId);
			if(emp!=null) {
				User usr=userDao.getuserbyid(emp.get(0).getUser().getId());
				eventPublisher.proceedEmailEvent(createEmployeeEmailMetaData(usr.getEmail(),usr.getName(), voidinvoiceId, df.format(sales.get(0).getDate())));
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}



	}

	private EmailMetaData createEmployeeEmailMetaData(final String email, final String name, final String invNo, final String date) {

		EmailMetaData metaData = new EmailMetaData();
		metaData.setToEmailAddresses(email);
		metaData.setVmFile(voidTemplateFile);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", name);
		data.put("invNo", invNo);
		data.put("date", date);
		metaData.setData(data);

		metaData.setSubject(voidEmailSubject);
		return metaData;
	}



}
