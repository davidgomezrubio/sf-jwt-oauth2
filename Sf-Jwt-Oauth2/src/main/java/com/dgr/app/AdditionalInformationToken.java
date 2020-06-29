package com.dgr.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.dgr.app.entities.User;
import com.dgr.app.services.IUserService;


@Component
public class AdditionalInformationToken implements TokenEnhancer {
	
	@Autowired
	private IUserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		User user = userService.findByUsername(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		
		info.put("id", "Additional info -> Id: " + user.getUserId());
		info.put("username", "Additional info -> Username: ".concat(user.getUsername()));
		info.put("name", "Additional info -> name: ".concat(user.getName()));
		info.put("lastName", "Additional info -> lastName: ".concat(user.getLastName()));
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
