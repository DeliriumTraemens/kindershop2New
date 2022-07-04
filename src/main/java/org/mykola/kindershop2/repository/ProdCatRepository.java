package org.mykola.kindershop2.repository;

import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameEntity;
import org.mykola.kindershop2.entity.Manufacturer;
import org.mykola.kindershop2.entity.ProdCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProdCatRepository extends JpaRepository<ProdCat, Long> {
	Set<ProdCat> findDistinctByNameContaining(String name);
	
	List<ProdCat> findByNameLike(String name);
	
	List<ProdCat> findByCatId(Long catId);
	
	List<ProdCat> findByTrash(boolean b);
	
	Set<ProdCat> findByCatIdAndManufacturer(Long catId, ManIdNameEntity manufacturer);
	
	List<ProdCat> findByManufacturerAndCatId(ManIdNameEntity manufacturer, Long catId);
	
	Set<ProdCat> findDistinctByNameContainingOrderByManufacturer(String name);
	
	List<ProdCat> findByManufacturer(ManIdNameEntity man);
	
	List<ProdCat> findByManufacturerAndNameContaining(ManIdNameEntity man, String name);
	
	Set<ProdCat> findDistinctByNameContainingAndManufacturer(String name, ManIdNameEntity man);
	
	Set<ProdCat> findDistinctByNameContainingAndManufacturerOrderByPrice(String name, ManIdNameEntity man);
	
	Set<ProdCat> findDistinctByNameContainingOrderByPrice(String name);
	
	Set<ProdCat> findDistinctByNameContainingAndManufacturerOrderByPriceDesc(String name, ManIdNameEntity man);
}
