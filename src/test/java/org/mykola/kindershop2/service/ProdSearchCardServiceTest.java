package org.mykola.kindershop2.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mykola.kindershop2.entity.tmpDto.CatRangedDto;
import org.mykola.kindershop2.repository.searchCard.CatRangedDtoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProdSearchCardServiceTest {
	@Autowired
	CatRangedDtoRepo crd;
	
	//Utils
	@Test
	public void showAll(){
		System.out.println(crd.findAll());
	}
	
	@Test
	public void clearAll(){
		crd.deleteAll();
	}
	
	@Test
	public void buildSet(){
		CatRangedDto root = new CatRangedDto();
					root.setId(1L);
					root.setName("Root");
		crd.save(root);
		
		CatRangedDto child1 = new CatRangedDto();
		child1.setId(2L);
		child1.setName("First child");
		child1.setParent(root);
		crd.save(child1);
		
		CatRangedDto child2 = new CatRangedDto();
		child2.setId(3L);
		child2.setName("Second child");
		child2.setParent(root);
		crd.save(child2);
		
		CatRangedDto child3 = new CatRangedDto();
		child3.setId(4L);
		child3.setName("Third child");
		child3.setParent(root);
		crd.save(child3);
		
		
	}
}