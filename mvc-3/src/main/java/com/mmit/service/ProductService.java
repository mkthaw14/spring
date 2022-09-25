package com.mmit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.entities.Product;
import com.mmit.repo.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;

	public List<Product> findAll() {
		return repo.findAll();
	}

	public Product save(Product product) {
		return repo.save(product);
	}

	public Product findById(int id) 
	{
		return repo.findById(id).get();
	}

	public void deleteById(int id) {
		repo.deleteById(id);
	}
	
	
}
