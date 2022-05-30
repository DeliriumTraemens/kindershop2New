package org.mykola.kindershop2.controller.prodSearchCard;

import org.mykola.kindershop2.dto.prodSearchCardDto.ProductSearchCardDto;
import org.mykola.kindershop2.dto.prodSearchCardDto.ProductSearchCardNewDto;
import org.mykola.kindershop2.entity.tmpDto.CatIdNameDto2;
import org.mykola.kindershop2.entity.tmpDto.ProductSearchCard;
import org.mykola.kindershop2.repository.searchCard.ProdSearchCardRepo;
import org.mykola.kindershop2.service.ProdSearchCardService;
import org.mykola.kindershop2.service.test.ProdSearchCardService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/prodsearchcard")
public class ProdSearchCardController {
	@Autowired
	ProdSearchCardRepo pscr;
	@Autowired
	ProdSearchCardService pscs;
	
	@Autowired
	ProdSearchCardService2 ps2;
//	@Autowired
//	CatRangedDtoDelRepo crdlr;
	
	@GetMapping("/{id}")
	public ProductSearchCardDto getOneById(@PathVariable("id") Long id){
//		crdlr.deleteAll();
		return pscs.rangeCats(pscr.findById(id).get());
//		return pscr.findById(id).get();
	}
	
	@GetMapping("/test/{id}")
	public ProductSearchCard testCard(@PathVariable("id")Long id){
		return pscr.findById(id).get();
	}
	
	@GetMapping("/test2/{id}")
//	public List<CatIdNameDto2> testCard2(@PathVariable("id")Long id){
	public ProductSearchCardNewDto testCard2(@PathVariable("id")Long id){
		return ps2.testCard(id);
	}
	
}
