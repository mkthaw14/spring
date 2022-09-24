package com.mmit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.entities.Customer;
import com.mmit.exceptions.UnAuthorizedException;
import com.mmit.repo.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo repo;

	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public void save(Customer customer) {
		if(emailInUse(customer.getEmail()))
			throw new UnAuthorizedException("email exists");
		
		repo.save(customer);
	}
	
	public void edit(Customer customer)
	{
		System.out.println("hello edit");
		Customer foundCustomer = findById(customer.getId());
		if(foundCustomer == null)
			throw new UnAuthorizedException("id not found");
		
		if(emailInUse(customer.getEmail()))
		{
			Customer currentEmailUser = repo.getCurrentCustomerByEmailAndID(customer.getId(), customer.getEmail());
			if(currentEmailUser == null) // if edited email is not current user's existing email
				throw new UnAuthorizedException("Email in use");
		}
		
		repo.save(customer);
	}

	public Customer findById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public boolean emailInUse(String email)
	{
		int emailCount = repo.countByEmail(email);
		
		System.out.println("EmailCount : " + emailCount);
		
		return emailCount > 0;
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Customer customerFound = findById(id);
		if(customerFound == null)
			throw new UnAuthorizedException("id not found");
		
		repo.delete(customerFound);
	}
	
	
	
}
