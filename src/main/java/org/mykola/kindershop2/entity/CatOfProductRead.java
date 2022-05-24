package org.mykola.kindershop2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aa_catsproduct")
public class CatOfProductRead {
	//id (, insertable=true,nullable=true)
	@Id
	@Column(name ="id", insertable=true,nullable=true)
	private Long id;
	
	@Column(name = "par_id")
	private Long parId;
	
	private String name;
}
