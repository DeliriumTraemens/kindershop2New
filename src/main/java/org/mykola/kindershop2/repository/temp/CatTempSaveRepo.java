package org.mykola.kindershop2.repository.temp;

import org.mykola.kindershop2.entity.tmpDto.CatTempSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatTempSaveRepo extends JpaRepository <CatTempSave, Long> {

}
