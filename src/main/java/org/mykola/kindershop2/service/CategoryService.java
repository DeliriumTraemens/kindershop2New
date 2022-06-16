package org.mykola.kindershop2.service;

import org.mykola.kindershop2.entity.Category;
import org.mykola.kindershop2.repository.CatalogRepository;
import org.mykola.kindershop2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
	private final CategoryRepository catRepo;
	private final CatalogService cataServ;
	private final CatalogRepository cataRepo;
	
	@Autowired
	public CategoryService(CategoryRepository catRepo, CatalogService cataServ, CatalogRepository cataRepo) {
		this.catRepo = catRepo;
		this.cataServ = cataServ;
		this.cataRepo = cataRepo;
	}
	
	public Category getTopCatList() {
		return catRepo.findCatalogList(30L);
//		return catRepo.findById(0L).get();
		
	}
	
	public void editIerarchy(Long id, Long parentId) throws IOException {
		Category parent = catRepo.findById(parentId).get();
		Category edited = catRepo.findById(id).get();
		
		edited.setParent(parent);
		if(!id.equals(parentId)){
			catRepo.save(edited);
		}
		
		//TODO вставить метод из КаталогСервис для сериализации нового дерева
		cataServ.writeCatalogToStringString(cataRepo.findById(30L).get());
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
