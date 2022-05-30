package org.mykola.kindershop2.service;

import org.mykola.kindershop2.entity.Category;
import org.mykola.kindershop2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {
	private final CategoryRepository catRepo;
	
	@Autowired
	public CategoryService(CategoryRepository catRepo) {
		this.catRepo = catRepo;
	}
	
	public Category getTopCatList() {
		return catRepo.findCatalogList(30L);
//		return catRepo.findById(0L).get();
		
	}
	
	public void editIerarchy(Long id, Long parentId) {
		Category parent = catRepo.findById(parentId).get();
		Category edited = catRepo.findById(id).get();
		
		edited.setParent(parent);
		if(!id.equals(parentId)){
			catRepo.save(edited);
		}
	}
	
	public void addNewSubcat(Long parentId, String name, String description) {
		Category parent = catRepo.findById(parentId).get();
		Category newSubCat = new Category();
		
		if (! name.isEmpty()) {
			newSubCat.setName(name);
		} else {
			newSubCat.setName("Unnamed Category");
		}
		
		newSubCat.setCreationDate(LocalDateTime.now());
		newSubCat.setModificationDate(LocalDateTime.now());
		newSubCat.setDescription(description);
		newSubCat.setParent(parent);
		
		catRepo.save(newSubCat);
		System.out.println("\n --------------==============NEW SUB============--------------");
		System.out.println(newSubCat);
	}
	
	public Category deleteCat(Long id) {
		
		Category removed = catRepo.findById(id).get();
		
		catRepo.delete(removed);
		
		return getTopCatList();
	}
	
	public List<Category> liveSearch(String name) {
//		return catRepo.findByNameLike(name);
		List<Category> result= catRepo.findDistinctByNameContaining(name);
		System.out.println("\n---------======FOUND=====-------");
		System.out.println(result);
		return result;
//		return catRepo.findDistinctByNameContaining(name);
	}
}
