package com.dgr.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.dgr.app.services.JpaUserDetailsService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JpaUserDetailsService userDetailsService;	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/companies", "/api-rest-doc/**", "/landing/**").permitAll()
		.antMatchers("/companies/{id}").hasAnyRole("ADMIN_COMPANY")
		.antMatchers("/companies/form/**").hasAnyRole("ADMIN")
		.antMatchers("/companies/delete/**").hasAnyRole("ADMIN")
		.antMatchers("/api-rest/**").hasAnyRole("USER")
		.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/login").permitAll()
			.passwordParameter("password")
			.usernameParameter("username")
		.and()
			.logout().permitAll()
		.and()
			.exceptionHandling().accessDeniedPage("/error_403");
		
		
	}
	


	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception  {
		
		try {
			builder.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error en el builder.userDetailsService: " + e);
		}

	}

}
