package org.mykola.kindershop2.controller;

import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping
	public List<Manufacturer> getAll(){
		List<Manufacturer> total=manService.findAll();
		System.out.println(total);
		return total;
	}
	
	@PostMapping("/editPic")
	public List<Manufacturer> editPic(@RequestParam("id")Long id, @RequestParam("file")MultipartFile file) throws IOException {
		return manService.editPicture(id, file);
	}
	
}
