package org.mykola.kindershop2.repository;

import org.mykola.kindershop2.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>  {
	
//	List<Manufacturer> findDistinctAll();
}
