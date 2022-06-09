package org.mykola.kindershop2.entity.product;

import com.sun.xml.bind.v2.TODO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameCard;
import org.mykola.kindershop2.entity.product.image.ProdImage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "oc_product")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id", "name"})

@SecondaryTables({
		@SecondaryTable(name = "oc_product_description", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id")),
		@SecondaryTable(name = "oc_product_to_category", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))})
public class ProductShow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "name", table = "oc_product_description")
	private String name;
	
	@Column(name = "description", table = "oc_product_description")
	private String description;
	
	//Articul etc
	@Column(name = "model")
	private String model;
	
	@Column(name ="sku")
	private String article;
	
	@Column(name = "upc")
	private String upc;
	
	@Column(name ="isbn")
	private String isbn;
	
	//Quantity
	@Column(name ="quantity")
	private int quantity;
	
	//Price
	@Column(name ="price")
	private Float price;
	
	
	
	//Status
	@Column(name ="status")
	private Short status;
	
	//Rating
	@Column(name ="points")
	private Integer points;
	
	//Viewed
//	TODO implement counter of Views
	@Column(name = "viewed")
	private Integer viewed;
	
	//Size and Weight
	@Column(name ="weight")
	private Float weight;
	
	@Column(name ="length")
	private Float length;
	
	@Column(name ="width")
	private Float width;
	
	@Column(name ="height")
	private Float height;
	
	
	//Manufacturer
	
//	@OneToOne(fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manufacturer_id")
	private ManIdNameCard manufacturer;
	
	//Category (one from the List - current)
	@Column(name = "category_id", table = "oc_product_to_category")
	private Long catId;
	
	//Images Set
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Set<ProdImage> images=new HashSet<>();

}
