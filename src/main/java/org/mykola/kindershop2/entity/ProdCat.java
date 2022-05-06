package org.mykola.kindershop2.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "oc_product")
@ToString(of = {"id", "name", "catId", "categoryList"})
@EqualsAndHashCode(of = {"id"})
//@JsonIdentityInfo(
//		generator= ObjectIdGenerators.PropertyGenerator.class,
//		property="id")
@SecondaryTables({
		@SecondaryTable(name = "oc_product_description", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id")),
		@SecondaryTable(name = "oc_product_to_category", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))})
public class ProdCat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	
	@Column(name = "name", table = "oc_product_description")
	private String name;
	
	
	@Column(name = "category_id", table = "oc_product_to_category")
	private Long catId;
	
	@Column(name = "image")
	private String image;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;
	
	@ManyToMany(cascade = CascadeType.ALL)
//	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "oc_product_to_category",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
//	private Set<CatCategory> categoryList;// = new HashSet<>()
	private List<CatCategory> categoryList = new ArrayList<>();
	
	@Column(name ="trash", nullable=true)
	@JsonIgnore
	private Boolean trash=true;
}
