package org.mykola.kindershop2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.fastinfoset.util.CharArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mykola.kindershop2.entity.*;
import org.mykola.kindershop2.entity.manufacturer.ManNamePrj;
import org.mykola.kindershop2.entity.tmpDto.CatTemp;
import org.mykola.kindershop2.repository.*;
import org.mykola.kindershop2.repository.temp.CatTempRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ManufacturerServiceTest {
	@Autowired
	ManufacturerRepository manRepo;
	
	@Autowired
	ProdCatRepository prodCatRepo;
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CatCategoryRepository catCatRepo;
	@Autowired
	CategoryRepository catRepo;
	@Autowired
	CatTempRepo catTempRepo;

	
//	@Test
//	public void findProductsProjection(){
//		List<Object[]> projection=productRepo.getProducts(4276L);
//		for (Object obj: projection){
//
//		}
//	}


//	@Test
//	public void prodCatExperiment(){
//		CatCategory ct= catCatRepo.findById(1117L).get();
////		System.out.println(ct);
//	}
	
	
//	@Test
//	public void cleanUpManufacturers() {
//		 List<Manufacturer> initialList=new ArrayList<>();
//		List<Manufacturer> emptyList=new ArrayList<>();
//		initialList = manRepo.findAll();
//		System.out.println("Amount of Manufacturers "+initialList.size());
//
//		for (Manufacturer man : initialList) {
//
//			if (man.getProdCatList().isEmpty()){
//				emptyList.add(man);
//			}
//
//		}
//		System.out.println("\nList of empty mans contains "+ emptyList.size());
//
//		for (Manufacturer manToRemove: emptyList){
//			manRepo.delete(manToRemove);
//		}
//
//		}//method End
	
	@Test
	public void removeTrash(){
		Optional<Manufacturer> toRemove=manRepo.findById(5L);
		Manufacturer deletedMan=toRemove.get();
		System.out.println(toRemove);
		manRepo.delete(deletedMan);
	}
	
	@Test
	public void categoryCount(){
		//2889
		//2698
		List catTotal=catCatRepo.findAll();
		System.out.println("Total amount of categories: " + catTotal.size());
	}
	
	@Test
	public void productCount(){
		List<ProdCat> productTotal = prodCatRepo.findAll();
		System.out.println("Total products: "+ productTotal.size());
		
		List catTotal=catCatRepo.findAll();
		System.out.println("Total amount of categories: " + catTotal.size());
		
//		List<ProdCat> productTotalNullCat = prodCatRepo.findByCatId(0L);
//		System.out.println("Category null Products: " + productTotalNullCat.size());
//		for (ProdCat prodCat : productTotalNullCat) {
//			prodCatRepo.delete(prodCat);
//		}
		
	}
	
	@Test
	public void findNullCatProduct(){
		List<ProdCat> nulList=prodCatRepo.findByCatId(null);
		System.out.println(nulList.size());
		System.out.println(nulList);
		
//		привязать в энтить массив картинок, и только после этого уже удалять
	}
	
	@Test
	public void prodCatCleanup(){
		List <Long> catIdList= new ArrayList();
		List catTotal=catCatRepo.findAll();
		List<ProdCat> productTotal = prodCatRepo.findAll();
		List<ProdCat> slectedEmpty = new ArrayList<>();
		
		for (ProdCat product : productTotal){
			
			if (product.getCategoryList().isEmpty()){
				slectedEmpty.add(product);
			}
			
		}
		
		System.out.println(slectedEmpty.size());
		System.out.println(slectedEmpty);
		
	}
	
	@Test
	public void prodCatCleanpPreparation(){
		List <Long> catIdList= new ArrayList();
		List<Long> prodCatIdList = new ArrayList();
		
		List <CatCategory>catTotal=catCatRepo.findAll();
		List<ProdCat> productTotal = prodCatRepo.findAll();
		List<ProdCat> slectedNotEmpty = new ArrayList<>();
		
		Long ProdCatToChangeId;
		boolean startChange=false;
		
		for(CatCategory catId : catTotal){
			catIdList.add(catId.getId());
		}
		
		for (ProdCat product : productTotal){
			
			if (!product.getCategoryList().isEmpty()){
				slectedNotEmpty.add(product);
			}
			
		}
//Собственно основной алгоритм
		for (ProdCat prodCat : slectedNotEmpty) {
			List<Long> catCatIdList = new ArrayList();
//			Fill catList with Id
			for (CatCategory cat: prodCat.getCategoryList()){
				catCatIdList.add(cat.getId());
			}
			
			for(long id: catIdList){
				for(long comparating: catCatIdList){
					if(id==comparating){
						prodCat.setTrash(false);
						prodCatRepo.save(prodCat);
						System.out.print("saved " + prodCat.getId() +"\t");
					}
				}
				
			}
			
		}
		
		
		System.out.println("The Selected List contains " + slectedNotEmpty.size() + " objects");
		
		System.out.println("The CatList contains "+catIdList.size() + " objects");
//		System.out.println(catIdList);
		
	}//method End
	
	@Test
	public void cleanupTrash2(){
		List <Long> catIdList= new ArrayList();
		List<Long> prodCatIdList = new ArrayList();
		
		List <CatCategory>catTotal=catCatRepo.findAll();
		List<ProdCat> productTotal = prodCatRepo.findAll();
		List<ProdCat> slectedNotEmpty = new ArrayList<>();
		int count = 0;
		List<Long> checkedProds=new ArrayList<>();
		List<Long>missingCats=new ArrayList<>();
		Set<Long> checkedCats=new HashSet<>();
		

		//		List of Category Id
		for(CatCategory catId : catTotal){
			catIdList.add(catId.getId());
		}

		//		List of nonEmpty Products
		for (ProdCat product : productTotal){
			if (!product.getCategoryList().isEmpty()){
				slectedNotEmpty.add(product);
			}
		}
		
		//		----- Main algo----
		
		// Iteration of Propuct List
		for(ProdCat prodCat:slectedNotEmpty){
		//inner iteration for product's category List
			for (CatCategory catCategory : prodCat.getCategoryList()) {
				//проверяем, содержит ли totalIdList Id текущей сатегории
//				if( catIdList.contains(catCategory.getId())){
//					System.out.print("checked " + prodCat.getName() +" counter= " +count +"\t");
//					checkedProds.add(prodCat.getId());
//					count++;
//					break;
//				}
				
				if( catIdList.contains(catCategory.getId())){
//					System.out.print("Missing cat " + catCategory.getName() +" counter= " +count +"\t");
//					missingCats.add(catCategory.getId());
					checkedCats.add(catCategory.getId());
//					добавить Set
					count++;
//					break;
				}
				
			}
			
		}
		System.out.println("\n--------RESULTS-----------");
		System.out.println("catIdList size " + catIdList.size());
		System.out.println("checkedCats size " + checkedCats.size());
//		35s646ms
//		catIdList size 2588
//		checkedCats size 2199
	}
	
	@Test
	public void setTrash(){
	List<ProdCat> total =prodCatRepo.findByTrash(false);
		System.out.println("Found " + total.size());
	}
	
	@Test
	public void manCategorySorted() {
		CatTemp main = new CatTemp();
			main.setId(40L);
			main.setName("Root");
			catTempRepo.save(main);
		
		
		CatTemp child1 = new CatTemp();
			child1.setId(1100L);
			child1.setName("Child 1");
			child1.setParent(main);
			catTempRepo.save(child1);
			
//			main.addChild(child1);
//			catTempRepo.save(main);


//		CatTemp child2 = new CatTemp();
//			child1.setId(1200L);
//			child1.setName("Child 2");
//			catTempRepo.save(child2);
//
//		CatTemp child3 = new CatTemp();
//			child1.setId(1300L);
//			child1.setName("Child 3");
//			catTempRepo.save(child3);
//
//		List<CatTemp> childList=new ArrayList<CatTemp>();
//			childList.add (child1);
//			childList.add (child2);
//			childList.add (child3);
		
//		System.out.println(childList);
//		System.out.println("=========---------=============");
//		main.setChildrenlist(childList);
//		catTempRepo.save(main);
		
		System.out.println("main -> "+main);
		System.out.println("find All "+catTempRepo.findAll());
	}
	
	@Test
	public void alphabetBuilder() throws JsonProcessingException {
		SortedSet<Character> charAlpha = new TreeSet<>();
		List <ManNamePrj> initialSet= manRepo.findNames();
		//good
		List<Character> firstLetters2 = initialSet.stream().map(chr -> chr.getName().charAt(0)).distinct().collect(Collectors.toList());
		List<Character> firstLetters = initialSet.stream().map(chr -> chr.getName().charAt(0)).collect(Collectors.toList());
		
		System.out.println("\n=========-----firstLetters2----==========");
		System.out.println(firstLetters2);
		
		
		for (Character firstLetter : firstLetters) {
			charAlpha.add(firstLetter);
		}
		
		System.out.println("\n===========-- charAlpha --============");
		System.out.println(charAlpha);
		
		ObjectMapper objMapper =new ObjectMapper();
		String alphabetMan = objMapper.writeValueAsString(firstLetters2);
		System.out.println("\n================-- alphabetMan --=========");
		System.out.println(alphabetMan);
	}
	
//	@Test
//	public void findSmall(){
//		List<ManNamePrj>small=manRepo.findByNameStartingWith('d');
//		System.out.println("Found " + small.size() + " elements");
//		List<String> foundList = small.stream().map(e -> e.getName()).distinct().collect(Collectors.toList());
//		System.out.println(foundList);
//	}
}
