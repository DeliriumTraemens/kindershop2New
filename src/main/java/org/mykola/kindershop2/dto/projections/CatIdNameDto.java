package org.mykola.kindershop2.dto.projections;

import java.util.Objects;

public class CatIdNameDto {
	private Long id;
	private String name;
	
	public CatIdNameDto() {
	}
	
	public CatIdNameDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
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
	
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (! (o instanceof CatIdNameDto)) {
			return false;
		}
		CatIdNameDto that = (CatIdNameDto) o;
		return getId().equals(that.getId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
	
	@Override
	public String toString() {
		return "\nCatIdNameDto{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       '}';
	}
}
