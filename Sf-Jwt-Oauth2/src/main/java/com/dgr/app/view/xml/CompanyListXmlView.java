package com.dgr.app.view.xml;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.dgr.app.entities.Company;

@Component("companies/index")
public class CompanyListXmlView extends MarshallingView {

	
	@Autowired
	public CompanyListXmlView(Jaxb2Marshaller marshaller) {
		super(marshaller);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		model.remove("title");
		model.remove("page");

		Page<Company> companies = (Page<Company>) model.get("companies");

		model.remove("companies");

		model.put("companyList", new CompanyList(companies.getContent()));
		
		super.renderMergedOutputModel(model, request, response);
	}
	
	

}
