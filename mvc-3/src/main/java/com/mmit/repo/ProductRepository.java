package com.mmit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> 
{

}
