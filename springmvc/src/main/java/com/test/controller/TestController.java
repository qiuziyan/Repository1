package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.Dao;

@Controller
public class TestController {
	@Autowired
	private Dao dao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request) throws Exception {		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			throw new Exception();
		}else {
			return new ModelAndView("show","name","name") ;
		}
		
	}
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			System.out.println("user+++++++++++++++++");
			session.setAttribute("user", "user");
		}else {
			
		}
		return new ModelAndView("show","name","name") ;
	}
}
