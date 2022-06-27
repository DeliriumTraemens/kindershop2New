package org.mykola.kindershop2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameCard;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "oc_product")
@ToString(of = {"id", "name", "creationDate","manufacturer"})
@EqualsAndHashCode(of = {"id","name"})
//@JsonIdentityInfo(
//		generator= ObjectIdGenerators.PropertyGenerator.class,
//		property="id")
@SecondaryTables({
		@SecondaryTable(name = "oc_product_description", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id")),
		@SecondaryTable(name = "oc_product_to_category", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	
	@Column(name = "price")
	private Float price;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "name", table = "oc_product_description")
	private String name;
	
//	@JsonIgnore
	@Column(name = "description", table = "oc_product_description")
	private String description;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="date_added")
	private LocalDateTime creationDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="date_modified")
	private LocalDateTime modificationDate;
	
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "manufacturer_id")
//	private Manufacturer manufacturer;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manufacturer_id")
	private ManIdNameCard manufacturer;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Category category;
//	@ManyToMany(cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@JoinTable(name = "oc_product_to_category",
//				joinColumns=@JoinColumn(name = "product_id"),
//				inverseJoinColumns=@JoinColumn(name = "category_id"))
//	private Set<Category> categoryList=new HashSet<>();
	
	@Column(name = "category_id", table = "oc_product_to_category")
	private Long catId;
	
}
