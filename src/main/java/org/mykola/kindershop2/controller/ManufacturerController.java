package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.dto.ManufacturerPageDto;
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
	
//	@GetMapping
//	public ManufacturerPageDto getAll(){
////	public ManufacturerPageDto getAll(@RequestParam(value="page")int page,
////	                                  @PageableDefault(size = ITEMS_PER_PAGE, sort={"id"}, direction= Sort.Direction.ASC) Pageable pageable){
////		List<Manufacturer> total=manService.findAll();
////		System.out.println(total);
////		return total;
//		return manService.findAllPaged(0);
//	}
	
	@GetMapping
	public ManufacturerPageDto getInitialPaged(){
		return manService.findAllPaged();
	}
	
	@GetMapping("/{id}")
	public Manufacturer findOneById(@PathVariable(value = "id")Long id){
		return manRepo.findById(id).get();
	}
	
	@GetMapping("/cat/{id}")
	public Set<CatIdNameDto> buildCategoryListByManufacturer(@PathVariable(value = "id")Long id){
		Set<CatCategory>catManSet= new HashSet<>();
		Set<CatIdNameDto> catIdNameDto= new HashSet<>();
		Manufacturer man=manRepo.findById(id).get();
		for(ProdCat prodCat: man.getProdCatList() ){
			for (CatCategory catCategory : prodCat.getCategoryList()) {
//				CatIdNameDto catDto= new CatIdNameDto(catCategory.getId(),
//				                                      catCategory.getName());
				catIdNameDto.add(new CatIdNameDto(catCategory.getId(),
				                                  catCategory.getName()));
			}
			
		}
		
//		return man.getProdCatList();
		System.out.println(catIdNameDto);
		return catIdNameDto;
	}
	
	@GetMapping("/ref")
	public ManIdName getByName(){
		return manService.findName("Hasbro");
	}
	
	@GetMapping("/refs")
	public List<ManIdName>findAllProjected(){
		return manService.findAllProjected();
	}
	
	@GetMapping("/page")
	public ManufacturerPageDto getManufacturerPage(@RequestParam(value = "page") int page){
		System.out.println("-------------------RECIEVED");
		System.out.println(manService.findRequestedPage(page).toString());
		return manService.findRequestedPage(page);
	}
	
	@PostMapping("/editPic")
	public ManufacturerPageDto editPic(@RequestParam("id")Long id,
	                                  @RequestParam("file")MultipartFile file,
	                                  @RequestParam("page") int page) throws IOException {
		return manService.editPicture(id, file, page);
	}
	
}
