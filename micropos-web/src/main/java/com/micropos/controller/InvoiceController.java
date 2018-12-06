package com.micropos.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micropos.dto.SaleDto;
import com.micropos.service.InvoiceService;
@Controller
@RequestMapping(value="/portal")
public class InvoiceController implements Serializable{/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	InvoiceService invoiceService;

	@RequestMapping(value = "/searchAdvanceInvoice", method = RequestMethod.GET)
	@ResponseBody
	public List<SaleDto> SearchAdvancedInvoice(@RequestParam(value = "query") String query,HttpServletRequest request) {

		List<SaleDto> searchResults= invoiceService.SearchAdvancedInvoice(query);

		return searchResults;

	}

	@RequestMapping(value = "/searchInvoicess", method = RequestMethod.GET)
	@ResponseBody
	public List<SaleDto> SearchInvoicees(@RequestParam(value = "query") String query,HttpServletRequest request) {

		List<SaleDto> searchResults= invoiceService.SearchInvoicees(query);

		return searchResults;

	}



}
