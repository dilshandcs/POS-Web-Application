package com.micropos.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micropos.dto.CashFlowDtoList;
import com.micropos.dto.PastSalesFilterReportDto;
import com.micropos.dto.PastSalesInfoDto;
import com.micropos.dto.PastSalesReportDto;
import com.micropos.service.PastSalesService;

@Controller
@RequestMapping(value = "/portal/admin")
public class PastSalesController {

	@Autowired
	PastSalesService pastSalesService;

	@RequestMapping(value = "pastSales", method = RequestMethod.GET)
	public String PastSales(Model map) {
		PastSalesInfoDto pastSalesInfoDto=pastSalesService.getDataForSalesReport();
		map.addAttribute("data", pastSalesInfoDto);

		return "pastSales";
	}

	@RequestMapping(value = "searchPastSales", method = RequestMethod.POST)
	@ResponseBody
	public List<PastSalesReportDto> GetPastSalesReportData(
			@ModelAttribute PastSalesFilterReportDto pastSalesReportFilterInfoDto, Model map,
			HttpServletRequest request) {

		List<PastSalesReportDto> plannerReportDtoList = pastSalesService
				.getPastSalesForReport(pastSalesReportFilterInfoDto);
		request.getSession().setAttribute("salesReportDataDtoList", plannerReportDtoList);
		request.getSession().setAttribute("FilteredDataToPrint", plannerReportDtoList);
		return plannerReportDtoList;
	}

//	filterPastSalesReportByMonths
//	salesReportDataDtoList
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "filterPastSalesReportByMonths")
	@ResponseBody
	public List<PastSalesReportDto> filterPlannerReport(HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest, @RequestParam(value = "fromYearMonth") String fromYearMonth,
			@RequestParam(value = "toYearMonth") String toYearMonth, HttpServletRequest request) {

		List<PastSalesReportDto> plannerReportDtoList = (List<PastSalesReportDto>) request.getSession()
				.getAttribute("salesReportDataDtoList");
		if (plannerReportDtoList == null) {
			plannerReportDtoList = new ArrayList<>();
		}
		List<PastSalesReportDto> FilteredData = new ArrayList<PastSalesReportDto>();

		Calendar c = Calendar.getInstance();
		String[] from = fromYearMonth.split("/");
		if(from.length==3) {
			c.set(Integer.parseInt(from[0]), Integer.parseInt(from[1]) - 1, Integer.parseInt(from[2]));
		}else {
			c.set(Integer.parseInt(from[0]), Integer.parseInt(from[1]) - 1, 1);
		}
		Date fromDate = c.getTime();

		String[] to = toYearMonth.split("/");
		if(to.length==3) {
			c.set(Integer.parseInt(to[0]), Integer.parseInt(to[1]) - 1, Integer.parseInt(to[2]));
		}else {
			c.set(Integer.parseInt(to[0]), Integer.parseInt(to[1]) - 1, 1);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		}

		Date toDate = c.getTime();

		for (PastSalesReportDto plannerReportDto : plannerReportDtoList) {

				if (fromDate.getTime() <= plannerReportDto.getDate().getTime() && toDate.getTime()+86400000 >= plannerReportDto.getDate().getTime()) {
					FilteredData.add(plannerReportDto);
				}
			}

		request.getSession().setAttribute("FilteredDataToPrint", FilteredData);

		return FilteredData;
	}



}
