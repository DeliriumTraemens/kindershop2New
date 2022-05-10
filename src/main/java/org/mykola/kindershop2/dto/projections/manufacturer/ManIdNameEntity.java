package org.mykola.kindershop2.dto.projections.manufacturer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.mykola.kindershop2.entity.ProdCat;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id", "name"})
//@JsonIdentityInfo(
//		generator = ObjectIdGenerators.PropertyGenerator.class,
//		property = "id")
@Table(name = "oc_manufacturer")


public class ManIdNameEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "manufacturer_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name ="image")
	private String image;
	
//	@JsonBackReference
//	@OneToMany(mappedBy="manufacturer")
//	private Set<ProdCat>productSet;
}
