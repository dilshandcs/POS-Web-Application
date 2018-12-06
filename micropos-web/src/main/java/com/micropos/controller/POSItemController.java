package com.micropos.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micropos.dto.ItemDto;
import com.micropos.service.ItemService;
import com.micropos.service.SalesManagementService;

@Controller
@RequestMapping(value="/portal")
public class POSItemController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/searchItem", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemDto> searchJobPart(@RequestParam(value = "query") String query,HttpServletRequest request) {

		List<ItemDto> searchResults= itemService.searchStock(query);

		return searchResults;

	}

	@RequestMapping(value = "/checkCodeAvailability", method = RequestMethod.POST)
	@ResponseBody
	public ItemDto checkPartAvailability(@RequestParam(value = "code") String itemId, Model map) {

		String itemNumber = itemId.trim();
		ItemDto data = itemService.getPartDetailsByItemNumber(itemNumber);
		return data;

	}
}
