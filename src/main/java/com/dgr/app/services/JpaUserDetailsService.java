package com.dgr.app.services;

//Aqui solo se comprueba si el usuario existe y se cargan sus roles y sus detalles.
//No se mira si la contraseña está bien o el usuario está bien escrito.

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgr.app.dao.IUserDao;
import com.dgr.app.entities.Role;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserDao userDao;
	
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
//		Search for the user with Username and password entered at login
//      The user has a attribute (roles) that contains a list of the roles to which it is enrolled
		com.dgr.app.entities.User user = userDao.findByUsername(username);
		
		
//		If it doesn't exist
		if (user == null) {
			logger.error("Username doesn't exist: '" + username + "'");
			throw new UsernameNotFoundException("Username: " + username + "doesn't exist");
		}
		
//		We create authorities, list with the roles for which the user is enabled
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
//		SimpleGrantedAuthority implements GrantedAuthority
//		Here we load the list authorities
		for (Role role : user.getRoles() ) {
			logger.info("Role:".concat(role.getRole()));
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}	
		
		if (authorities.isEmpty()) {
			logger.error("The user: '" + username + "' has no assigned roles");
			throw new UsernameNotFoundException("Username: '" + username + "' has no assigned roles");
		}
		
//		(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, adn the list [authorities])
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true , true, true, authorities);
	}

}
