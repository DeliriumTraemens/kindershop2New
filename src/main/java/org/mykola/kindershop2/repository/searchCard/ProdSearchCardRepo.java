package org.mykola.kindershop2.repository.searchCard;

import org.mykola.kindershop2.entity.tmpDto.ProductSearchCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdSearchCardRepo extends JpaRepository<ProductSearchCard, Long> {

}
