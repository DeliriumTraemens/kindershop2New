package org.mykola.kindershop2.service;

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
import java.util.List;

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
}
