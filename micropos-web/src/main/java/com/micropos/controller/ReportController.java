package com.micropos.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/portal")
public class ReportController {
	@Autowired
	ReportDataManager pdfInvoiceData;

	@RequestMapping(method = RequestMethod.GET, value = "pdftransaction")
		public ModelAndView generatePdfReportForTransactions(@RequestParam(value = "invoiceIds") String invoiceId,
				ModelAndView modelAndView, HttpServletRequest httpServletRequest) throws Exception {
		try {
			Map<String, Object> parameterMap = pdfInvoiceData.getTransactionData(invoiceId);
			modelAndView = new ModelAndView("pdfTransaction", parameterMap);
			return modelAndView;
		}
		catch(Exception e) {
			e.printStackTrace();
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			modelAndView = new ModelAndView("pdfTransaction", parameterMap);
			return modelAndView;
	}}

		@RequestMapping(method = RequestMethod.POST, value = "pdftransactiondummy")
		public ModelAndView generatePdfReportForTransactionsDummy(@RequestParam(value = "invoiceIds") String invoiceId,
				ModelAndView modelAndView, HttpServletRequest httpServletRequest) throws Exception {
		try {
			Map<String, Object> parameterMap = pdfInvoiceData.getTransactionData(invoiceId);
			modelAndView = new ModelAndView("pdfTransaction", parameterMap);
			return modelAndView;
		}
		catch(Exception e) {
			e.printStackTrace();
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			modelAndView = new ModelAndView("pdfTransaction", parameterMap);
			return modelAndView;
	}
}









}