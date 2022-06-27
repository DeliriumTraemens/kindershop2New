package org.mykola.kindershop2.entity.product.projections;

import org.mykola.kindershop2.dto.projections.ManIdName;

public interface ProdIdNameImageManCat {
	Long getId();
	String getName();
	Float getPrice();
	Long getCatId();
	String getImage();
	
	
	ManIdName getManufacturer();
}
