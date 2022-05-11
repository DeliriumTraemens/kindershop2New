package org.mykola.kindershop2.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of={"id","name"})
public class CatList {
	private Long id;
	private Long parentId;
	private String name;
	
	@JsonManagedReference
	private CatList parent;
	@JsonBackReference
	private Set<CatList> children;
	
}
