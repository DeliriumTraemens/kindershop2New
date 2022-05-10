package org.mykola.kindershop2.repository;

import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManIdNameEntityRepo extends JpaRepository<ManIdNameEntity, Long> {

}
