package org.mykola.kindershop2.controller.prodSearchCard;

import org.mykola.kindershop2.entity.tmpDto.ProductSearchCard;
import org.mykola.kindershop2.repository.searchCard.ProdSearchCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/prodsearchcard")
public class ProdSearchCardController {
	@Autowired
	ProdSearchCardRepo pscr;
	
	@GetMapping("/{id}")
	public  ProductSearchCard getOneById(@PathVariable("id") Long id){
		System.out.println("prodOut ->"+pscr.findById(id).get());
		return pscr.findById(id).get();
	}
	
}
