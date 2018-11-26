package com.example.demo;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogDemo2Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogDemo2Application.class);
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(LogDemo2Application.class, args);
		
	}
	
}
