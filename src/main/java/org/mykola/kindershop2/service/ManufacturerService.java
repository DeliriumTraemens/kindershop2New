package org.mykola.kindershop2.service;

import org.mykola.kindershop2.dto.ManIdNamePickPageDto;
import org.mykola.kindershop2.dto.ManufacturerPageDto;
import org.mykola.kindershop2.dto.projections.ManIdName;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNamePick;
import org.mykola.kindershop2.entity.CatCategory;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.entity.ProdCat;
import org.mykola.kindershop2.repository.CatCategoryRepository;
import org.mykola.kindershop2.repository.ManufacturerRepository;
import org.mykola.kindershop2.repository.ProdCatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ManufacturerService {
	@Value("${upload.path}")
	private String uploadPath;
	
	private final ManufacturerRepository manRepo;
	private final CatCategoryRepository catCatRepo;
	private final ProdCatRepository prodCatRepo;
	
	@Autowired
	public ManufacturerService(ManufacturerRepository manRepo, CatCategoryRepository catCatRepo, ProdCatRepository prodCatRepo) {
		this.manRepo = manRepo;
		this.catCatRepo = catCatRepo;
		this.prodCatRepo = prodCatRepo;
	}
	
	public List<Manufacturer> findAll() {
		return manRepo.findAll();
	}
	
	public ManufacturerPageDto editPicture(Long id, MultipartFile file, int page) throws IOException {
		
		Manufacturer edited = manRepo.findById(id).get();
		
//		Directory path
		String pathToSave = uploadPath +"/manufacture/";

		//		MkDir
		File pathMaker = new File(pathToSave);
		if(!pathMaker.exists()){
			pathMaker.mkdir();
		}
		
		edited.setImage("manufacture/"+file.getOriginalFilename());
		//upload path check
//		file.transferTo(new File(uploadPath+"/"+file.getOriginalFilename()));
		file.transferTo(new File(pathToSave + file.getOriginalFilename()));
		
		manRepo.save(edited);
		
//		return findAll();
		return findRequestedPage(page);
	}
	
	
	
	public ManIdNamePickPageDto findAllPaged() {
		Pageable pageRequest= PageRequest.of(0,9);
//		Page <Manufacturer> manPage = manRepo.findAll(pageRequest);
		Page <ManIdNamePick> manPage = manRepo.getAll(pageRequest);
		return new ManIdNamePickPageDto(manPage.getContent(), 0, manPage.getTotalPages());
	}
	
	public ManufacturerPageDto findRequestedPage(int page) {
		Pageable pageRequest= PageRequest.of(page,9);
		Page<Manufacturer> manPage = manRepo.findAll(pageRequest);
		
		return new ManufacturerPageDto(manPage.getContent(),
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
		Manufacturer manufacturer = manRepo.findById(manId).get();
		List<ProdCat>prodsCatsList= prodCatRepo.findAllByCatIdAndManufacturer(catId,manufacturer);
		return prodsCatsList;
	}
}
