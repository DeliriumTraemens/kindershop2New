package org.mykola.kindershop2.entity.tmpDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = {"id", "name"})
@Table(name = "aa_catrange")
public class CatRangedDtoDel {
	@Id
	@Column(name = "id")
	private Long id;
	
	private String name;
	
	private Long parId;
	
	@ManyToOne()
//	@JsonIgnore
	@JsonBackReference
	@JoinColumn(name = "parent_id")
	private CatRangedDtoDel parent;
	
	@Override
	public String toString() {
		return "CatRangedDto{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       ", parId=" + parId +
				       '}';
	}
}
