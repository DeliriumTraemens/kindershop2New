package org.mykola.kindershop2.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.mykola.kindershop2.entity.Category;
import org.mykola.kindershop2.entity.Views;
import org.mykola.kindershop2.repository.CategoryRepository;
import org.mykola.kindershop2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	private final CategoryService catService;
	private final CategoryRepository catRepo;
	@Autowired
	public CategoryController(CategoryService catService, CategoryRepository catRepo) {
		this.catService = catService;
		this.catRepo = catRepo;
	}
	
	@GetMapping
	@JsonView(Views.IdName.class)
//	public List<Category> getTopCatList()
	public Category getTopCatList()
	{
		return catService.getTopCatList();
	}
	
}
