package org.mykola.kindershop2.entity.tmpDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aa_cattemp")
@EqualsAndHashCode(of = {"id", "name"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatTemp{
	
	@Id
	@Column(name = "cattemp_id")
	private Long id;

//	@Column(name = "parent_id")
//	private Long parentId;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne()
	@JsonIgnore
//	@JoinColumn(name = "parent_id")
	private CatTemp parent;
	
	@OneToMany(mappedBy = "parent")
//	@OrderBy("id ASC ")
	private Set<CatTemp> children = new HashSet<>();
	
	public CatTemp(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
//	@Override
//	public String toString() {
//		return "CatTemp{" +
//				       "id=" + id +
//				       ", name='" + name + '\'' +
//				       ", children=" + children +
//				       '}';
//	}
}
