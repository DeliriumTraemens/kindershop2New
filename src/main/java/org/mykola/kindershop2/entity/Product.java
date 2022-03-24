package org.mykola.kindershop2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "oc_product")
@ToString(of = {"id", "name", "creationDate"})
@EqualsAndHashCode(of = {"id"})
@JsonIdentityInfo(
		generator= ObjectIdGenerators.PropertyGenerator.class,
		property="id")
@SecondaryTables({
		@SecondaryTable(name = "oc_product_description", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id")),
		@SecondaryTable(name = "oc_product_to_category", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "name", table = "oc_product_description")
	private String name;
	
	@Column(name = "description", table = "oc_product_description")
	private String description;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="date_added")
	private LocalDateTime creationDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="date_modified")
	private LocalDateTime modificationDate;
	
//	@OneToOne
//	@JoinColumn(name = "manufacturer_id")
//	private Manufacturer manufacturer;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	
	@Column(name = "category_id", table = "oc_product_to_category")
	private Long catId;
	
}
