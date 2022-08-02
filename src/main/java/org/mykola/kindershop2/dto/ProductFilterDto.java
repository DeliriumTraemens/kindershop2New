package org.mykola.kindershop2.dto;

import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameCard;
import org.mykola.kindershop2.dto.projections.product.ProdIdNameMan;
import org.mykola.kindershop2.entity.Product;

import java.util.List;

public class ProductFilterDto {
    private List<ProdIdNameMan> products;
    private List<ManIdNameCard> manufacturers;
    private int minPrice;
    private int maxPrice;
}
