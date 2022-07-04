package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.dto.ProductFind2Dto;
import org.mykola.kindershop2.dto.ProductFindDto;
import org.mykola.kindershop2.entity.ProdCat;
import org.mykola.kindershop2.repository.ProdCatRepository;
import org.mykola.kindershop2.service.ProdCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/prodcat")
public class ProdCatController {
	private final ProdCatRepository prodCatRepo;
	private final ProdCatService pcs;
	@Autowired
	public ProdCatController(ProdCatRepository prodCatRepo, ProdCatService pcs) {
		this.prodCatRepo = prodCatRepo;
		this.pcs = pcs;
	}
	
	@PostMapping("/search")
	public Set<ProdCat> searchList(@RequestParam("name")String name){
//		Set<ProdCat> found = prodCatRepo.findDistinctByNameContaining(name);
//		Set<ProdCat>send=new HashSet<>();
//
//		for (ProdCat found1:found){
//			send.add(found1);
//		}
//		System.out.println("\n-----=== Result sent===----");
		return prodCatRepo.findDistinctByNameContaining(name);
	}
	
	@PostMapping("/find")
	public ProductFind2Dto findProducts(@RequestParam("name")String name){
		return pcs.findProducts(name);
	}
	
	@PostMapping("/manFilter")
	public Set<ProdCat> manFilter(@RequestParam("id")Long id, @RequestParam("name")String name ){
		return pcs.manufacturersFilter(id, name);
	}
	
}
