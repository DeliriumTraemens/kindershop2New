package org.mykola.kindershop2.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mykola.kindershop2.entity.tmpDto.CatTempRead;
import org.mykola.kindershop2.entity.tmpDto.CatTempWrite;
import org.mykola.kindershop2.repository.temp.CatTempReadRepo;
import org.mykola.kindershop2.repository.temp.CatTempWriteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CatTempServiceTest {
	
	@Autowired
	CatTempWriteRepo ctwr;
	@Autowired
	CatTempReadRepo ctr;
	
	@Test
	public void catIerarchy() {
		
//		CatTempWrite newMain = new CatTempWrite(20L,
//		                                     "Root",25L);
//		ctwr.save(newMain);
		
		CatTempRead newRead = new CatTempRead();
		newRead.setId(52L);
		newRead.setName("Child 2");
		ctr.save(newRead);
		
	}
	
	@Test
	public void setParent(){
		CatTempRead parent = ctr.findById(42L).get();
		CatTempRead child = ctr.findById(52L).get();
			child.setParent(parent);
			ctr.save(child);
	}
	
	@Test
	public void output(){
		System.out.println(ctr.findAll());
	}
}