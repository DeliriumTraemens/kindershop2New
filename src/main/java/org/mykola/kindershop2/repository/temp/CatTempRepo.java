package org.mykola.kindershop2.repository.temp;

import org.mykola.kindershop2.entity.tmpDto.CatTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatTempRepo extends JpaRepository<CatTemp, Long> {
	
	@Query(value = "SELECT c FROM CatTemp c JOIN FETCH c.childrenlist ch where c.id = :id")
	List<CatTemp> findRoot(@Param("id") Long id);

//	List<CatTemp> findByParentId(long l);
}
