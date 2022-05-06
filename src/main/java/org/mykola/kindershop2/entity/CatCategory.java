package org.mykola.kindershop2.entity;

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
	
	@JsonIgnore
	@ManyToMany(mappedBy = "categoryList")
	private Set<ProdCat> productList = new HashSet<>();
}
