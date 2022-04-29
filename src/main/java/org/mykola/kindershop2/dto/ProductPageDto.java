package org.mykola.kindershop2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mykola.kindershop2.entity.Product;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductPageDto {
	private List<Product> products;
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
