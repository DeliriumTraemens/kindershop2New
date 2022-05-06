package org.mykola.kindershop2.service;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.mykola.kindershop2.dto.ManufacturerPageDto;
import org.mykola.kindershop2.dto.projections.ManIdName;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.repository.ManufacturerRepository;
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
	
	@Autowired
	public ManufacturerService(ManufacturerRepository manRepo) {
		this.manRepo = manRepo;
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
	
	
	
	public ManufacturerPageDto findAllPaged() {
		Pageable pageRequest= PageRequest.of(0,9);
		Page <Manufacturer> manPage = manRepo.findAll(pageRequest);
		return new ManufacturerPageDto(manPage.getContent(),0,manPage.getTotalPages());
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
}
