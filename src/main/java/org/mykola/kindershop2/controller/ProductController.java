package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.dto.ProductPageDto;
import org.mykola.kindershop2.entity.Product;
import org.mykola.kindershop2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
	public static final int PRODUCTS_PER_PAGE = 5;
	private final ProductService prodService;
	
	@Autowired
	public ProductController(ProductService prodService) {
		this.prodService = prodService;
	}
	
//	@GetMapping("/{id}")
//	public List<Product> getProductListByCategory(@PathVariable(value = "id") Long id){
//		return prodService.getProdListByCat(id);
//	}
	
	@GetMapping("/{id}")
	public ProductPageDto getProdQuery(@PathVariable(value = "id") Long id,
	                                   @RequestParam(value="page")int page,
	                                   @PageableDefault(size = PRODUCTS_PER_PAGE, sort={"id"}, direction= Sort.Direction.ASC) Pageable pageable){
//
		System.out.println(prodService.getProdListByCat(id,pageable, page));
		return prodService.getProdListByCat(id,pageable, page);
	}
	
//
	
}
