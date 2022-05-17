package org.mykola.kindershop2.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mykola.kindershop2.entity.Category;
import org.mykola.kindershop2.repository.CatalogRepository;
import org.mykola.kindershop2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class CatalogControllerTest {
	@Autowired
	CategoryRepository catRepo;
	
	@Test
	public void createRoot(){
		Category root = new Category();
		Category top = catRepo.findById(0L).get();
		root.setName("RootMain");
		root.setParent(top);
		root.setCreationDate(LocalDateTime.now());
		root.setDescription("The Root Category");
//		root.setStatus((short) 1);
		catRepo.save(root);
		System.out.println(catRepo.findByName("RootMain"));
	}
	
}