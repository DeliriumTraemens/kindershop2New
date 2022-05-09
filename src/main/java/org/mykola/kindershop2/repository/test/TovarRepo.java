package org.mykola.kindershop2.repository.test;

import org.mykola.kindershop2.entity.test.Tovar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TovarRepo extends JpaRepository<Tovar, Long> {

}
