package org.mykola.kindershop2.entity;

import javax.persistence.*;

@Entity
@Table(name = "oc_manufacturer")
public class Manufacturer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
