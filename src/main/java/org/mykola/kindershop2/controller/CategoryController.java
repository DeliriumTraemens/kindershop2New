package org.mykola.kindershop2.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.mykola.kindershop2.entity.Category;
import org.mykola.kindershop2.entity.Views;
import org.mykola.kindershop2.repository.CategoryRepository;
import org.mykola.kindershop2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	
	@PostMapping("/editierarchy")
	public Category editIerarchy(@RequestParam("id")Long id, @RequestParam("parentId")Long parentId){
		
		catService.editIerarchy(id, parentId);
		
		return catService.getTopCatList();
	}
	
	@PostMapping("/addNewSubcat")
	public Category addNewSubcat(@RequestParam("parentId")Long parentId,
	                             @RequestParam("name")String name,
	                             @RequestParam("description")String description){
		catService.addNewSubcat(parentId, name, description);
		return catService.getTopCatList();
	}
	
	@PostMapping("/deleteCat/{id}")
	public Category deleteCat(@PathVariable("id") Long id){
		return catService.deleteCat(id);
	}
	
	@PostMapping("/search")
	public List<Category> liveSearch(@RequestParam("name")String name){
		return catService.liveSearch(name);
	}
	
}
