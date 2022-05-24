package org.mykola.kindershop2.entity.tmpDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "aa_cattemp_sort")

public class CatTempWrite {
	@Id
	@Column(name = "cattemp_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "parent_id",insertable = true, nullable=true)
	private Long parentId;
	
	// Cons
	
	public CatTempWrite() {
	}
	
	public CatTempWrite(Long id, String name, Long parentId) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}
	
	//GettSett
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//EQ HC
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CatTempWrite that = (CatTempWrite) o;
		return getId().equals(that.getId()) &&
				       getName().equals(that.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
}
