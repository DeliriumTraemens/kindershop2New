package org.mykola.kindershop2.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mykola.kindershop2.entity.Product;
import org.mykola.kindershop2.entity.product.projections.ProdIdNameImageManCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {
	@Autowired
	ProductRepository prodRepo;
	
	@Test
	public void findListByNameContaining() throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		Set<ProdIdNameImageManCat> unicString = new HashSet<>();
		List<ProdIdNameImageManCat> testList = prodRepo.findListByNameContaining("качели");
		List<ProdIdNameImageManCat> products= prodRepo.findListByNameContaining("Микроскоп");
		
		for (ProdIdNameImageManCat product : products) {
			unicString.add(product);
		}
		
		
		String productsString = mapper.writeValueAsString(unicString);
		System.out.println(productsString);
		System.out.println("Найдено "+unicString.size() +" элементов");
		
		
//		Set<String> actual = testList.stream().map(el -> el.getManufacturer().getName()).collect(Collectors.toSet());
//		System.out.println("Найдено "+actual.size() +" элементов");
//		actual.forEach(System.out::println);
		
	}
}