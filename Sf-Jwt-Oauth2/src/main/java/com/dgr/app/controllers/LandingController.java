package com.dgr.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {
	
	@GetMapping({"", "/"})
	public String home(Model model) {
		
		model.addAttribute("title", "Landing");
		
		return "/landing/index";
	}

}
