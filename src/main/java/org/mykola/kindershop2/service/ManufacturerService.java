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
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	public List<CatTemp> manCategorySorted(Long id) {
//	public Set<CatTemp> manCategorySorted(Long id) {
		//a -- cleanUp table
		catTempRepo.deleteAll();
		
		//1 -- Declare and init working Collections
		Set<CatTemp> initialList = new HashSet<>();
		Set<CatList> initialListStart = new HashSet<>();
		Set<CatList> rootSet;
		
		//2 - get requested Manufacturer
		Manufacturer man = manRepo.findById(id).get();
		
		//3 Populate working collections
		for (ProdCat prodCat : man.getProdCatList()) {
			for (CatCategory catCategory : prodCat.getCategoryList()) {
				//May be add CatTempSave = просто класс с парентАйди, и по нему уже мапить энтить? -YYYEEEEE
				catTempRepo.save(new CatTemp(catCategory.getId(), catCategory.getName()));
				catTempSaveRepo.save(new CatTempSave(catCategory.getId(), catCategory.getParentId(), catCategory.getName()));
				//Cразу сохраняем в базу?
				
				//И ВОТ ТУТА маппим!!!!!
				// тупо забираем цыклом первый об
				
				initialList.add(new CatTemp(catCategory.getId(), catCategory.getName()));
				initialListStart.add(new CatList(catCategory.getId(), catCategory.getParentId(), catCategory.getName()));
			}
		} //InitialList of total categories List end
		int i=0;
		for (CatTemp catTemp: catTempRepo.findAll()){
			List<CatList> collect = initialListStart.stream().filter(c -> c.getParentId().equals(catTemp.getId())).collect(Collectors.toList());
			if(!collect.isEmpty()){
				CatList preParent = collect.get(0);
				CatTemp parent= catTempRepo.findById(preParent.getId()).get();
				
				System.out.println("=========Parent=============");
				System.out.println(parent);
				
				catTemp.setParent(parent);
				catTempRepo.save(catTemp);
			}
			
			System.out.println("=========collect"+i);
			System.out.println(collect);
			i++;
//			Long idc=found.getId();
//			System.out.println("=========Idc=========");
//			System.out.println(idc);
//			CatTemp parent = catTempRepo.findById(idc).get();
//			System.out.println("========parentFound======");
//			System.out.println(parent);
//			catTemp.setParent(parent);
//			catTempRepo.save(catTemp);
		}
		
		return catTempRepo.findByParentId(0L);
	}
		
			//			Long cid= parent1.getId();
//			Long parentId=parent1.getParentId();
//			System.out.println("===============parent===============");
//			System.out.println(parent1);
//			//			CatList parent = взять из сета объект по parentId ;
//			CatList cLParent = initialListStart.stream().filter(c->c.getId().equals(parentId)).findFirst().get();
//			System.out.println("===============cLPparent===============");
//			System.out.println(cLParent);

//


//

//		System.out.println(initialList);
//		return initialList;
}
