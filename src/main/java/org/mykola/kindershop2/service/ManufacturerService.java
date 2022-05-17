package org.mykola.kindershop2.service;

import org.mykola.kindershop2.dto.CatList;
import org.mykola.kindershop2.dto.ManIdNamePickPageDto;
import org.mykola.kindershop2.dto.projections.ManIdName;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameEntity;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNamePick;
import org.mykola.kindershop2.entity.CatCategory;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.entity.ProdCat;
import org.mykola.kindershop2.entity.tmpDto.CatTemp;
import org.mykola.kindershop2.entity.tmpDto.CatTempSave;
import org.mykola.kindershop2.repository.CatCategoryRepository;
import org.mykola.kindershop2.repository.ManIdNameEntityRepo;
import org.mykola.kindershop2.repository.ManufacturerRepository;
import org.mykola.kindershop2.repository.ProdCatRepository;
import org.mykola.kindershop2.repository.temp.CatTempRepo;
import org.mykola.kindershop2.repository.temp.CatTempSaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {
	@Value("${upload.path}")
	private String uploadPath;
	
	private final ManufacturerRepository manRepo;
	private final ManIdNameEntityRepo manIdNameDtoRepo;
	private final CatCategoryRepository catCatRepo;
	private final CatTempRepo catTempRepo;
	private final CatTempSaveRepo catTempSaveRepo;
	private final ProdCatRepository prodCatRepo;
	
	@Autowired
	public ManufacturerService(
			ManufacturerRepository manRepo, ManIdNameEntityRepo manIdNameDtoRepo, CatCategoryRepository catCatRepo, CatTempRepo catTempRepo,
			CatTempSaveRepo catTempSaveRepo, ProdCatRepository prodCatRepo
	                          ) {
		this.manRepo = manRepo;
		this.manIdNameDtoRepo = manIdNameDtoRepo;
		
		this.catCatRepo = catCatRepo;
		this.catTempRepo = catTempRepo;
		this.catTempSaveRepo = catTempSaveRepo;
		this.prodCatRepo = prodCatRepo;
	}
	
	public List<Manufacturer> findAll() {
		return manRepo.findAll();
	}
	
	public ManIdNamePickPageDto editPicture(Long id, MultipartFile file, int page) throws IOException {
		
		Manufacturer edited = manRepo.findById(id).get();

//		Directory path
		String pathToSave = uploadPath + "/manufacture/";
		
		//		MkDir
		File pathMaker = new File(pathToSave);
		if (! pathMaker.exists()) {
			pathMaker.mkdir();
		}
		
		edited.setImage("manufacture/" + file.getOriginalFilename());
		//upload path check
//		file.transferTo(new File(uploadPath+"/"+file.getOriginalFilename()));
		file.transferTo(new File(pathToSave + file.getOriginalFilename()));
		
		manRepo.save(edited);

//		return findAll();
		return findRequestedPage(page);
	}
	
	
	public ManIdNamePickPageDto findAllPaged() {
		Pageable pageRequest = PageRequest.of(0, 9);
//		Page <Manufacturer> manPage = manRepo.findAll(pageRequest);
		Page<ManIdNamePick> manPage = manRepo.getAll(pageRequest);
		return new ManIdNamePickPageDto(manPage.getContent(), 0, manPage.getTotalPages());
	}
	
	public ManIdNamePickPageDto findRequestedPage(int page) {
		Pageable pageRequest = PageRequest.of(page, 9);
//		Page<ManIdNamePick> manPage = manRepo.findAll(pageRequest);
		Page<ManIdNamePick> manPage = manRepo.getAll(pageRequest);
		
		return new ManIdNamePickPageDto(manPage.getContent(),
		                                pageRequest.getPageNumber(),
		                                manPage.getTotalPages());
	}
	
	public ManIdName findName(String hasbro) {
		return manRepo.findByName(hasbro);
	}
	
	public List<ManIdName> findAllProjected() {
		return manRepo.getAllProjected();
	}
	
	public List<ProdCat> getCatProductsList(Long catId, Long manId) {
		ManIdNameEntity manufacturer = manIdNameDtoRepo.findById(manId).get();
//		Set<ProdCat>prodsCatsList= prodCatRepo.findByCatIdAndManufacturer(catId, manufacturer);
		List<ProdCat> prodsCatsList = prodCatRepo.findByManufacturerAndCatId(manufacturer, catId);
		return prodsCatsList;
	}
	
	//          ==================--------@ manCategorySorted @----------=========================
	//<<<>>>\\
//	public CatTemp manCategorySorted(Long id) {
	public List<CatTemp> manCategorySorted(Long id) {
		//a -- cleanUp table
		catTempRepo.deleteAll();
		catTempSaveRepo.deleteAll();
		
		//1 -- Declare and init working Collections
//		List<CatTempSave> catTempStartSet= new ArrayList<>();
		//1a setUp root Cats what's for?
		CatTempSave rootCat = catTempSaveRepo.save(new CatTempSave(0L,null,"Root"));
		CatTemp rootCatTemp = catTempRepo.save(new CatTemp(0L,"RootTemp"));
		
		//2 - get requested Manufacturer
		Manufacturer man = manRepo.findById(id).get();
		
		//3 Populate working collections
		for (ProdCat prodCat : man.getProdCatList()) {
			for (CatCategory catCategory : prodCat.getCategoryList()) {
				//May be add CatTempSave = просто класс с парентАйди, и по нему уже мапить энтить? -YYYEEEEE
				catTempSaveRepo.save(new CatTempSave(catCategory.getId(), catCategory.getParentId(), catCategory.getName()));
				
			}
		} //InitialList of total categories List end
		
		
		List<CatTempSave> catTempStartSet = catTempSaveRepo.findAll();
		
		System.out.println("======= catTempStartSet =====");
		System.out.println(catTempStartSet);
		
		int i=0;
		for (CatTempSave catTempSave : catTempStartSet){
			if(catTempSave.getId() != 0L) {
//				System.out.println("========/////=========");
//				System.out.println(catTempSave);
				
				CatTemp catTemp = new CatTemp(catTempSave.getId(), catTempSave.getName());
				
				CatTempSave currentParent = catTempSaveRepo.findById(catTempSave.getParentId()).get();
				CatTemp parent = new CatTemp(currentParent.getId(), currentParent.getName());
//				catTempRepo.save(parent);
				
				catTemp.setParent(parent);
				catTempRepo.save(catTemp);
				
				System.out.println("currentParent  -> " + currentParent);
				System.out.println("parent -> " + parent);
				}
		}//end for?
			
//		return catTempRepo.findById(0L).get();
		return catTempRepo.findAll();
	}
		
	
} // CLASS END
