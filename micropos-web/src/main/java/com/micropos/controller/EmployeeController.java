package com.micropos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micropos.dto.PaidOutDto;
import com.micropos.dto.UserDetailDto;
import com.micropos.properties.MicroPOSResponse;
import com.micropos.service.EmployeeService;

@Controller
@RequestMapping(value = "/portal/admin")
public class EmployeeController {

	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "employees", method = RequestMethod.GET)
	public String Employees(Model map,
			HttpServletRequest request) {
		return "employees";
	}

	@RequestMapping(value = "newUser", method = RequestMethod.GET)
	public String AddNew(Model map,
			HttpServletRequest request) {
		return "addNewUser";
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestParam("email") String email,@RequestParam("name") String name,@RequestParam("userName") String userName,@RequestParam("password") String password,HttpServletRequest request, Model map) {

		String createdPW = null;
		if (password != null && !password.equalsIgnoreCase("")) {
			createdPW= shaPasswordEncoder.encodePassword(password, null);
	}

		return employeeService.createOrUpdate(name,userName,createdPW,email);
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.POST)
	public String searchUser(@RequestParam(value = "sWord") String searchText,
			@RequestParam(value = "selectOption", required = false) String selectOption,HttpServletRequest request, Model map) {

		String searchword = searchText.trim();
		List<UserDetailDto> dataList = employeeService.searchUser(searchword, selectOption);
		map.addAttribute("searchText", searchText);
		map.addAttribute("data", dataList);

		return "searchresults";

	}







}
