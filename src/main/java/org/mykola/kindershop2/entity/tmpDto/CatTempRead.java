package org.mykola.kindershop2.entity.tmpDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "aa_cattemp_sort")
@Table(name = "aa_cattemp")

public class CatTempRead {
	
	@Id
	@Column(name = "id", insertable=true,nullable=true)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "parent_id" )
	private CatTempRead parent;
	
	//,cascade = CascadeType.ALL, orphanRemoval = true
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER )
//	@OrderBy("id ASC ")
	private List<CatTempRead> childrenlist = new ArrayList<>();
	
	//An Empty Constructor
	public CatTempRead() {
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
	
	public CatTempRead getParent() {
		return parent;
	}
	
	public void setParent(CatTempRead parent) {
		this.parent = parent;
	}
	
	public List<CatTempRead> getChildrenlist() {
		return childrenlist;
	}
	
	public void setChildrenlist(List<CatTempRead> childrenlist) {
		this.childrenlist = childrenlist;
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
		CatTempRead that = (CatTempRead) o;
		return getId().equals(that.getId()) &&
				       getName().equals(that.getName()) &&
				       Objects.equals(getParent(), that.getParent());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getParent());
	}
	
	//ToStr
	
	@Override
	public String toString() {
		return "CatTempRead{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       ", childrenlist=" + childrenlist +
				       '}';
	}
}//EOC
