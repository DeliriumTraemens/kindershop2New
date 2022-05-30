package org.mykola.kindershop2.entity.tmpDto;

import java.util.Objects;


public class CatIdNameDto2 {
	private Long id;
	private Long parId;
	private String name;
	
	//Cons
	
	public CatIdNameDto2() {
	}
	
	public CatIdNameDto2(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CatIdNameDto2(Long id, Long parId, String name) {
		this.id = id;
		this.parId = parId;
		this.name = name;
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
	
	//EqHc
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CatIdNameDto2 that = (CatIdNameDto2) o;
		return getId().equals(that.getId()) &&
				       getName().equals(that.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
	
	//toString
	
	@Override
	public String toString() {
		return "\nCatIdNameDto{" +
				       "id=" + id +
				       ", parId=" + parId +
				       ", name='" + name + '\'' +
				       '}';
	}
}
