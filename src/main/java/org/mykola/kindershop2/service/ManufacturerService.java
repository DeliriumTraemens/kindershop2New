package org.mykola.kindershop2.service;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ManufacturerService {
	@Value("${upload.path}")
	private String uploadPath;
	
//	private final ManufacturerRepository manRepo;
	@Autowired
	ManufacturerRepository manRepo;
//	private final ManIdNameEntityRepo manIdNameDtoRepo;
	@Autowired
	ManIdNameEntityRepo manIdNameDtoRepo;
//	private final CatCategoryRepository catCatRepo;
	@Autowired
	CatCategoryRepository catCatRepo;
//	private final CatTempRepo catTempRepo;
	@Autowired
	CatTempRepo catTempRepo;
//	private final CatTempSaveRepo catTempSaveRepo;
	@Autowired
	CatTempSaveRepo catTempSaveRepo;
//	private final ProdCatRepository prodCatRepo;
	@Autowired
	ProdCatRepository prodCatRepo;
	@Autowired
	CatTempService cts;
	
//	@Autowired
//	public ManufacturerService(
//			ManufacturerRepository manRepo,
//			ManIdNameEntityRepo manIdNameDtoRepo,
//			CatCategoryRepository catCatRepo,
////			CatTempRepo catTempRepo,
//			CatTempSaveRepo catTempSaveRepo,
//			ProdCatRepository prodCatRepo) {
//		this.manRepo = manRepo;
//		this.manIdNameDtoRepo = manIdNameDtoRepo;
//
//		this.catCatRepo = catCatRepo;
////		this.catTempRepo = catTempRepo;
//		this.catTempSaveRepo = catTempSaveRepo;
//		this.prodCatRepo = prodCatRepo;
//	}
	
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
//	@Transactional
	public Iterable<CatTemp> manCategorySorted(Long id) {
//	public List<CatTemp> manCategorySorted(Long id) {
		//a -- cleanUp table
		catTempRepo.deleteAll();
		catTempSaveRepo.deleteAll();
		
		//1 -- Declare and init working Collections
//		List<CatTempSave> catTempStartSet= new ArrayList<>();
		//1a setUp root Cats what's for?
		CatTempSave rootCat = catTempSaveRepo.save(new CatTempSave(30L,0L,"Root"));
		CatTemp rootCatTemp = new CatTemp();
				rootCatTemp.setId(30L);
				rootCatTemp.setName("RootTemp");
		catTempRepo.save(rootCatTemp);
		
		//2 - get requested Manufacturer
		Manufacturer man = manRepo.findById(id).get();
		
		//3 Populate working collections
		for (ProdCat prodCat : man.getProdCatList()) {
			for (CatCategory catCategory : prodCat.getCategoryList()) {
				//May be add CatTempSave = просто класс с парентАйди, и по нему уже мапить энтить? -YYYEEEEE
				catTempSaveRepo.save(new CatTempSave(catCategory.getId(),
				                                     catCategory.getParentId(),
				                                     catCategory.getName()));
				
			}
		} //InitialList of total categories List end
		
		
		List<CatTempSave> catTempStartSet = catTempSaveRepo.findAll();
		
//		System.out.println("======= catTempStartSet =====");
//		System.out.println(catTempStartSet);
		
		int i=0;
		for (CatTempSave catTempSave : catTempStartSet){
			if(catTempSave.getId() != 30L) {
				System.out.println("catTempSave -=> " + catTempSave);
				CatTempSave currentParent = catTempSaveRepo.findById(catTempSave.getParentId()).get();

//				new CatTemp(currentParent.getId(), currentParent.getName());
				CatTemp parent = new CatTemp();
						parent.setId(currentParent.getId());
						parent.setName(currentParent.getName());
						parent.setParId(currentParent.getParentId());
				catTempRepo.save(parent);

				
				}
		}//End for?
		
		for (CatTemp catTemp : catTempRepo.findAll()) {
			if(catTemp.getParId()!=0L){
				System.out.println("catTemp ==>> " + catTemp);
				CatTemp parent = catTempRepo.findById(catTemp.getParId()).get();
				System.out.println("parent -=> " + parent);
				catTemp.setParent(parent);
				catTempRepo.save(catTemp);
				System.out.println("\tcatTemp+Parent +==>> " + catTemp);
				
			}
		}


//		main Loop
		
		List<CatTemp> testCatTemp = (List<CatTemp>) catTempRepo.findAll();
		System.out.println("\n\n fromDB -->" + catTempRepo.findAll() + "\n");
		return cts.findNumber();
	}
		
	
} // CLASS END
