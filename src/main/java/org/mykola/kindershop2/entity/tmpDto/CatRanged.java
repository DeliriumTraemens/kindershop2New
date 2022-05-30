package org.mykola.kindershop2.entity.tmpDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = {"id", "name"})
@Table(name = "oc_category")
@SecondaryTable(name = "oc_category_description", pkJoinColumns = @PrimaryKeyJoinColumn(name = "category_id"))
public class CatRanged {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;
	
	@Column(name = "name", table = "oc_category_description")
//	@JsonView(Views.IdName.class)
	@JoinColumn(name = "category_id")
	private String name;
	
	
	
	@ManyToOne()
//	@JsonIgnore
	@JsonBackReference
	@JoinColumn(name = "parent_id")
	private CatRanged parent;
	
//	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
//	private List<CatRanged> children = new ArrayList<>();
	
	
	@Override
	public String toString() {
		return "\nCatRanged{" +
				       "\nid=" + id +
				       ", name='" + name + '\'' +
				       ", parent_name=" + parent.getName() +
				       '}';
	}
}
