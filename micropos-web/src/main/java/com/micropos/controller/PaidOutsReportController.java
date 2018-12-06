package com.micropos.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micropos.dto.PastPaidOutsDtoList;
import com.micropos.dto.PastPaidOutsFilterReportDto;
import com.micropos.service.PastPaidOutService;

@Controller
@RequestMapping(value = "/portal/admin")
public class PaidOutsReportController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	PastPaidOutService pastPaidOutService;

	@RequestMapping(value = "paidoutreport", method = RequestMethod.GET)
	public String pastSales(Model map) {
/*		List<PaidOutDto> paidOutDtoList=pastPaidOutService.getDataForPaidOutsReport();
		map.addAttribute("data", paidOutDtoList);*/

		return "paidout";
	}

	@RequestMapping(value = "searchPaidouts", method = RequestMethod.POST)
	@ResponseBody
	public List<PastPaidOutsDtoList> getPaidOutsReportData(
			@ModelAttribute PastPaidOutsFilterReportDto pastPaidOutsFilterReportDto, Model map,
			HttpServletRequest request) {

		List<PastPaidOutsDtoList> paidOutDtoList = pastPaidOutService.getPaidOutsForReport(pastPaidOutsFilterReportDto);
		request.getSession().setAttribute("paidOutsReportDataDtoList", paidOutDtoList);
		request.getSession().setAttribute("FilteredPaidOutDataToPrint", paidOutDtoList);
		return paidOutDtoList;
	}


}
