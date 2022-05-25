package org.mykola.kindershop2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "aa_catsproduct")
public class CatOfProduct {
	@Id 
	@Column(name ="id")
	private Long id;
	
	@Column(name = "par_id")
	private Long parId;
	
	private String name;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "parent_id")
	private CatOfProduct parent;
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER )
	private List <CatOfProduct> children = new ArrayList<>();
	
	//Constructors
	
	
	public CatOfProduct() {
	}
	
	public CatOfProduct(Long id, Long parId, String name) {
		this.id = id;
		this.parId = parId;
		this.name = name;
	}
	
	public CatOfProduct(Long id, CatOfProduct parent) {
		this.id = id;
		this.parent = parent;
	}
	
	//GetSet
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getParId() {
		return parId;
	}
	
	public void setParId(Long parId) {
		this.parId = parId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public CatOfProduct getParent() {
		return parent;
	}
	
	public void setParent(CatOfProduct parent) {
		this.parent = parent;
	}
	
	public List<CatOfProduct> getChildren() {
		return children;
	}
	
	public void setChildren(List<CatOfProduct> children) {
		this.children = children;
	}
	
	//EqHc
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CatOfProduct that = (CatOfProduct) o;
		return getId().equals(that.getId()) &&
				       Objects.equals(getParId(), that.getParId()) &&
				       getName().equals(that.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getParId(), getName());
	}
	
	//ToString
	
	@Override
	public String toString() {
		return "CatOfProductWrite{" +
				       "id=" + id +
				       ", parId=" + parId +
				       ", name='" + name + '\'' +
				       ", children=" + children +
				       '}';
	}
}
