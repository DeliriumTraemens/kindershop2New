package org.mykola.kindershop2.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id"})
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
@Table(name = "oc_manufacturer")

@SecondaryTables({
		@SecondaryTable(name = "oc_manufacturer_description",
				pkJoinColumns = @PrimaryKeyJoinColumn(name = "manufacturer_id"))
})

public class Manufacturer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "manufacturer_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image")
	private String image;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "manufacturer", fetch = FetchType.EAGER)
//	@JsonIgnore
//	@JoinColumn(name = "manufacturer_id")
	private Set<ProdCat> prodCatList=new HashSet<>();
	
}
