package org.mykola.kindershop2.entity.tmpDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aa_cattmp")
@EqualsAndHashCode(of = {"id", "name"})
@Data
@NoArgsConstructor
public class CatTemp implements Serializable {
	
	@Id
	@Column(name = "cattemp_id")
	private Long id;
	
//	@Column(name = "parent_id")
//	private Long parentId;
	
	@Column(name ="name")
	private String name;
	
	@ManyToOne()
//	@JsonIgnore
	@JoinColumn(name = "parent_id")
	private CatTemp parent;
	
	@OneToMany(	mappedBy="parent",cascade = CascadeType.ALL, orphanRemoval = true,
			fetch = FetchType.EAGER)
	@OrderBy("id ASC ")
	private Set<CatTemp> children = new HashSet<>();
	
	public CatTemp(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "\nCatTemp{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       ", \n\tchildren=" + children +
				       '}';
	}
}
