package org.mykola.kindershop2.dto.projections.product;

import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameCard;

public interface ProdIdNameMan {
    Long getId();
    String getName();
    ManIdNameCard getManufacturer();
}
