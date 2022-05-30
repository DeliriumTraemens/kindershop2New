package org.mykola.kindershop2.dto.prodSearchCardDto;

import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameEntity;
import org.mykola.kindershop2.entity.tmpDto.CatRangedDto;

import java.security.PrivateKey;
import java.util.List;
import java.util.Objects;

public class ProductSearchCardDto {
	private Long id;
	private String name;
	private String image;
	
//	private ProductSearchCardManufacturerDto manufacturer;
	private ManIdNameEntity manufacturer;
	
	private List<CatRangedDto> categories;
	
	//Constructor
	
	
	public ProductSearchCardDto() {
	}
	
	public ProductSearchCardDto(Long id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image = image;
	}
	
	public ProductSearchCardDto(Long id, String name, String image, ManIdNameEntity manufacturer) {
		this.id = id;
		this.name = name;
		this.image = image;
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
	
	public ManIdNameEntity getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(ManIdNameEntity manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public List<CatRangedDto> getCategories() {
		return categories;
	}
	
	public void setCategories(List<CatRangedDto> categories) {
		this.categories = categories;
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
		ProductSearchCardDto that = (ProductSearchCardDto) o;
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
		return "\nProductSearchCardDto{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       ", image='" + image + '\'' +
				       ", \n manufacturer=" + manufacturer +
				       ", \n categories=" + categories +
				       '}';
	}
}
