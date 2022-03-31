package org.mykola.kindershop2.service;

import org.mykola.kindershop2.controller.ProductController;
import org.mykola.kindershop2.dto.ProductPageDto;
import org.mykola.kindershop2.entity.Product;
import org.mykola.kindershop2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	private final ProductRepository prodRepo;
	@Autowired
	public ProductService(ProductRepository prodRepo) {
		this.prodRepo = prodRepo;
	}
	
	
	public ProductPageDto getProdListByCat(Long id, Pageable pageable, int page1) {
		
		Pageable pageRequest = PageRequest.of(page1, 5);
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
}
