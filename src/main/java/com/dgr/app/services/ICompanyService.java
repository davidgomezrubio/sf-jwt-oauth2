package com.dgr.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgr.app.entities.Company;


public interface ICompanyService {
	
	public List<Company> findAll();
	
	public Page<Company> findAll(Pageable pageable);
	
	public void save(Company company);
	
	public Company findOne(Long id);
	
	public void delete(Long id);

}
