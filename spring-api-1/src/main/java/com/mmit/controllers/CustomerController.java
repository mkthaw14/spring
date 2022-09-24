package com.mmit.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mmit.entities.Customer;
import com.mmit.exceptions.UnAuthorizedException;
import com.mmit.services.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/api/customers")
	public List<Customer> getAllCustomers()
	{
		return customerService.findAll(); 
	}
	
	@PostMapping("/api/customer-add")
	public ResponseEntity<Map<String, String>> addCustomer(@RequestBody Customer customer) // RequestBody means getting the value only from request body not by parameter which is used by GET method
	{
		System.out.println("Customer : " + customer);
			
		customerService.save(customer);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "add success!");
		
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
	
	@PutMapping("/api/customer-edit")
	public ResponseEntity<Map<String, String>> editCustomer(@RequestBody Customer customer)
	{
		System.out.println("Edit id : " + customer.getId());
		
		customerService.edit(customer);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "edit success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/customer-delete/{id}")
	public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable int id)
	{
		System.out.println("Delete");
		
		customerService.deleteById(id);
		
		Map<String, String> map = new HashMap<>();
		map.put("message", "delete success");
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
	}
}
