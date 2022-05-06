package org.mykola.kindershop2.dto.projections;

public class ProductNamePriceImageDto {
	private String name;
	private float price;
	private String image;
	
	public ProductNamePriceImageDto(String name, float price, String image) {
		this.name = name;
		this.price = price;
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "\nProductNamePriceImageDto{" +
				       "\n\tname='" + name + '\'' +
				       "\n\t, price=" + price +
				       "\n\t, image='" + image + '\'' +
				       '}';
	}
}
