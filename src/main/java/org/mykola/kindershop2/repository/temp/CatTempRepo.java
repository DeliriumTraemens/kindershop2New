package org.mykola.kindershop2.repository.temp;

import org.mykola.kindershop2.entity.tmpDto.CatTemp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatTempRepo extends JpaRepository <CatTemp, Long> {
	List<CatTemp> findByParentId(long l);
}
