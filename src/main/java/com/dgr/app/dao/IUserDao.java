package com.dgr.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.dgr.app.entities.User;

public interface IUserDao extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);

}
