package io.pivotal.microservices.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.pivotal.microservices.services.AccountRepoService;

@Controller
public class HomeController {
	protected Logger logger = Logger.getLogger(HomeController.class
			.getName());
	@Autowired
	protected AccountRepoService accountService;
	
	@RequestMapping("/")
	public String home() {
		logger.info("go to index");
		return "index";
	}

}
