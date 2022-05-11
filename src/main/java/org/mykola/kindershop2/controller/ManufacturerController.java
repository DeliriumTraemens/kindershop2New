package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.dto.ManIdNamePickPageDto;
import org.mykola.kindershop2.dto.projections.CatIdNameDto;
import org.mykola.kindershop2.dto.projections.ManIdName;
import org.mykola.kindershop2.entity.CatCategory;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.entity.ProdCat;
import org.mykola.kindershop2.repository.ManufacturerRepository;
import org.mykola.kindershop2.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
