package com.micropos.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micropos.dao.UserDao;
import com.micropos.dto.EmployeeDto;
import com.micropos.dto.EmployeeDtoList;
import com.micropos.dto.ItemDto;
import com.micropos.dto.SalesEmployeeDto;
import com.micropos.dto.UserDetailDto;
import com.micropos.properties.MicroPOSResponse;
import com.micropos.security.AuthenticationUserDetails;
import com.micropos.service.EmployeeService;
import com.micropos.service.ItemService;
import com.micropos.service.SalesManagementService;

@Controller
@RequestMapping(value="/portal")
public class LoginController {

	@Autowired
	SalesManagementService salesManagementService;

	@Autowired
	ItemService ItemService;


	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value= {"/","/login"}, method=RequestMethod.GET)
	public String login(Model map) {
		return "login";
	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String Home(Map<String, Object> modelN, HttpSession session) {

		AuthenticationUserDetails userObj = (AuthenticationUserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		String usertype = userObj.getUserType();
		String lonigUser = userObj.getName();
		String role[] = userObj.getUserType().split("_");
		String userRole = role[1];
		Integer maxInvoice=salesManagementService.getMaximumInvoiceNumber();
		List<ItemDto> itemList=ItemService.getAllItems();
		List<Object> permissionList=userObj.getMicroPosPermissionCollection();
		List<EmployeeDtoList> employeeRecors=employeeService.employeeRecors(userObj.getId());
		List<SalesEmployeeDto> salesEmployeeDtos=employeeService.getLastTenSalesEmployee();
		List<UserDetailDto> employees=employeeService.getAll();
		String invoiceNo=String.format("M%05d", maxInvoice+1);
		modelN.put("lonigUser", lonigUser);
		modelN.put("userRole", userRole);
		modelN.put("usertype", usertype);
		modelN.put("inoviceNo", invoiceNo);
		modelN.put("employeeRecors", employeeRecors);
		modelN.put("itemList", itemList);
		modelN.put("employees", employees);
		modelN.put("prevInv", maxInvoice);
		modelN.put("salesEmployeeDtos", salesEmployeeDtos);


		return "home";

	}

	@RequestMapping(value="/employeePortion", method = RequestMethod.POST)
	@ResponseBody
	public MicroPOSResponse EmploeePortion(@RequestParam("employeesel") Integer empNo,@RequestParam("prevInv") Integer prevInv) {

		try {
			employeeService.AddEmployeePortion(empNo, prevInv);
			return new MicroPOSResponse(null, "Successfully Added the Employee's Portion", true, "Successfull");
		}
		catch(Exception e) {
			return new MicroPOSResponse(null, e.getMessage(), false, "Info");
		}

	}
}
