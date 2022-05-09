package org.mykola.kindershop2.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mykola.kindershop2.entity.test.Polka;
import org.mykola.kindershop2.entity.test.Tovar;
import org.mykola.kindershop2.repository.test.PolkaRepo;
import org.mykola.kindershop2.repository.test.TovarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class PolkaControllerTest {
	
	@Autowired
	PolkaRepo polkaRepo;
	@Autowired
	TovarRepo tovarRepo;
	
	@Test
	public void addPolka(){
		Polka newPolka = new Polka();
		newPolka.setName("Закусь");
		polkaRepo.save(newPolka);
	}
	
	@Test
	public void addTovar(){
		Tovar newTovar = new Tovar();
		newTovar.setName("Плавленный сырок Дружба");
		tovarRepo.save(newTovar);
	}
	
	@Test
	public void addTovarToPolka(){
		Tovar forAddTovar = tovarRepo.findById(20L).get();
		Polka polka = polkaRepo.findById(15L).get();
		
		System.out.println(forAddTovar);
		
		polka.getTovarySet().add(forAddTovar);
		
		polkaRepo.save(polka);
	}
	
	@Test
	public void addPolkaToTovar(){
		Tovar tovar = tovarRepo.findById(26L).get();
		Polka polkaForAdd = polkaRepo.findById(23L).get();
		
		tovar.getPolkiSet().add(polkaForAdd);
		
		tovarRepo.save(tovar);
	}
	
	@Test
	public void printPolka(){
		Polka polka=polkaRepo.findById(15L).get();
			for (Tovar tovar :  polka.getTovarySet()){
				System.out.println(tovar.getName());
			}
//		System.out.println(polka.getTovarySet());
	}
	
	@Test
	public void printTovar(){
		System.out.println(tovarRepo.findById(24L).get());
	}
	
}