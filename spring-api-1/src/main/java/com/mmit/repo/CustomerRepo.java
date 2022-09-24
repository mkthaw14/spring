package com.mmit.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmit.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	//Select Count(*) from customers where email = :email
	int countByEmail(String email);
	
	@Query("Select c From Customer c Where c.email = :email And c.id = :id")
	Customer getCurrentCustomerByEmailAndID(@Param("id") int id,@Param("email")  String email);
}
