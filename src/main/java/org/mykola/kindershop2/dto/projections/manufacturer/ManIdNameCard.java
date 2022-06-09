package org.mykola.kindershop2.dto.projections.manufacturer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(of = {"id", "name", "prodCatList"})
@EqualsAndHashCode(of = {"id", "name"})
@Table(name = "oc_manufacturer")
public class ManIdNameCard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "manufacturer_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image")
	private String image;
}
