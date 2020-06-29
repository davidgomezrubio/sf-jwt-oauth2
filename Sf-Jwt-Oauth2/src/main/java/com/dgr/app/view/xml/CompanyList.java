package com.dgr.app.view.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.dgr.app.entities.Company;


@XmlRootElement(name="companies")
public class CompanyList {
	
	@XmlElement(name="company")
	public List<Company> company;

	public CompanyList() {

	}

	public CompanyList(List<Company> company) {
		this.company = company;
	}

	public List<Company> getCompany() {
		return company;
	}

}
