package com.micropos.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	@RequestMapping(value = {"/"}, method = { RequestMethod.GET, RequestMethod.POST })
	 public String redirect(HttpServletRequest request) {
		LOGGER.info("In Redirect Controller ---> Redirect to the /portal/login page");
	      return "redirect:/portal/";
	   }
}
