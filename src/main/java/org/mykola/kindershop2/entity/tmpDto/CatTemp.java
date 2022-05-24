package org.mykola.kindershop2.entity.tmpDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "aa_cattemp")
//@Data
//@EqualsAndHashCode(of = {"id", "name"})
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class CatTemp{

//	А если сделать липовый Айди -
//	У сущности поставить автогенерацию, а импортируемый айди
//	Поместить в отдельное поле, и мапить чилдренов через него?
//	Или ЛомБок вырубить, и поставить @ID на геттерАйди????
	
	
	@Id
//	@Column(name = "cattemp_id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(name = "par_id")
	private Long parId;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "parent_id")
	private CatTemp parent;

	//,cascade = CascadeType.ALL, orphanRemoval = true
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER )
//	@OrderBy("id ASC ")
	private List<CatTemp> childrenlist = new ArrayList<>();
	
	
//	Constructor
	
	
	public CatTemp() {
	}
	
	public CatTemp(Long id,  String name, Long parId) {
		this.id = id;
		this.name = name;
		this.parId = parId;
	}
	//	GetSetters
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getParId() {
		return parId;
	}
	
	public void setParId(Long parId) {
		this.parId = parId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public CatTemp getParent() {
		return parent;
	}
	
	public void setParent(CatTemp parent) {
		this.parent = parent;
	}
	
	public List<CatTemp> getChildrenlist() {
		return childrenlist;
	}
	
	public void setChildrenlist(List<CatTemp> childrenlist) {
		this.childrenlist = childrenlist;
	}
	
	public void addChild(CatTemp child){
		this.childrenlist.add(child);
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CatTemp catTemp = (CatTemp) o;
		return Objects.equals(getId(), catTemp.getId()) &&
				       Objects.equals(getName(), catTemp.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
	
	@Override
	public String toString() {
		return "CatTemp{" +
				       "id=" + id +
				       ", parId=" + parId +
				       ", name='" + name + '\'' +
				       ", childrenList=" + childrenlist +
				       '}';
	}
}
