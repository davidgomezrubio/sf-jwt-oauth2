package com.dgr.app.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.dgr.app.entities.Company;


public interface ICompanyDao extends PagingAndSortingRepository<Company, Long> {
	
	//we can have our personal selects
	
	

}
