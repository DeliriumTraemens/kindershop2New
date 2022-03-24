package org.mykola.kindershop2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "oc_category")
@SecondaryTable(name = "oc_category_description",pkJoinColumns = @PrimaryKeyJoinColumn(name = "category_id"))
public class Catalog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="category_id")
	private Long id;
	
	@Column(name = "name",table="oc_category_description")
	@JsonView(Views.IdName.class)
	@JoinColumn(name = "category_id" )
	private String name;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name =  "parent_id")
	private Category parent;
	
	@OneToMany(mappedBy="parent")
	private Set<Catalog> children= new HashSet<Catalog>();
	
	
}
