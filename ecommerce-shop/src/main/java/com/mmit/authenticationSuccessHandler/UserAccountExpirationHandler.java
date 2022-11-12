package com.mmit.authenticationSuccessHandler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmit.model.entity.User;
import com.mmit.model.service.UserService;

@Component
public class UserAccountExpirationHandler {

	@Autowired
	UserService service;
	
	public void findByEmail(String email)
	{
		User u = service.findByEmail(email);
	}
}
