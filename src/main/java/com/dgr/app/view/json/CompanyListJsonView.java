package com.dgr.app.view.json;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.dgr.app.entities.Company;


@Component("companies/index.json")
@SuppressWarnings("unchecked")
public class CompanyListJsonView extends MappingJackson2JsonView {

		
	@Override
	protected Object filterModel(Map<String, Object> model) {
		
		model.remove("title");
		model.remove("page");
		
		
		Page<Company> companies = (Page<Company>) model.get("companies");
		
		model.remove("companies");
		
		model.put("companies", companies.getContent());

		return super.filterModel(model);
	}

	
}
