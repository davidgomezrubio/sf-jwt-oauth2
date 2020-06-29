package com.dgr.app;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



import com.dgr.app.view.xml.CompanyList;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

	
	//Encode a String with BCript
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
//	//Catch de error_403 and redirect to /error_403 view
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/error_403").setViewName("error_403");
//	}
	
	
	
	
	//To gernerate de Xml View
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller =  new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(new Class[] {CompanyList.class});
		return marshaller;
	}

	
}
