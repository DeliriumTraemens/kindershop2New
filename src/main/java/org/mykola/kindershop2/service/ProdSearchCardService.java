package org.mykola.kindershop2.service;

import org.mykola.kindershop2.entity.tmpDto.CatRanged;
import org.mykola.kindershop2.entity.tmpDto.CatRangedDto;
import org.mykola.kindershop2.entity.tmpDto.ProductSearchCard;
import org.mykola.kindershop2.repository.searchCard.CatRangedDtoRepo;
import org.mykola.kindershop2.repository.searchCard.ProdSearchCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdSearchCardService {
	@Autowired
	ProdSearchCardRepo pscr;
	
	@Autowired
	CatRangedDtoRepo crdr;
	
	public void rangeCats(ProductSearchCard prSearchCard) {
		
		CatRangedDto root=new CatRangedDto();
			root.setId(30L);
			root.setName("Root");
			crdr.save(root);
		
		for (CatRanged category : prSearchCard.getCategories()) {
			CatRangedDto catDto = new CatRangedDto();
				catDto.setId(category.getId());
				catDto.setName(category.getName());
			
				CatRanged parent = category.getParent();
				
				catDto.setParId(parent.getId());
				
//			System.out.println("catDto->"+catDto);
			crdr.save(catDto);
		}
		
		for (CatRangedDto catRangedDto : crdr.findAll()) {
			if(catRangedDto.getId() != 30L){
				CatRangedDto parent = crdr.findById(catRangedDto.getParId()).get();
				catRangedDto.setParent(parent);
				crdr.save(catRangedDto);
			}
		}
		
		System.out.println("root->"+crdr.findById(30L).get());
		//Return children List to assign further
		
	}
}
