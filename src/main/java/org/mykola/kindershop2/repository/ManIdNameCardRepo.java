package org.mykola.kindershop2.repository;

import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManIdNameCardRepo extends JpaRepository<ManIdNameCard,Long> {
}
