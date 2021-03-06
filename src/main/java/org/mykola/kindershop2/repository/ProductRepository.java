package org.mykola.kindershop2.repository;

import org.mykola.kindershop2.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE p.catId = :id")
	Page <Product> findByCatId(@Param("id") Long id, Pageable pageable);
	
	
	@Query(value = "SELECT p.name, p.price, p.image FROM Product p WHERE p.catId = :id")
	List<Object[]> getProducts(@Param("id") Long id);
}
