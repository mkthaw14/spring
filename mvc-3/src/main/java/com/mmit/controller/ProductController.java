package com.mmit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mmit.FileUploadUtil;
import com.mmit.entities.Category;
import com.mmit.entities.Product;
import com.mmit.service.CategoryService;
import com.mmit.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute
	public void assignDefaultModel(Model model)
	{
		model.addAttribute("page", "product");
		model.addAttribute("product", new Product());
	}
	
	@GetMapping("/products")
	public String goHome(Model model)
	{
		List<Product> products = service.findAll();
		
		System.out.println("size " + products.size());
		model.addAttribute("products", products);
		return "product-list";
	}
	
	@GetMapping("/product/add")
	public String goAdd(Model model)
	{
		System.out.println("Product add");
		
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "product-add";
	}
	
	@PostMapping("/product/save")
	public String goSave(@ModelAttribute("product") Product product, @RequestParam("photo_file_name") MultipartFile file)
	{
		String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // get file name (eg photo.png)
		System.out.println("File name " + fileName);
		System.out.println("Product save" + product);
		
		if( !fileName.equals("")) //If not empty string This is for add/edit operation
		{
			product.setPhoto(fileName);
		}
		//save to db
		var saveProduct = service.save(product);
	
		// saves the photo inside server file system
		if(! "".equals(fileName)) // not empty string
		{
			String uploadDir = "upload-photos/" + saveProduct.getId(); // upload-photos/1
			FileUploadUtil.savePhoto(uploadDir, fileName, file);
		}
		
		return "redirect:/products";
	}
	
	@GetMapping("/products/edit/{id}")
	public String goEdit(@PathVariable("id") int id, Model model)
	{
		var product = service.findById(id);
		
		model.addAttribute("product", product); // overriding attribute in assignDefaultModel()
		model.addAttribute("categories", categoryService.findAll());
		return "product-add";
	}
	
	@GetMapping("/products/delete/{id}")
	public String goDelete(@PathVariable("id") int id)
	{
		service.deleteById(id);
		return "redirect:/products";
	}
}
