package org.mykola.kindershop2.dto;

import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameEntity;
import org.mykola.kindershop2.entity.ProdCat;

import java.util.*;

public class ProductFind2Dto {
	private Set<ProdCat> products = new HashSet<>();
	private List<ManIdNameEntity>manufacturers = new ArrayList<>();
	
	//Cons
	public ProductFind2Dto(Set<ProdCat> products, List<ManIdNameEntity> manufacturers) {
		this.products = products;
		this.manufacturers = manufacturers;
	}
	
	//G/s
	
	public Set<ProdCat> getProducts() {
		return products;
	}
	
	public void setProducts(Set<ProdCat> products) {
		this.products = products;
	}
	
	public List<ManIdNameEntity> getManufacturers() {
		return manufacturers;
	}
	
	public void setManufacturers(List<ManIdNameEntity> manufacturers) {
		this.manufacturers = manufacturers;
	}
	
	//EqHc
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductFind2Dto that = (ProductFind2Dto) o;
		return getProducts().equals(that.getProducts()) &&
				       getManufacturers().equals(that.getManufacturers());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getProducts(), getManufacturers());
	}
}
