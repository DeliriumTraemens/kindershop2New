package org.mykola.kindershop2.dto.prodSearchCardDto;

import java.util.Objects;

public class ProductSearchCardManufacturerDto {
	private Long id;
	private String name;
	private String image;
	
	//Cons
	public ProductSearchCardManufacturerDto() {
	}
	
	public ProductSearchCardManufacturerDto(Long id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image = image;
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
	
	//EqHc
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductSearchCardManufacturerDto that = (ProductSearchCardManufacturerDto) o;
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
		return "ProductSearchCardManufacturerDto{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       ", image='" + image + '\'' +
				       '}';
	}
}
