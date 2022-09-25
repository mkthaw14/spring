package com.mmit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mmit.entities.Category;
import com.mmit.service.CategoryService;

@Controller
public class CategoryController 
{
	@Autowired
	private CategoryService service;
	
	@ModelAttribute
	public void assignDefaultModel(Model model)
	{
		model.addAttribute("page", "category");
		model.addAttribute("category", new Category());
	}
	
	@GetMapping("/categories")
	public String goHome(Model model)
	{
		model.addAttribute("categories", service.findAll());
		return "category-list";
	}
	
	@GetMapping("/category/add")
	public String goAdd()
	{
		return "category-add";
	}
	
	@PostMapping("/category/save")
	public String goSave(@ModelAttribute Category category)
	{
		System.out.println("Category save");
		service.save(category);
		return "redirect:/categories";
	}
	
	@GetMapping("/category/edit/{id}")
	public String goEdit(@PathVariable int id, Model model)
	{
		model.addAttribute("category", service.findById(id));
		return "category-add";
	}
	
	@GetMapping("/category/delete/{id}")
	public String goDelete(@PathVariable int id)
	{
		System.out.println("ID to delete " + id);
		service.deleteById(id);
		return "redirect:/categories";
	}
	
	@PostMapping("/category/delete")
	public String goDelete(@RequestParam("cat_id") String cat_id)
	{
		int id = 0;
		try
		{
			id = Integer.parseInt(cat_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Cat ID " + cat_id);
		//service.deleteById(id);
		return "redirect:/categories";
	}
}
