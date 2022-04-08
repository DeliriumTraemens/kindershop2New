package org.mykola.kindershop2.repository;

import org.mykola.kindershop2.entity.CatCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatCategoryRepository extends JpaRepository <CatCategory, Long>{
	List<CatCategory> findByNameContaining(String name);
}
