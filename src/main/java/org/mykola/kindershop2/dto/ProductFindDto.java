package org.mykola.kindershop2.dto;

import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameEntity;
import org.mykola.kindershop2.entity.ProdCat;

import java.util.*;

public class ProductFindDto {

	private Set<ProdCat> products = new HashSet<>();
	private Set<ManIdNameEntity> manufacturers = new HashSet<>();
	
	//Constr
	
	
	public ProductFindDto(Set<ProdCat> products, Set<ManIdNameEntity> manufacturers) {
		this.products = products;
		this.manufacturers = manufacturers;
	}
	
	//	G/S
	
	public Set<ProdCat> getProducts() {
		return products;
	}
	
	public void setProducts(Set<ProdCat> products) {
		this.products = products;
	}
	
	public Set<ManIdNameEntity> getManufacturers() {
		return manufacturers;
	}
	
	public void setManufacturers(Set<ManIdNameEntity> manufacturers) {
		this.manufacturers = manufacturers;
	}


//	EqHc
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductFindDto that = (ProductFindDto) o;
		return Objects.equals(getProducts(), that.getProducts()) &&
				       Objects.equals(getManufacturers(), that.getManufacturers());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getProducts(), getManufacturers());
	}


//	ToStr
	
	@Override
	public String toString() {
		return "ProductFindDto{" +
				       "products=" + products +
				       ", manufacturers=" + manufacturers +
				       '}';
	}
}
