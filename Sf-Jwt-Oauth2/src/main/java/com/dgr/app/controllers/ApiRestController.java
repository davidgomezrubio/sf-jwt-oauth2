package com.dgr.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgr.app.entities.Company;
import com.dgr.app.services.ICompanyService;
import com.dgr.app.view.xml.CompanyList;

@RestController
@RequestMapping("/api-rest")
public class ApiRestController {
	
	
	@Autowired
	private ICompanyService companyService;
	
	
	@GetMapping(value = "/companies")
	public CompanyList listCompanies() {
		System.out.println("We are in listCompanies");
		return new CompanyList(companyService.findAll());
	}
	
	@PostMapping(value = "/companies")
	@Secured("ROLE_USER")
	public CompanyList postCompanies() {
		System.out.println("We are in postCompaniesS");
		return new CompanyList(companyService.findAll());
	}
	
	
	@GetMapping("/companies/{id}")
	@Secured("ROLE_ADMIN")
	public Company showCompany(@PathVariable(value = "id") Long id) {
		
		System.out.println("We are in showCompany");

		Company company = null;

		if (id > 0) {
			company = companyService.findOne(id);
			if(company == null) {
				System.out.println("No se encuentra la empresa requerida");
			}
		} else {
			System.out.println("Error!!! - The company_id no puede ser 0");
		}

		return company;

	}
	
	
	
	

}
