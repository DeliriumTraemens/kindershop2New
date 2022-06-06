package org.mykola.kindershop2.dto;

import java.util.Objects;
import java.util.Set;

public class ManCat {
	
	private Long id;
	private String name;
	private Long parentId;
	private Set<ManCat> children;
	
	//Cons
	public ManCat(Long id, String name, Long parentId) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}
	//GetSet
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Set<ManCat> getChildren() {
		return children;
	}
	
	public void setChildren(Set<ManCat> children) {
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
		ManCat manCat = (ManCat) o;
		return getId().equals(manCat.getId()) &&
				       getName().equals(manCat.getName()) &&
				       getParentId().equals(manCat.getParentId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getParentId());
	}
	
	//toString
	
	@Override
	public String toString() {
		return "\nManCat{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       ", parentId=" + parentId +
				       ", children=" + children +
				       '}';
	}
}
