package com.dgr.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dgr.app.entities.User;

public interface IUserDao extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);
	
	@Query("select u from User u where u.username=?1")
	public User findByUsernameQuery(String username);

}
