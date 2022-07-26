package org.mykola.kindershop2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mykola.kindershop2.dto.projections.ManIdName;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameCard;
import org.mykola.kindershop2.entity.Product;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductPageDto {
	private List<Product> products;
	private List<ManIdNameCard> manufacturers;
	private int minPrice;
	private int maxPrice;
//	private List<ManIdNameFilter> manufacturers;
	private int currentPage;
	private int totalPages;
	
	
	
	@Override
	public String toString() {
		return "\nProductPageDto{" +
				       "products=" + products +
				       ", currentPage=" + currentPage +
				       ", totalPages=" + totalPages +
				       '}';
	}
}
