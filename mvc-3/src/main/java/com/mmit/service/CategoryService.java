package com.mmit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.entities.Category;
import com.mmit.repo.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public List<Category> findAll() {
		return repo.findAll();
	}

	public void save(Category category) {
		repo.save(category);
	}

	public Category findById(int id) {
		return repo.findById(id).get();
	}

	public void deleteById(int id) {
		repo.deleteById(id);
	}
	
	
}
