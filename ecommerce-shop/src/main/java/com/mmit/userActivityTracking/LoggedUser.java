package com.mmit.userActivityTracking;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmit.authenticationSuccessHandler.UserAccountExpirationHandler;
import com.mmit.authenticationSuccessHandler.UserAuthenticationSuccessHandler;
import com.mmit.model.service.UserService;


public class LoggedUser implements HttpSessionBindingListener {

	
	private String name;
	private ActiveUser activeUser;
	@Autowired
	UserAccountExpirationHandler accountHandler;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActiveUser getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(ActiveUser activeUser) {
		this.activeUser = activeUser;
	}

	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LoggedUser user = (LoggedUser) event.getValue();
		
		accountHandler.findByEmail(user.getName());
		//if(! activeUser.users.contains(user.getName()))
		//{
			//activeUser.users.add(user.getName());
		//}

		System.out.println("session user created : " + user.getName());
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LoggedUser user = (LoggedUser) event.getValue();
		
		//if(activeUser.users.contains(user.getName()))
		//{
			//activeUser.users.remove(user.getName());
		//}
		
		System.out.println("session user remove : " + user.getName());
	}
	
}
