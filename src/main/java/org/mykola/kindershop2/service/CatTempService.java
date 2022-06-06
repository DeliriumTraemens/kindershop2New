package org.mykola.kindershop2.service;

import org.mykola.kindershop2.dto.ManCat;
import org.mykola.kindershop2.entity.CatCategory;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.entity.ProdCat;
import org.mykola.kindershop2.entity.tmpDto.CatTemp;
import org.mykola.kindershop2.entity.tmpDto.CatTempRead;
import org.mykola.kindershop2.repository.ManufacturerRepository;
import org.mykola.kindershop2.repository.temp.CatTempReadRepo;
import org.mykola.kindershop2.repository.temp.CatTempRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CatTempService {
//	@Autowired
	private final CatTempRepo catTempRepo;
	@Autowired
	CatTempReadRepo ctr;
	
	@Autowired
	ManufacturerRepository mfr;
	
	public CatTempService(CatTempRepo catTempRepo) {
		this.catTempRepo = catTempRepo;
	}
	
	public Iterable<CatTemp> findAll() {
		return getAll();
	}
	
	public CatTemp findById(long l) {
		return catTempRepo.findById(30L).get();
	}
	
	public Iterable<CatTemp> findNumber() {
		List<CatTemp> out= new ArrayList<>();
		
		out.add(catTempRepo.findById(30L).get());
		return out ;
	}
	
	//Cat Ierarchy
//	@Transactional
//	public Iterable catIerarchy(Long id){
	public void catIerarchy(Long id){
		
		//PrePare
		catTempRepo.deleteAll();

		//		Make the Root category with Id 30
		CatTemp rootCatTemp = new CatTemp();
		rootCatTemp.setId(30L);
		rootCatTemp.setName("RootTemp");
		catTempRepo.save(rootCatTemp);
		
		// Get the requested manufacturer
		Manufacturer man = mfr.findById(id).get();
		
		// Populate initial CatTemps
		for (ProdCat prodCat : man.getProdCatList()) {
			for (CatCategory catCategory : prodCat.getCategoryList()) {
//				ctwr.save(new CatTempWrite(catCategory.getId(),
				catTempRepo.save(new CatTemp(catCategory.getId(),
				                             catCategory.getName(),
				                             catCategory.getParentId()
				                           ));
			}
		} //EndOfFor
		
		
		
		//Assign Parents
		for (CatTemp catTemp : catTempRepo.findAll()) {
			if (catTemp.getId() != 30L) {
				CatTemp parent = catTempRepo.findById(catTemp.getParId()).get();

				catTemp.setParent(parent);

				catTempRepo.save(catTemp);
			}
		}
		
		
//		List<CatTemp> out= new ArrayList<>();
//			out.add(catTempRepo.findById(144L).get());
//		return findNumber();
//		return getAll();
//		return out ;
//		return ctr.findById(30L).get();
	}
	
	private List<CatTemp> getAll() {
		return catTempRepo.findAll();
	}
	
	//Manufacturer's Category Hierarchy
	public Set<ManCat> catIerarchy2(Long id) {
		
		Manufacturer man = mfr.findById(id).get();
		
		Set<ManCat> startCategoryList = new HashSet<>();
		
		//Fill the Total Set of categories
		for (ProdCat prodCat : man.getProdCatList()) {
			for (CatCategory catCategory : prodCat.getCategoryList()) {
				startCategoryList.add(new ManCat(catCategory.getId(), catCategory.getName(), catCategory.getParentId()));
			}
		}
		
		//Fill The RootList
		Set<ManCat> rootsList = startCategoryList.stream().filter(p -> p.getParentId() == 30L).collect(Collectors.toSet());
		
		//Recursive setting the Childrens (Yes Orthography mistake)))
		setChildrens(startCategoryList, rootsList);
		
		return rootsList;
	}
	
	private void setChildrens(Set<ManCat> startCategoryList, Set<ManCat> rootsList) {
		for (ManCat manCat : rootsList) {
			Set<ManCat>childrens = startCategoryList.stream().filter(t -> t.getParentId().equals(manCat.getId())).collect(Collectors.toSet());
			
			manCat.setChildren(childrens);
			
			//Recursion loop entrance
			if(!manCat.getChildren().isEmpty()){
				setChildrens( startCategoryList, manCat.getChildren());
			}
		}
	}
}//EoC
