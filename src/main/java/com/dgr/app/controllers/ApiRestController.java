package com.dgr.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgr.app.services.ICompanyService;
import com.dgr.app.view.xml.CompanyList;

@RestController
@RequestMapping("/api-rest/companies")
public class ApiRestController {
	
	
	@Autowired
	private ICompanyService companyService;
	
	
	@GetMapping(value = "list")
	public CompanyList apiRest() {
		return new CompanyList(companyService.findAll());
	}

}
