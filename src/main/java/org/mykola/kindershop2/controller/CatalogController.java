package org.mykola.kindershop2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mykola.kindershop2.entity.Catalog;
import org.mykola.kindershop2.repository.CatalogRepository;
import org.mykola.kindershop2.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/catalog")
@CrossOrigin("*")
public class CatalogController {
	
	private final CatalogRepository cataRepo;
	private final CatalogService catServ;
	@Autowired
	public CatalogController(CatalogRepository cataRepo, CatalogService catServ) {
		this.cataRepo = cataRepo;
		this.catServ = catServ;
	}
	
//	@GetMapping
//	public List<Catalog> getCatalog(){
//		return cataRepo.findCatalog(1130L);
//	}
	
	@GetMapping
	public Catalog getCatalog() {
		return cataRepo.findById(30L).get();
	}
	
	@GetMapping("/buildCatalog")
	public String buildCatalog() throws IOException {
		Catalog catalog = cataRepo.findById(30L).get();
		
		return catServ.writeCatalogToStringString(catalog);
	}
	
	private String writeCatalogToStringString(Catalog catalog) throws IOException {
		ObjectMapper objectMaper = new ObjectMapper();
		FileOutputStream fos = new FileOutputStream("D:\\docs\\catalog\\catalog.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		String catalogString = objectMaper.writeValueAsString(catalog);
		
		oos.writeObject(catalogString);
		oos.close();
		
		return catalogString;
	}
	
	@GetMapping("/getCatalog")
	public String giveCatalog() throws IOException, ClassNotFoundException {
		return catServ.getCatalogString();
	}
	
	private String getCatalogString() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("D:\\docs\\catalog\\catalog.bin");
		ObjectInputStream ois = new ObjectInputStream(fis);
		String catalog = (String) ois.readObject();
		
		return catalog;
	}
	
	
}
