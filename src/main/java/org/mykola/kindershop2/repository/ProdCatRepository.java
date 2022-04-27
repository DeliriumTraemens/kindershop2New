package org.mykola.kindershop2.repository;

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
}
