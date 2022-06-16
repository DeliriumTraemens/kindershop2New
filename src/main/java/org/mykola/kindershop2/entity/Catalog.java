package org.mykola.kindershop2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "oc_category")
@EqualsAndHashCode(of = {"id", "name"})
@SecondaryTable(name = "oc_category_description",pkJoinColumns = @PrimaryKeyJoinColumn(name = "category_id"))
public class Catalog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="category_id")
	private Long id;
	
	@Column(name = "name",table="oc_category_description")
	@JsonView(Views.IdName.class)
	@JoinColumn(name = "category_id" )
	private String name;
	
	@JsonIgnore
	@Column(name = "status")
	private short status;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name =  "parent_id")
	private Catalog parent;
	
	@OneToMany(mappedBy="parent",cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id ASC ")
	private Set<Catalog> children= new HashSet<Catalog>();
	
	
	@Override
	public String toString() {
		return "Catalog{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       ", children=" + children +
				       '}';
	}
}
