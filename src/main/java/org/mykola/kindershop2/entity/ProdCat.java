package org.mykola.kindershop2.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "oc_product")
@ToString(of = {"id", "name"})
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
	
	@ManyToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "oc_product_to_category",
			joinColumns=@JoinColumn(name = "product_id"),
			inverseJoinColumns=@JoinColumn(name = "category_id"))
	private Set<CatCategory> categoryList=new HashSet<>();
	
	
}