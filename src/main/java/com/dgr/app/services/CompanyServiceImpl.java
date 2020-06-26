package com.dgr.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgr.app.dao.ICompanyDao;
import com.dgr.app.entities.Company;

@Service
public class CompanyServiceImpl implements ICompanyService {
	
	@Autowired
	private ICompanyDao companyDao;

	@Override
	@Transactional(readOnly = true)
	public List<Company> findAll() {

		return (List<Company>) companyDao.findAll();
	}

	@Override
	@Transactional
	public void save(Company company) {

		companyDao.save(company);
	}

	@Override
	@Transactional(readOnly = true)
	public Company findOne(Long id) {

		return companyDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		companyDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Company> findAll(Pageable pageable) {
		
		return companyDao.findAll(pageable);
	}

}
