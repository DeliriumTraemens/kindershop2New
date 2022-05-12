package org.mykola.kindershop2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"id","name"})
@Table(name = "oc_category")
@SecondaryTable(name = "oc_category_description",pkJoinColumns = @PrimaryKeyJoinColumn(name = "category_id"))
public class CatCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="category_id")
	private Long id;
	
	@Column(name = "name",table="oc_category_description")
//	@JsonView(Views.IdName.class)
	@JoinColumn(name = "category_id" )
	private String name;
	
	@Column(name = "parent_id")
	private Long parentId;
	
	@JsonBackReference
	
	@ManyToMany(mappedBy = "categoryList")
	private Set<ProdCat> productList = new HashSet<>();
	
//	@ManyToOne()
//	@JsonIgnore
//	@JoinColumn(name =  "parent_id")
//	private CatCategory parent;
//	//
//	@OneToMany(mappedBy="parent",cascade = CascadeType.ALL, orphanRemoval = true)
//	@OrderBy("id ASC ")
//	@JsonView(Views.IdName.class)
////	@JsonIgnore
//	@JsonBackReference
//	private Set<CatCategory> children = new HashSet<>();
	
	
}
