package org.mykola.kindershop2.repository.temp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mykola.kindershop2.entity.tmpDto.CatTemp;
import org.mykola.kindershop2.entity.tmpDto.CatTempSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class CatTempSaveRepoTest {
	@Autowired
//	CatTempSaveRepo ctsr;
	CatTempRepo ctr;
	
	@Test
	public void saveNew(){
		CatTemp newOne=new CatTemp(4L,"Fourth");
		CatTemp root= ctr.findById(0L).get();
		newOne.setParent(root);
		ctr.save(newOne);
		System.out.println(newOne);
	}
	
	@Test
//	@Transactional
	public void showCatTemp(){
		CatTemp toShow=ctr.findById(0L).get();
		System.out.println(toShow);
	}
	
}