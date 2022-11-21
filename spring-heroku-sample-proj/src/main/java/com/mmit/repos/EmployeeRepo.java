package com.mmit.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
