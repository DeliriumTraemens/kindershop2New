package org.mykola.kindershop2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mykola.kindershop2.entity.Catalog;
import org.mykola.kindershop2.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CatalogService {
	private final CatalogRepository cataRepo;
	
	@Autowired
	public CatalogService(CatalogRepository cataRepo) {
		this.cataRepo = cataRepo;
	}
	
	
	public String writeCatalogToStringString(Catalog catalog) throws IOException {
		ObjectMapper objectMaper = new ObjectMapper();
		FileOutputStream fos = new FileOutputStream("D:\\docs\\catalog\\catalog.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		String catalogString = objectMaper.writeValueAsString(catalog);
		
		oos.writeObject(catalogString);
		oos.close();
		
		return catalogString;
	}
	
	public String getCatalogString() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("D:\\docs\\catalog\\catalog.bin");
		ObjectInputStream ois = new ObjectInputStream(fis);
		String catalog = (String) ois.readObject();
		
		return catalog;
	}
}
