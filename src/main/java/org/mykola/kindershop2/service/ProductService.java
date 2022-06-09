package org.mykola.kindershop2.service;

import org.mykola.kindershop2.dto.ProductPageDto;
import org.mykola.kindershop2.entity.Product;
import org.mykola.kindershop2.repository.ManufacturerRepository;
import org.mykola.kindershop2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	private final ManufacturerRepository manRepo;
	private final ProductRepository prodRepo;
	
	@Autowired
	public ProductService(ManufacturerRepository manRepo, ProductRepository prodRepo) {
		this.manRepo = manRepo;
		this.prodRepo = prodRepo;
	}
	
	//Refactor to DTO
	public ProductPageDto getProdListByCat(Long id, Pageable pageable, int page1) {
		
		Pageable pageRequest = PageRequest.of(page1, 8);
		Page <Product> page = prodRepo.findByCatId(id, pageRequest);
		
				//		Возвращаем НОВЫЙ объект, Созданный с помощью конструктора(Ctrl-P подсказывает порядок аргументов
				//		— соответствующий порядку объявления полей в классе)
		return new ProductPageDto(page.getContent(),
		                          pageable.getPageNumber(),
		                          page.getTotalPages());
	}
	
	public List<Object[]> getProdQuery(Long id) {
		return prodRepo.getProducts(id);
	}
	
	public List<Product> liveSearch(String name) {
		return prodRepo.findByNameContaining(name);
	}
}
