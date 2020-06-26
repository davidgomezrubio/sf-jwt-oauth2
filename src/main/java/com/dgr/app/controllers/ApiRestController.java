package com.dgr.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dgr.app.entities.Company;
import com.dgr.app.services.ICompanyService;

@RestController
@RequestMapping("/api-rest/companies")
public class ApiRestController {
	
	
	@Autowired
	private ICompanyService companyService;
	
	
	@GetMapping
	@ResponseBody
	public List<Company> apiRest(Model model) {
		
		return  companyService.findAll();
	}

}
