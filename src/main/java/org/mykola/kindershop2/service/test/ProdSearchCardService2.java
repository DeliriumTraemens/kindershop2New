package org.mykola.kindershop2.service.test;

import org.mykola.kindershop2.entity.tmpDto.CatIdNameDto2;
import org.mykola.kindershop2.entity.tmpDto.CatRanged;
import org.mykola.kindershop2.entity.tmpDto.ProductSearchCard;
import org.mykola.kindershop2.repository.searchCard.ProdSearchCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdSearchCardService2 {
	@Autowired
	ProdSearchCardRepo pscr;
	
	
	public List<CatIdNameDto2> testCard(Long id) {
		ProductSearchCard product= pscr.findById(id).get();
		/*id
		* name
		* image
		*
		* Manuf
		* id
		* name
		* image
		*
		* Categories<CatRanged>
		* */
		List<CatRanged> productCategories = product.getCategories();
		List<CatIdNameDto2> unSortedCats = new ArrayList<>();
		List<CatIdNameDto2> sortedCats = new ArrayList<>();
		
		
		CatRanged rootCR = getCatRanRoot(product);
		
		CatIdNameDto2 rootDto = getRootDto2(rootCR);
		
		for (CatRanged cat : productCategories) {
			unSortedCats.add(new CatIdNameDto2( cat.getId(), cat.getParent().getId(), cat.getName() ) );
		}
		
		sortedCats.add(getRootDto2(rootCR));
		System.out.println("Sorted initial =>" + sortedCats);
		
		
//
		for (CatIdNameDto2 unSortedCat : unSortedCats) {
			//
			List<CatIdNameDto2> collect = unSortedCats.stream().filter(c -> c.getParId() == unSortedCat.getId()).collect(Collectors.toList());
			//включить или исключить корневой элемент мля
			if(!collect.isEmpty() ){
				sortedCats.add(collect.get(0));
			}
//			System.out.println("collect ==>"+collect);
//			sortedCats.add(  catIdNameDto2);
		}
		
		
//		System.out.println("rootDto ->>"+rootDto);
		System.out.println("UnSorted "+unSortedCats);
		System.out.println("Sorted ->->" + sortedCats);
		
		/*Search By Parent*/
		
		
		
		return sortedCats;
	}
	
	private CatIdNameDto2 getRootDto2(CatRanged rootCR) {
		return new CatIdNameDto2(rootCR.getId(),
			                     rootCR.getParent().getId(),
			                     rootCR.getName() );
	}
	
	private CatRanged getCatRanRoot(ProductSearchCard product) {
		return product.getCategories().stream()
					   .filter(c -> c.getParent().getId().equals(30L))
				       .findFirst().get();
	}
	
	
}
