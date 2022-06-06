package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.dto.ManCat;
import org.mykola.kindershop2.entity.CatCategory;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.entity.tmpDto.CatTemp;
import org.mykola.kindershop2.entity.tmpDto.CatTempRead;
import org.mykola.kindershop2.repository.temp.CatTempReadRepo;
import org.mykola.kindershop2.repository.temp.CatTempRepo;
import org.mykola.kindershop2.service.CatTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cattemp")
@CrossOrigin("*")
public class CatTempController {
	@Autowired
	CatTempRepo catTempRepo;
	@Autowired
	CatTempService cts;
	
	@Autowired
	CatTempReadRepo ctr;
	
	
	@GetMapping()
	public CatTemp getAll(){
		List total = catTempRepo.findAll();
		return cts.findById(30L);
//		return catTempRepo.findById(30L).get();
	}
	
	@GetMapping("/number")
	public Iterable<CatTemp> number(){
		return cts.findNumber();
	}
	
//	/cattemp/man/{id}
	//Иерархический список категорий
	@GetMapping("/man/{id}")
	public List <CatTempRead> filtered(@PathVariable("id") Long id){
		//Первый вариант
		cts.catIerarchy(id);
		cts.catIerarchy2(id);
	return ctr.findById(30L).get().getChildrenlist();
	}
	//Рабочая версия
	@GetMapping("/man2/{id}")
	public Set<ManCat> filtered2(@PathVariable("id") Long id){
		//Первый вариант
		return cts.catIerarchy2(id);
	}
	
	
}
