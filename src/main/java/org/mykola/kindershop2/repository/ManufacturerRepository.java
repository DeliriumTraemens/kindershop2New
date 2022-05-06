package org.mykola.kindershop2.repository;

import org.mykola.kindershop2.dto.projections.ManIdName;
import org.mykola.kindershop2.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>  {
	
	ManIdName findByName(String name);
	
	@Query("SELECT m.id AS id, m.name AS name FROM Manufacturer m")
	List<ManIdName> getAllProjected();
	
	//	List<Manufacturer> findDistinctAll();
}
