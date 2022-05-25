package org.mykola.kindershop2.entity.tmpDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = {"id", "name"})
@Table(name = "aa_catrange")
public class CatRangedDto {
	@Id
	@Column(name = "id")
	private Long id;
	
	private String name;
	
	private Long parId;
	
	@ManyToOne()
//	@JsonIgnore
	@JsonBackReference
	@JoinColumn(name = "parent_id")
	private CatRangedDto parent;
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private List<CatRangedDto> children = new ArrayList<>();
	
	@Override
	public String toString() {
		return "CatRangedDto{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       ", parId=" + parId +
				       ", children=" + children +
				       '}';
	}
}
