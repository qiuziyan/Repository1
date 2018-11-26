package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	public TestController() {
		System.out.println("初始化controller");
		LOGGER.error("info______");
	}
	
	@Autowired
	private Dao d;
	
	@RequestMapping("/")
	public String test() {
		LOGGER.error("info______");
		return  "2323";
	}
}
