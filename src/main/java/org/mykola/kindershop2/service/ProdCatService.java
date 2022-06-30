package org.mykola.kindershop2.service;

import org.mykola.kindershop2.dto.ProductFind2Dto;
import org.mykola.kindershop2.dto.ProductFindDto;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameEntity;
import org.mykola.kindershop2.entity.ProdCat;
import org.mykola.kindershop2.repository.ProdCatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProdCatService {
	private final ProdCatRepository prodCatRepo;
	
	@Autowired
	public ProdCatService(ProdCatRepository prodCatRepo) {
		this.prodCatRepo = prodCatRepo;
	}
	
	
	public ProductFind2Dto findProducts(String name) {
		Set<ProdCat>products=prodCatRepo.findDistinctByNameContainingOrderByManufacturer(name);
		Set<ManIdNameEntity> manufacturers = products.stream().map(t -> t.getManufacturer()).collect(Collectors.toSet());
		List<ManIdNameEntity> manSorted = new ArrayList<>();
		manufacturers.forEach(jo -> manSorted.add(jo));
		
		List<ManIdNameEntity> collect = manSorted.stream()
				                                .sorted(Comparator.comparing(ManIdNameEntity::getName))
				                                .collect(Collectors.toList());
		
		System.out.println("=================---------------================\n"+collect);
		return new ProductFind2Dto(products, collect);
	}
}
