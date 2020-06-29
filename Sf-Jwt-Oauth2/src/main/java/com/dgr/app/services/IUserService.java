package com.dgr.app.services;

import com.dgr.app.entities.User;

public interface IUserService {
	
	public User findByUsername(String username);

}
