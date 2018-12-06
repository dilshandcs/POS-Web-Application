package com.micropos.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micropos.dto.ItemDto;
import com.micropos.dto.ItemDtoList;
import com.micropos.properties.MicroPOSResponse;
import com.micropos.security.AuthenticationUserDetails;
import com.micropos.service.InvoiceService;
import com.micropos.service.ItemService;
import com.micropos.service.UserManagementService;

@Controller
@RequestMapping(value = "/portal/admin")
public class AdminController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserManagementService userManagementService;

	@Autowired
	ItemService itemService;

	@Autowired
	InvoiceService invoiceService;

	@RequestMapping(value = "/changePassword" , method = RequestMethod.GET)
	public String setANewPassword(){

		return "change_password_panel";
	}

	@RequestMapping (value = "/checkCurrentPassword" , method = RequestMethod.POST)
	@ResponseBody
	public boolean CheckOldPassword(@RequestParam(value = "password" , required = true) final String password ){
		AuthenticationUserDetails user = (AuthenticationUserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		boolean exist = userManagementService.checkOldPassword(user.getId(), password);

		return exist;
	}

	@RequestMapping (value = "/setNewPassword" , method = RequestMethod.POST)
	@ResponseBody
	public MicroPOSResponse changePassword(@RequestParam(value ="confirmedPassword", required = true) final String newPassword ,
			@RequestParam(value ="password") final String currentPassword){
		AuthenticationUserDetails user = (AuthenticationUserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

	    try {
			userManagementService.changePassword(user.getId(),currentPassword,newPassword);
			return new MicroPOSResponse(null, null, true, "Success");
	    } catch (Exception e) {
			return new MicroPOSResponse(null, e.getMessage(), false, "Warning");
		}

	}

	@RequestMapping(value = "/stockManager" , method = RequestMethod.GET)
	public String getLocationList(HttpServletRequest request,ModelMap model){
		List<ItemDto> itemList =  (List<ItemDto>) itemService.getAllItems();
		model.addAttribute("itemList", itemList);
		return "itemMan";
	}

	@RequestMapping(value = "/addItems" , method = RequestMethod.POST)
	@ResponseBody
	public MicroPOSResponse addItems(HttpServletRequest request,@ModelAttribute ItemDtoList itemDtoList){

		try{
			itemService.updateItems(itemDtoList);

		return new MicroPOSResponse(null,"Successfully Updated",true,"Success");

		}catch (Exception e) {
			return new MicroPOSResponse(null, e.getMessage(), false, "Error");
		}
	}

	@RequestMapping(value = "/removeAnItem" , method = RequestMethod.POST)
	@ResponseBody
	public MicroPOSResponse removeAnItem(@RequestParam(value = "itemId") Integer itemId){

		try{
			itemService.removeAnItem(itemId);
		return new MicroPOSResponse(null,"Successfully deleted the item",true,"Success");
		}
	catch (Exception e) {
		return new MicroPOSResponse(null, e.getMessage(), false, "Error");
	}

	}



	@RequestMapping(value="/voidInvoice", method = RequestMethod.POST)
	@ResponseBody
	public MicroPOSResponse VoidInvoice(@RequestParam(value="voidinvoiceId") String voidinvoiceId) {

try {
			invoiceService.voidInvoice(voidinvoiceId);
			return new MicroPOSResponse(null, "Successfully deleted the invoice", true, "Successfull");
		}
		catch(Exception e) {
			return new MicroPOSResponse(null, e.getMessage(), false, "Info");
		}

	}


}
