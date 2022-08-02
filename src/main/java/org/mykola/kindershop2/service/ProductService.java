package org.mykola.kindershop2.service;

import org.mykola.kindershop2.dto.ProductFilterDto;
import org.mykola.kindershop2.dto.ProductPageDto;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameCard;
import org.mykola.kindershop2.dto.projections.product.ProdIdNameMan;
import org.mykola.kindershop2.entity.Product;
import org.mykola.kindershop2.repository.ManIdNameCardRepo;
import org.mykola.kindershop2.repository.ManufacturerRepository;
import org.mykola.kindershop2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
	private final ManufacturerRepository manRepo;
	private final ManIdNameCardRepo manIdNameRepo;
	private final ProductRepository prodRepo;
	
	@Autowired
	public ProductService(ManufacturerRepository manRepo, ManIdNameCardRepo manIdNameRepo, ProductRepository prodRepo) {
		this.manRepo = manRepo;
		this.manIdNameRepo = manIdNameRepo;
		this.prodRepo = prodRepo;
	}
	
	//Refactor to DTO
	public ProductPageDto getProdListByCat(Long id, Pageable pageable, int page1) {
		
		Pageable pageRequest = PageRequest.of(page1, 15);
//		Page <Product> page = prodRepo.findByCatId(id, pageRequest);
		Page <ProdIdNameMan> page = prodRepo.findByCatId(id, pageRequest);
		List<ProdIdNameMan> prodMans = prodRepo.findAllByCatId(id);
		List<ManIdNameCard> sortedManufacturers = prodMans.stream().map(p -> p.getManufacturer()).distinct()
				.sorted(Comparator.comparing(ManIdNameCard::getName)).collect(Collectors.toList());
		Set<Float> prices = prodMans.stream().map(p->p.getPrice()).collect(Collectors.toSet());
		int minPrice = prices.stream().min(Comparator.naturalOrder()).get().intValue();
		int maxPrice = prices.stream().max(Comparator.naturalOrder()).get().intValue();



//		sortedManufacturers.forEach(System.out::println);
//		List<ManIdNameCard> sortedManufacturers = prodMans.stream().map(p -> p.getManufacturer()).distinct()

		//		Возвращаем НОВЫЙ объект, Созданный с помощью конструктора(Ctrl-P подсказывает порядок аргументов
				//		— соответствующий порядку объявления полей в классе)
		return new ProductPageDto(page.getContent(),
		                          sortedManufacturers,
		                          minPrice,
		                          maxPrice,
		                          pageable.getPageNumber(),
		                          page.getTotalPages());
	}
	
	public List<Object[]> getProdQuery(Long id) {
		return prodRepo.getProducts(id);
	}
	
	public List<Product> liveSearch(String name) {
		return prodRepo.findByNameContaining(name);
	}

	public ProductPageDto getFiltered(Long catId, List<Long> manList) {
		List<ProductPageDto>productsToSend=new ArrayList<>();
		List<ManIdNameCard> manToFilter=manIdNameRepo.findAllById(manList);
		System.out.println("============Manufacturers");
		System.out.println(manToFilter);
		List<ProdIdNameMan> firstFiltered = prodRepo.findDistinctByCatIdAndManufacturerIn(catId, manToFilter);

		Set<Float> prices =firstFiltered.stream().map(t->t.getPrice()).collect(Collectors.toSet());
		int minPrice = prices.stream().min(Comparator.naturalOrder()).get().intValue();
		int maxPrice = prices.stream().max(Comparator.naturalOrder()).get().intValue();

		System.out.println("=============FILTERED PRODUCTS");
		for (ProdIdNameMan prodIdNameMan : firstFiltered) {
			System.out.println(prodIdNameMan.getName());
		}


		return new ProductPageDto(firstFiltered,null,minPrice,maxPrice,0,0);
	}
}
