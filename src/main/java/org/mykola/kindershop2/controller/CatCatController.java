package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.entity.CatCategory;
import org.mykola.kindershop2.repository.CatCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/catcat")
public class CatCatController {
	private final CatCategoryRepository catCatRepo;
	
	@Autowired
	public CatCatController(CatCategoryRepository catCatRepo) {
		this.catCatRepo = catCatRepo;
	}
	
	@PostMapping("/catman")
	public List<CatCategory> findByMan(@RequestParam("id")Long id){
		return catCatRepo.findByProductListManufacturerId(id);
	}
	
	@PostMapping
	public List<CatCategory> liveSearch(@RequestParam("name")String name){
//		System.out.println("\n---===FOUND===---");
//		System.out.println(catCatRepo.findByNameContaining(name));
		return catCatRepo.findByNameContaining(name);
	}
	
}
