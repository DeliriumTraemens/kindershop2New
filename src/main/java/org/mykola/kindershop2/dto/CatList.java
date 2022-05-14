package org.mykola.kindershop2.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.mykola.kindershop2.entity.tmpDto.CatTemp;

import java.util.Set;

@Getter
@Setter
//@AllArgsConstructor
@EqualsAndHashCode(of={"id","name"})
public class CatList {
	private Long id;
	private Long parentId;
	private String name;
	
	@JsonManagedReference
	private CatList parent;
	
	private CatList child;
	
	private CatTemp parent1;
	
//	@JsonBackReference
	private Set<CatList> children;
	
//	--------------
	
	
	public CatList(Long id, Long parentId, String name) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "\nCatList{" +
				       "id=" + id +
				       ", parentId=" + parentId +
				       ", name='" + name + '\'' +
				       ", parent=" + parent +
				       ", child=" + child +
				       ", children=" + children +
				       '}';
	}
}
