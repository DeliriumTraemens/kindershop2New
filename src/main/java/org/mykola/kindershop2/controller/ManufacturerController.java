package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.dto.CatList;
import org.mykola.kindershop2.dto.ManIdNamePickPageDto;
import org.mykola.kindershop2.dto.projections.CatIdNameDto;
import org.mykola.kindershop2.dto.projections.ManIdName;
import org.mykola.kindershop2.entity.CatCategory;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.entity.ProdCat;
import org.mykola.kindershop2.entity.tmpDto.CatTemp;
import org.mykola.kindershop2.entity.tmpDto.CatTempSave;
import org.mykola.kindershop2.repository.ManufacturerRepository;
import org.mykola.kindershop2.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/manufacturer")
public class ManufacturerController {
	public static final int ITEMS_PER_PAGE=20;
	
	private final ManufacturerService manService;
	private final ManufacturerRepository manRepo;
	
	@Autowired
	public ManufacturerController(ManufacturerService manService, ManufacturerRepository manRepo) {
		this.manService = manService;
		this.manRepo = manRepo;
	}
	
	
//	Change to ManIdNamePic type
//	@GetMapping
//	public ManufacturerPageDto getInitialPaged(){
//		return manService.findAllPaged();
//	}
	@GetMapping
	public ManIdNamePickPageDto getInitialPaged(){
		return manService.findAllPaged();
	}
	//Old Version
//	@GetMapping("/page")
//	public ManufacturerPageDto getManufacturerPage(@RequestParam(value = "page") int page){
//		return manService.findRequestedPage(page);
//	}
	
	@GetMapping("/page")
	public ManIdNamePickPageDto getManufacturerPage(@RequestParam(value = "page") int page){
		return manService.findRequestedPage(page);
	}
	
	@GetMapping("/{id}")
	public Manufacturer findOneById(@PathVariable(value = "id")Long id){
		return manRepo.findById(id).get();
	}
	
	@GetMapping("/catprod")
	public List<ProdCat> manufacturCategoryProd(
			@RequestParam(value="catId")Long catId,
			@RequestParam(value="manId")Long manId){
		
		return manService.getCatProductsList(catId, manId);
	}
	
	
	@GetMapping("/cat/{id}")
	public Set<CatIdNameDto> buildCategoryListByManufacturer(@PathVariable(value = "id")Long id){
		Set<CatCategory>catManSet= new HashSet<>();
		Set<CatIdNameDto> catIdNameDto= new HashSet<>();
		Manufacturer man=manRepo.findById(id).get();
//		TODO Вероятно нужна простая ДТО-Энтить с чилдренСетом , фильтрованном по продукт - производитель
		for(ProdCat prodCat: man.getProdCatList() ){
			for (CatCategory catCategory : prodCat.getCategoryList()) {
				catIdNameDto.add(new CatIdNameDto(catCategory.getId(),
				                                  catCategory.getName(), catCategory.getParentId()));
			}
		}
//		return man.getProdCatList();
		System.out.println(catIdNameDto);
		return catIdNameDto;
	}
	
//	--------------------------------------------------------------TEST---------------
	//2
	@GetMapping("/test/cattemp/{id}")
	public List<CatTemp> manCategorySorted(@PathVariable(value ="id") Long id){
		return manService.manCategorySorted(id);
	}
	
	
	//1
	@GetMapping("/test/cat/{id}")
//	public Set<Set<CatList>> manCategoryIerarchy(@PathVariable(value = "id")Long id){
	public Set<CatList> manCategoryIerarchy(@PathVariable(value = "id")Long id){
		
		Set<CatList> initialList=new HashSet<>();
//		List<CatList> initialList=new ArrayList<>();
		Set<CatList> rootList=new HashSet<>();
		Set<CatList>resultList=new HashSet<>();
		
		Set<Set<CatList>> collector= new HashSet<>();
		
		
		Manufacturer man = manRepo.findById(id).get();
		//START
		
		//InitialList of total categories List
		//Возможно сразу его отконвертить в CatList??
		for (ProdCat prodCat : man.getProdCatList()) {
			for (CatCategory catCategory : prodCat.getCategoryList()) {
				initialList.add(new CatList(catCategory.getId(), catCategory.getParentId(), catCategory.getName()));
			}
		} //InitialList of total categories List end
		
		//Extract Roots  from InitialList
		Set<CatList> collectRoots = getCatCategories(initialList, 0L);
		
		
		
		
		
		for (CatList rootCatElem : collectRoots) {
			//Извлекли корневой элемент
//			//теперь для него ищем в initialList элемент , у которого catCategory.parentId == catList.id
			Set<CatList> collect1 = initialList.stream().filter(t -> t.getParentId().equals(rootCatElem.getId()) )
					                             .collect(Collectors.toSet());
			
			// Нужна рекурсия для обхода collect1 -- и условие для рекурсии
			
			for (CatList catListChildren : collect1) {
				Set<CatList> children = initialList.stream().filter(t -> t.getParentId().equals(catListChildren.getId()) )
						                        .collect(Collectors.toSet());
				catListChildren.setChildren(children);
			resultList.add(catListChildren);
			}
			
			
			//TODO add parent to every child in children ???
			rootCatElem.setChildren(collect1);
			//добавляем рутКат в Сет КатЛист
//			resultList.add(rootCatElem);
//			collector.add(collect1);
//			System.out.println(rootCatElem.toString());
		}
//			System.out.println("===================");
//			System.out.println(collector.toString());
		


//		return initialList;
//		return rootList;
		return resultList;
//		return collectRoots;
//		return collector;
	}//EndPoint method end
	
//
	private Set<CatList> getCatCategories(Set<CatList> initialList, Long id) {
	
		return initialList.stream().filter( c -> c.getParentId() == 0L ).collect(Collectors.toSet());
	}


//	--------------------------------------------------------------
	
	@GetMapping("/ref")
	public ManIdName getByName(){
		return manService.findName("Hasbro");
	}
	
	@GetMapping("/refs")
	public List<ManIdName>findAllProjected(){
		return manService.findAllProjected();
	}
	
	
	
	@PostMapping("/editPic")
	public ManIdNamePickPageDto editPic(@RequestParam("id")Long id,
	                                  @RequestParam("file")MultipartFile file,
	                                  @RequestParam("page") int page) throws IOException {
		return manService.editPicture(id, file, page);
	}
	
}
