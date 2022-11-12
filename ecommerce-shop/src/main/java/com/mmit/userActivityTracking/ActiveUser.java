package com.mmit.userActivityTracking;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.mmit.model.entity.User;

//Default Scope is Singleton Scope
@Component
public class ActiveUser 
{
	public List<String> users;
	
	@PostConstruct
	public void init()
	{
		System.out.println("activeUser post construct");
	}
	
	@PreDestroy
	public void preDestroy()
	{
		System.out.println("activeUser pre destroy");
	}
	
	public ActiveUser()
	{
		users = new ArrayList<String>();
	}
}
