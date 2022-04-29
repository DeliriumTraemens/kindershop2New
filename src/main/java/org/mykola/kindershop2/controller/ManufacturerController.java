package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.dto.ManufacturerPageDto;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/manufacturer")
public class ManufacturerController {
	public static final int ITEMS_PER_PAGE=20;
	
	private final ManufacturerService manService;
	
	@Autowired
	public ManufacturerController(ManufacturerService manService) {
		this.manService = manService;
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
	
	@GetMapping("/page")
	public ManufacturerPageDto getManufacturerPage(@RequestParam(value = "page") int page){
		System.out.println("-------------------RECIEVED");
		System.out.println(manService.findRequestedPage(page).toString());
		return manService.findRequestedPage(page);
	}
	
	@PostMapping("/editPic")
	public List<Manufacturer> editPic(@RequestParam("id")Long id,
	                                  @RequestParam("file")MultipartFile file,
	                                  @RequestParam("page") int page) throws IOException {
		return manService.editPicture(id, file, page);
	}
	
}
