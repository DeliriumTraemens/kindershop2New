package org.mykola.kindershop2.repository.searchCard;

import org.mykola.kindershop2.entity.tmpDto.CatRangedDtoDel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRangedDtoDelRepo extends JpaRepository<CatRangedDtoDel, Long> {

//	@Modifying(clearAutomatically = true)
//	@Query("delete from CatRangedDtoDel c" )
//	void clearTheTable();
}
