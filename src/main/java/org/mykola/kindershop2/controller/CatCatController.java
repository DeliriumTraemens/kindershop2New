package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.entity.CatCategory;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.repository.CatCategoryRepository;
import org.mykola.kindershop2.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
//@CrossOrigin(origins = "http://192.168.1.68:8080", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS,
//                                        RequestMethod.POST, RequestMethod.PUT }, allowedHeaders = "*", exposedHeaders = "*")
@RequestMapping("/catcat")
public class CatCatController {
	private final CatCategoryRepository catCatRepo;
	private final ManufacturerRepository manRepo;
	@Autowired
	public CatCatController(CatCategoryRepository catCatRepo, ManufacturerRepository manRepo) {
		this.catCatRepo = catCatRepo;
		this.manRepo = manRepo;
	}
	
	@PostMapping("/catman")
	public List<CatCategory> findByMan(@RequestParam("id")Long id){
		Manufacturer man = manRepo.findById(id).get();
		return catCatRepo.findByProductList_Manufacturer(man);
	}
	
	@PostMapping
	public List<CatCategory> liveSearch(@RequestParam("name")String name){
//		System.out.println("\n---===FOUND===---");
//		System.out.println(catCatRepo.findByNameContaining(name));
		return catCatRepo.findByNameContaining(name);
	}
	
}
