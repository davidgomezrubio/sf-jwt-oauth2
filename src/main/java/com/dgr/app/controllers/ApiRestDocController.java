package com.dgr.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api-rest-doc")
public class ApiRestDocController {
	
	
	@GetMapping("")
	public String index(Model model) {
		
		model.addAttribute("title", "ApiRest Documentation");
		
		return "/api-rest-doc/index";
	}
	
	

}
