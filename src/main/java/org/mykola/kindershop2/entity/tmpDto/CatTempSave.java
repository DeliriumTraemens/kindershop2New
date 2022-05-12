package org.mykola.kindershop2.entity.tmpDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aa_cattmpsave")
@Data
@EqualsAndHashCode(of = {"id", "name"})
@ToString(of = {"id","name"})
//@NoArgsConstructor
//@AllArgsConstructor
public class CatTempSave {
	@Id
	@Column(name ="cattemp_id")
	private Long id;
	
//	@Column(name ="parent_id")
//	private Long parentId;
	
	@Column(name ="name")
	private String name;
	
	public CatTempSave() {
	}
	
	public CatTempSave(Long id,  String name) {
		this.id = id;
		this.name = name;
	}
}
