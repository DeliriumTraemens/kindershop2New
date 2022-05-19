package org.mykola.kindershop2.entity.tmpDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aa_cattemp")
@Data
@EqualsAndHashCode(of = {"id", "name"})
@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class CatTemp{
	
	@Id
//	@Column(name = "cattemp_id")
//	@GeneratedValue
	private Long id;

	
	@Column(name = "par_id")
	private Long parId;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "parent_id")
	private CatTemp parent;

	//,cascade = CascadeType.ALL, orphanRemoval = true
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	@OrderBy("id ASC ")
	private List<CatTemp> children = new ArrayList<>();
	
	
	@Override
	public String toString() {
		return "CatTemp{" +
				       "id=" + id +
				       ", parId=" + parId +
				       ", name='" + name + '\'' +
				       ", children=" + children +
				       '}';
	}
}
