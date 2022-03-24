package org.mykola.kindershop2.service;

import org.mykola.kindershop2.entity.Category;
import org.mykola.kindershop2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
	private final CategoryRepository catRepo;
	
	@Autowired
	public CategoryService(CategoryRepository catRepo) {
		this.catRepo = catRepo;
	}
	
	public Category getTopCatList() {
		return catRepo.findCatalogList(0L);
		
	}
}
