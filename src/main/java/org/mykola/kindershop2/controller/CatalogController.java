package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.entity.Catalog;
import org.mykola.kindershop2.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/catalog")
@CrossOrigin("*")
public class CatalogController {
	
	private final CatalogRepository cataRepo;
	@Autowired
	public CatalogController(CatalogRepository cataRepo) {
		this.cataRepo = cataRepo;
	}
	
//	@GetMapping
//	public List<Catalog> getCatalog(){
//		return cataRepo.findCatalog(1130L);
//	}
	
	@GetMapping
	public Optional<Catalog> getCatalog() {
		return cataRepo.findById(1130L);
	}
	
	
}
