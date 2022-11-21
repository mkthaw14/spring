package com.mmit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.entities.Employee;
import com.mmit.repos.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo repo;

	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	
	
}
