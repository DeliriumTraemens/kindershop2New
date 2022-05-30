package org.mykola.kindershop2.service;

import org.mykola.kindershop2.dto.prodSearchCardDto.ProductSearchCardDto;
import org.mykola.kindershop2.entity.tmpDto.CatRanged;
import org.mykola.kindershop2.entity.tmpDto.CatRangedDto;
import org.mykola.kindershop2.entity.tmpDto.CatRangedDtoDel;
import org.mykola.kindershop2.entity.tmpDto.ProductSearchCard;
import org.mykola.kindershop2.repository.searchCard.CatRangedDtoDelRepo;
import org.mykola.kindershop2.repository.searchCard.CatRangedDtoRepo;
import org.mykola.kindershop2.repository.searchCard.ProdSearchCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdSearchCardService {
	@Autowired
	ProdSearchCardRepo pscr;
	
	@Autowired
	CatRangedDtoRepo crdr;
	
	@Autowired
	CatRangedDtoDelRepo crdlr;
	
	// TODO: 25.05.2022 change return Type to List
//	public ProductSearchCardDto rangeCats(Long id) {
	public ProductSearchCardDto rangeCats(ProductSearchCard prSearchCard) {
		
		ProductSearchCardDto prscDto = createProductSearchCardDto(prSearchCard);
		
		createRootCategory();
		
		for (CatRanged category : prSearchCard.getCategories()) {
			CatRangedDto catDto = new CatRangedDto();
						 catDto.setId(category.getId());
						 catDto.setName(category.getName());
			
			CatRanged parent = category.getParent();
				
//				catDto.setParent(parent);
				catDto.setParId(parent.getId());
				
			crdr.save(catDto);
		}
		
		
		for (CatRangedDto catRangedDto : crdr.findAll()) {
			if(catRangedDto.getId() != 30L){
				CatRangedDto parent = crdr.findById(catRangedDto.getParId()).get();
				catRangedDto.setParent(parent);
				crdr.save(catRangedDto);
			}
		}
		
		CatRangedDto catSorted=crdr.findById(30L).get();
		System.out.println("Children ->"+catSorted.getChildren());
		prscDto.setCategories(catSorted.getChildren());
		//Return children List to assign further
		return prscDto;
	}//rangeCatsEnd
	
	private CatRangedDto createParent(CatRanged parent){
		CatRangedDto parentDto= new CatRangedDto();
		parentDto.setId(parent.getId());
		parentDto.setName(parent.getName());
		return parentDto;
	}
	
	private ProductSearchCardDto createProductSearchCardDto(ProductSearchCard prSearchCard) {
		return new ProductSearchCardDto(prSearchCard.getId(),
				                        prSearchCard.getName(),
				                        prSearchCard.getImage(),
				                        prSearchCard.getManufacturer());
	}
	
	private void createRootCategory() {
//		CatRangedDto bigRoot = new CatRangedDto();
//					bigRoot.setId(1L);
//					bigRoot.setParId(0L);
//					bigRoot.setName("MainRoot");
//		crdr.save(bigRoot);
		CatRangedDto root=new CatRangedDto();
					 root.setId(30L);
					 root.setParId(0L);
					 root.setName("Root");
//					 root.setParent(bigRoot);
		crdr.save(root);
	}
	
	private void clearDbTable() {
//		List<CatRangedDtoDel> toDelete= new ArrayList<>();
//		for (CatRangedDtoDel catRangedDto : crdlr.findAll()) {
//			if(catRangedDto.getId() != 30L){
//				toDelete.add(catRangedDto);
//			}
//		}
//		crdlr.clearTheTable();
//		crdlr.deleteAll(toDelete);
	}
}
