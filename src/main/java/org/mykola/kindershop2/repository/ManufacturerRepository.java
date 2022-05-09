package org.mykola.kindershop2.repository;

import org.mykola.kindershop2.dto.projections.ManIdName;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNamePick;
import org.mykola.kindershop2.entity.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>  {
	
	ManIdName findByName(String name);
	
	@Query("SELECT m.id AS id, m.name AS name FROM Manufacturer m")
	List<ManIdName> getAllProjected();
	
	
	Page<Manufacturer> findAll(Pageable pageRequest);
	
	@Query("SELECT m.id AS id, m.name AS name, m.image AS image FROM Manufacturer m")
	Page<ManIdNamePick> getAll(Pageable pageRequest);
	
	//	List<Manufacturer> findDistinctAll();
}
