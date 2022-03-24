package org.mykola.kindershop2.repository;

import org.mykola.kindershop2.entity.Catalog;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {
	
	@Query(value ="select c from Catalog c where c.id = :id")
	List<Catalog> findCatalog(@Param("id") Long id);
	
	@EntityGraph(attributePaths={"parent"})
	Optional<Catalog> findById(Long id);
}
