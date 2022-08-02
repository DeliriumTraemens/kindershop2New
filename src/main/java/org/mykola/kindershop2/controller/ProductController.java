package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.dto.ProductPageDto;
import org.mykola.kindershop2.dto.projections.ProdIdNamePrice;
import org.mykola.kindershop2.dto.projections.ProductNamePriceImageDto;
import org.mykola.kindershop2.entity.Product;
import org.mykola.kindershop2.repository.ProductRepository;
import org.mykola.kindershop2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
//@CrossOrigin(origins = "http://192.168.1.68:8080", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS,
//                                        RequestMethod.POST, RequestMethod.PUT }, allowedHeaders = "*", exposedHeaders = "*")
@RequestMapping("/product")
public class ProductController {
	public static final int PRODUCTS_PER_PAGE = 5;
	private final ProductService prodService;
	private final ProductRepository proRepo;
	
	@Autowired
	public ProductController(ProductService prodService, ProductRepository proRepo) {
		this.prodService = prodService;
		this.proRepo = proRepo;
	}

//	@GetMapping("/{id}")
//	public List<Product> getProductListByCategory(@PathVariable(value = "id") Long id){
//		return prodService.getProdListByCat(id);
//	}
	
	@GetMapping("/refs")
	public ProdIdNamePrice showById(){
		return proRepo.findOneById(131972L).get(0);
	}
	@GetMapping("/refs1")
	public List<ProdIdNamePrice> showByName(){
		return proRepo.findOneByNameStartingWith("пар");
	}
	
	@GetMapping("/ref")
//	public List<Object[]> getProducts() {
//	public ProdIdNamePrice getProducts(){
	public List<ProductNamePriceImageDto> getProducts(){
		
		List<ProductNamePriceImageDto> projList = new ArrayList<>();
		for (Object[] prj : proRepo.getProducts(4276L)) {
			
			ProductNamePriceImageDto prPrj=
					new ProductNamePriceImageDto((String)prj[0],(Float)prj[1],(String)prj[2]);
			projList.add(prPrj);
			
				System.out.println("----\n"+projList);
		}
		
//		return proRepo.getProducts(4276L);
		return projList;
	}

	@PostMapping("/filter")
//	public ProductPageDto getFiltered(
	public String getFiltered(
			@RequestParam(value="catId") Long catId,
			@RequestParam(value="manufacturers")List<Long> manList
	){
		System.out.println("========== FilterRequest");
				prodService.getFiltered(catId, manList);
		return "Resulted";
	}

	@GetMapping("/{id}")
	public ProductPageDto getProdQuery(
			@PathVariable(value = "id") Long id,
			@RequestParam(value = "page") int page,
			@PageableDefault(size = PRODUCTS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
	                                  ) {
//
//		System.out.println(prodService.getProdListByCat(id,pageable, page));
		return prodService.getProdListByCat(id, pageable, page);
	}
	
	@PostMapping("/search")
	public List<Product> searchProduct(@RequestParam(value = "name") String name) {
		List<Product> searchList = prodService.liveSearch(name);
//		System.out.println(searchList);
		return searchList;
	}
	
	@PostMapping("/search/sample")
	public String searchBySample(@RequestParam("sample")String sample){
		return null;
	}

//
	
}
