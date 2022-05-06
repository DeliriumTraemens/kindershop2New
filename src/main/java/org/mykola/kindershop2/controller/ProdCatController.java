package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.entity.ProdCat;
import org.mykola.kindershop2.repository.ProdCatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/prodcat")
public class ProdCatController {
	private final ProdCatRepository prodCatRepo;
	
	public ProdCatController(ProdCatRepository prodCatRepo) {
		this.prodCatRepo = prodCatRepo;
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
}
