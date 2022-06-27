/*
* 1-FindAll
* */
package org.mykola.kindershop2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mykola.kindershop2.dto.ManIdNamePickPageDto;
import org.mykola.kindershop2.dto.prodSearchCardDto.ProductSearchCardNewDto;
import org.mykola.kindershop2.dto.projections.ManIdName;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameEntity;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNamePick;
import org.mykola.kindershop2.entity.CatCategory;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.entity.ProdCat;
import org.mykola.kindershop2.entity.manufacturer.ManNamePrj;
import org.mykola.kindershop2.entity.tmpDto.CatIdNameDto2;
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
	
	//======== 1-FindAll ==========
	public List<Manufacturer> findAll() {
		return manRepo.findAll();
	}
	
	//======== 2-EditPicture ==========
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
	
	//======== 3-FindAllPaged ==========
	public ManIdNamePickPageDto findAllPaged() {
		Pageable pageRequest = PageRequest.of(0, 9);
//		Page <Manufacturer> manPage = manRepo.findAll(pageRequest);
		Page<ManIdNamePick> manPage = manRepo.getAll(pageRequest);
		return new ManIdNamePickPageDto(manPage.getContent(), 0, manPage.getTotalPages());
	}
	//======== 4-FindRequestedPage ==========
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
	
	//For ProductSearchCard List @GetMapping("/catprod")
	public List<ProductSearchCardNewDto> getCatProductsList(Long catId, Long manId) {
		ManIdNameEntity manufacturer = manIdNameDtoRepo.findById(manId).get();
		List<ProdCat> prodsCatsList = prodCatRepo.findByManufacturerAndCatId(manufacturer, catId);
		List<CatIdNameDto2>catIdND2List = new ArrayList<>();
		List<CatIdNameDto2>catIdND2SortedList = new ArrayList<>();
		List<ProductSearchCardNewDto> prSCNDto = new ArrayList<>();
		List<ProductSearchCardNewDto> resultList = new ArrayList<>();
		
		//todo catIdND2List отследить почему не самоочищается! -Done!
//		int number =1;
		for (ProdCat prodCat : prodsCatsList) {
			if(!catIdND2List.isEmpty()){
				catIdND2List.clear();
			}
			for (CatCategory catCategory : prodCat.getCategoryList()) {
				catIdND2List.add(new CatIdNameDto2(catCategory.getId(),catCategory.getParentId(),catCategory.getName()));
			}
			
			//Сюда пихуем обработчик листа категорийДто
			
//			catIdND2SortedList=catIdND2Sorter(catIdND2List);
//			System.out.println("SORTED catIdND2SortedList ->"+catIdND2SortedList);
			
			
			// Итоговая карточка товара для вывода
			ProductSearchCardNewDto prSCDto = new ProductSearchCardNewDto(prodCat.getId(), prodCat.getName(),prodCat.getImage(),prodCat.getManufacturer());
									//TODO replace plain argument with a method -- catIdND2Sorter(catIdND2List)
//									prSCDto.setCategoryList(catIdND2SortedList);
									prSCDto.setCategoryList( catIdND2Sorter(catIdND2List) );
			
			//Однако еще нужно добавить карточку в лист -DONE
			resultList.add(prSCDto);
		}
		
		
//		return prodsCatsList;
		return resultList;
	}
	
	//Сортер категорий
	private List<CatIdNameDto2> catIdND2Sorter(List<CatIdNameDto2> catIdND2List) {
		
		//вывести в тушку класса
		List<CatIdNameDto2> sorted = new ArrayList<>();
		
		List<CatIdNameDto2> noRootList=new ArrayList<>();
		
		//Make Root
		CatIdNameDto2 rootCat = catIdND2List.stream().filter(c -> c.getParId().equals(30L)).findFirst().get();
		//Add Root to Empty sorted List
		sorted.add(rootCat);
		
		//Collect cats excepting Root
		noRootList.addAll(0, catIdND2List.stream().filter(c -> c.getParId() != 30L).collect(Collectors.toList()));
		
		//The Main Routine
		for (int i = 0; i<noRootList.size(); i++) {
			CatIdNameDto2 curParent=sorted.get(sorted.size()-1);
			Optional<CatIdNameDto2> curChild = catIdND2List.stream().filter(c->c.getParId().equals(curParent.getId())).findFirst();
			if (curChild.isPresent()){
				sorted.add(curChild.get());
			}
		}
		noRootList.clear();
		
		return sorted;
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
//				System.out.println("catTemp ==>> " + catTemp);
				CatTemp parent = catTempRepo.findById(catTemp.getParId()).get();
//				System.out.println("parent -=> " + parent);
				catTemp.setParent(parent);
				catTempRepo.save(catTemp);
//				System.out.println("\tcatTemp+Parent +==>> " + catTemp);
				
			}
		}


//		main Loop
		
		List<CatTemp> testCatTemp = (List<CatTemp>) catTempRepo.findAll();
//		System.out.println("\n\n fromDB -->" + catTempRepo.findAll() + "\n");
		return cts.findNumber();
	}
		
	
	//Alphabet Builder
	public List <Character> alphabetBuilder (){
		List <ManNamePrj> initialSet= manRepo.findNames();
		//good
		List<Character> firstLetters2 = initialSet.stream().map(chr -> chr.getName().charAt(0)).distinct().collect(Collectors.toList());
//		ObjectMapper objMapper =new ObjectMapper();
//		String alphabetMan = objMapper.writeValueAsString(firstLetters2);
		System.out.println(firstLetters2);
		return firstLetters2;
	}
	
	public List<ManIdNamePick> findLetter(String letter) {
		return manRepo.findByNameStartingWith(letter);
	}
	
	public List<ManIdNamePick> editPictureAlphabet(Long id, String letter, MultipartFile file) throws IOException {
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
		
		return manRepo.findByNameStartingWith(letter);
		
	}
} // CLASS END
