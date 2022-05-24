package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.entity.tmpDto.CatTemp;
import org.mykola.kindershop2.entity.tmpDto.CatTempRead;
import org.mykola.kindershop2.repository.temp.CatTempReadRepo;
import org.mykola.kindershop2.repository.temp.CatTempRepo;
import org.mykola.kindershop2.service.CatTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	@GetMapping("/man/{id}")
	public List <CatTempRead> filtered(@PathVariable("id") Long id){
		cts.catIerarchy(id);
	return ctr.findById(30L).get().getChildrenlist();
	}
	
}
