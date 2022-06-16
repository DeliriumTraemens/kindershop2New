package org.mykola.kindershop2.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.mykola.kindershop2.entity.Category;
import org.mykola.kindershop2.entity.Views;
import org.mykola.kindershop2.repository.CategoryRepository;
import org.mykola.kindershop2.service.CatalogService;
import org.mykola.kindershop2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	private final CategoryService catService;
	private final CategoryRepository catRepo;
	private final CatalogService cataServe;
	private final CatalogController cataContr;
	@Autowired
	public CategoryController(CategoryService catService, CategoryRepository catRepo, CatalogService cataServe, CatalogController cataContr) {
		this.catService = catService;
		this.catRepo = catRepo;
		this.cataServe = cataServe;
		this.cataContr = cataContr;
	}
	
	@GetMapping
	@JsonView(Views.IdName.class)
	public Category getTopCatList()
//	public Category getTopCatList()
	{
		return catService.getTopCatList();
	}
	
	@PostMapping("/editierarchy")
	public String editIerarchy(@RequestParam("id")Long id, @RequestParam("parentId")Long parentId) throws IOException, ClassNotFoundException {
		if (!id.equals(parentId)){
			catService.editIerarchy(id, parentId);
		}
		
		return cataContr.giveCatalog();
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
