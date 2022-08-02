package org.mykola.kindershop2.dto.prodSearchCardDto;

import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameEntity;
import org.mykola.kindershop2.entity.tmpDto.CatIdNameDto2;
import org.mykola.kindershop2.entity.tmpDto.CatRangedDto;

import java.util.List;
import java.util.Objects;

public class ProductSearchCardNewDto {
	private Long id;
	private String name;
	private String image;
	private Float price;
	
	
	
	private Long catId;
	
	private ManIdNameEntity manufacturer;
	
	private List<CatIdNameDto2> categoryList;
	
	//Cons
	
	
	public ProductSearchCardNewDto() {
	}
	
	public ProductSearchCardNewDto(Long id, String name, String image, Float price, ManIdNameEntity manufacturer) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.manufacturer = manufacturer;
	}
	//GetSet
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public Long getCatId() {
		return catId;
	}
	
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	
	public ManIdNameEntity getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(ManIdNameEntity manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public List<CatIdNameDto2> getCategoryList() {
		return categoryList;
	}
	
	public void setCategoryList(List<CatIdNameDto2> categoryList) {
		this.categoryList = categoryList;
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
		ProductSearchCardNewDto that = (ProductSearchCardNewDto) o;
		return getId().equals(that.getId()) &&
				       getName().equals(that.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
	
	//toString
	
	@Override
	public String toString() {
		return "ProductSearchCardNewDto{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       ", image='" + image + '\'' +
				       ", manufacturer=" + manufacturer +
				       ", categories=" + categoryList +
				       '}';
	}
}
