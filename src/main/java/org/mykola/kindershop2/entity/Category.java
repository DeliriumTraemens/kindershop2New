package org.mykola.kindershop2.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@ToString(of = {"id", "name", "creationDate"})
@EqualsAndHashCode(of = {"id", "name",})

@JsonIdentityInfo(
		generator= ObjectIdGenerators.PropertyGenerator.class,
		property="id")
@Table(name = "oc_category")
@SecondaryTable(name = "oc_category_description",pkJoinColumns = @PrimaryKeyJoinColumn(name = "category_id"))
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	@JsonView(Views.IdName.class)
	private Long id;
	
//	@Column(name = "parent_id")
//	private Long parentId;
	
	@JsonIgnore
	@Column(name = "image")
	private String image;
	
	@JsonIgnore
	@Column(name = "status")
	private short status;
	
	@Column(name = "name",table="oc_category_description")
	@JsonView(Views.IdName.class)
	private String name;
	
	@Column(name = "description",table="oc_category_description")
	private String description;
	
	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="date_added")
	private LocalDateTime creationDate;
	
	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="date_modified")
	private LocalDateTime modificationDate;
	
//	@JsonIgnore
//	@Column(name ="top")
//	short top = 0;
//
//	@JsonIgnore
//	@Column(name ="column")
//	int column = 0;
	
	@ManyToOne()
//	@JsonIgnore
	@JoinColumn(name =  "parent_id")
	private Category parent;
//
	@OneToMany(mappedBy="parent",cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id ASC ")
	@JsonView(Views.IdName.class)
//	@JsonIgnore
	private Set<Category> children = new HashSet<>();
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@JoinTable(name = "oc_product_to_category",
//			joinColumns=@JoinColumn(name = "category_id"),
//			inverseJoinColumns=@JoinColumn(name = "product_id"))
//	private Set<Product> productList = new HashSet<>();
	
//	@ManyToMany(mappedBy = "categoryList")
//	private Set<Product> productList = new HashSet<>();
	
	//	@ManyToMany(mappedBy = "category")
//	private Set<Product> products = new HashSet<>();
	
	
	
	
	
}
