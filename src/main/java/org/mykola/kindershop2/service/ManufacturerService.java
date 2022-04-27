package org.mykola.kindershop2.service;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	public List<Manufacturer> editPicture(Long id, MultipartFile file) throws IOException {
		
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
		
		return findAll();
	}
	
	public void cleanUpManufacturers(){
	
	}
}
