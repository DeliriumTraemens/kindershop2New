package org.mykola.kindershop2.controller.prodSearchCard;

import org.mykola.kindershop2.entity.tmpDto.ProductSearchCard;
import org.mykola.kindershop2.repository.searchCard.ProdSearchCardRepo;
import org.mykola.kindershop2.service.ProdSearchCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/prodsearchcard")
public class ProdSearchCardController {
	@Autowired
	ProdSearchCardRepo pscr;
	
	@Autowired
	ProdSearchCardService pscs;
	@GetMapping("/{id}")
	public  ProductSearchCard getOneById(@PathVariable("id") Long id){
		pscs.rangeCats(pscr.findById(id).get());
		System.out.println("prodOut ->"+pscr.findById(id).get());
		return pscr.findById(id).get();
	}
	
}
