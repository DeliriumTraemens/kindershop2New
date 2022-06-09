package org.mykola.kindershop2.entity.product.image;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = {"id","name"})
@Table(name = "oc_product_image")
public class ProdImage {
	@Id
	@GeneratedValue
	@Column(name="product_image_id")
	private Long id;
	
	@Column(name="image")
	private String image;
}
