package org.mykola.kindershop2.controller.productShow;

import org.mykola.kindershop2.entity.product.ProductShow;
import org.mykola.kindershop2.service.productshow.ProductShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/prodshow")
public class ProductShowController {
	private final ProductShowService pss;
	@Autowired
	public ProductShowController(ProductShowService pss) {
		this.pss = pss;
	}
	
	@GetMapping("/{id}")
	public ProductShow showOne (@PathVariable("id") Long id){
		return pss.showOne(id);
	}
	
}//EoC
