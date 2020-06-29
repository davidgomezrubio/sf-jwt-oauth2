package com.dgr.app.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dgr.app.entities.Company;
import com.dgr.app.paginator.PageRender;
import com.dgr.app.services.ICompanyService;

@Controller
@SessionAttributes
@RequestMapping("/companies")
public class CompaniesController {

	@Autowired
	private ICompanyService companyService;

	@GetMapping("{id}")
	public String company(@PathVariable(value = "id") Long id, Model model) {

		model.addAttribute("title", "Club: " + id);

		return "companies/company";

	}
	
	
	@GetMapping("")
	public String index(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 15);

		Page<Company> companies = companyService.findAll(pageRequest);

		PageRender<Company> pageRender = new PageRender<>("companies/index", companies);

		
		model.addAttribute("title", "index");
		model.addAttribute("companies", companies);
		model.addAttribute("page", pageRender);
		

		return "companies/index";
	}
	
	
	

	@GetMapping("/form")
	public String form(Model model) {

		model.addAttribute("title", "Form Company");

		Company company = new Company();
		model.addAttribute("company", company);

		return "companies/form";
	}

	@PostMapping("/form")
	public String save(@Valid Company company, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("title", "List Companies");
			return "companies/form";
		}

		companyService.save(company);
		status.setComplete();
		flash.addFlashAttribute("success", "Action successfully!");

		return "redirect:/companies";
	}

	@GetMapping("/form/{id}")
	public String edit(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		model.addAttribute("title", "Edit Copany");

		Company company = null;

		if (id > 0) {
			company = companyService.findOne(id);
			if(company == null) {
				flash.addFlashAttribute("error", "Error!!! - The company_id no existe en la DDBB");
				return "redirect:/companies";
			}
		} else {
			flash.addFlashAttribute("error", "Error!!! - The company_id no puede ser 0");
			return "redirect:/companies";
		}
		model.addAttribute("company", company);

		return "companies/form";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			companyService.delete(id);
			flash.addFlashAttribute("success", "Company deleted!!!");
		}

		return "redirect:/companies";
	}

}
