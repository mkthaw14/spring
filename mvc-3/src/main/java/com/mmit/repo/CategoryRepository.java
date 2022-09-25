package com.mmit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> 
{

}
