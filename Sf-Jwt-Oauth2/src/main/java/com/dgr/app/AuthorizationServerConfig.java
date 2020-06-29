package com.dgr.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AdditionalInformationToken additionalInformationToken;
	

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	
	
	
	//This Endpoins are validated with Basic protocol
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");

	}



	//TODO Pasar este código a la base de datos en lugar de tenerlo en inMemory
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {


//		 The type of password authorization makes us send username and password that we have in our DB
//		 To request the token we do it at localhost:9090/oauth/token or our domain
		
//		Postman configuration 
//		Body:
//			username: 	(our username to login)
//			password:	(our user password)
//			grant_type: password
//		Authorization:
//			TYPE: Basic Auth
//			Username: (app username) to connect to the EndPoint
//			Password: (app password) to connect to the EndPoint
		
		
		clients.inMemory().withClient("angularapp")
		.secret(passwordEncoder.encode("123456"))
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600);
	
	}

	
	
	
	
	
	//TODO Ver como funciona bien este método
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {


		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(additionalInformationToken, accessTokenConverter()));
		
//		accessTokenConverter handles the authentication data. email, name, and any non-sensitive information.
// 		Translate the token data and validate it.
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.tokenEnhancer(tokenEnhancerChain);
	}

	
//		Here we keep the secret key for Jwt encryption
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.SECRET_KEY);
		return jwtAccessTokenConverter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

}
