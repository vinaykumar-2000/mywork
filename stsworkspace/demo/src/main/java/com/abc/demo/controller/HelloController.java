package com.abc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	public String sayHello(Model model) {
		String myTitle = "Hello Spring boot";
		model.addAttribute("title",myTitle);
		
		return "hello";
		
	}

}
