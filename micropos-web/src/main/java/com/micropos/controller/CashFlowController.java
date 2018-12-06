package com.micropos.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.micropos.dto.CashFLowDataLists;
import com.micropos.dto.CashFlowDtoList;
import com.micropos.service.PastSalesService;

@Controller
@RequestMapping(value = "/portal/admin")
public class CashFlowController implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	PastSalesService pastSalesService;

	@RequestMapping(value = "cashFlow", method = RequestMethod.GET)
	public String CashFlowStatement(Model map,
			HttpServletRequest request) {

		CashFLowDataLists cashFlowDtoList = pastSalesService
				.getCashFlowData();

		map.addAttribute("cashFlowDtoList", cashFlowDtoList);
		return "cashFlow";
	}

	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String Statistics(Model map,
			HttpServletRequest request) {

		CashFLowDataLists statData = pastSalesService
				.getCashFlowData();

		map.addAttribute("statData", statData);
		return "stat";
	}



}
