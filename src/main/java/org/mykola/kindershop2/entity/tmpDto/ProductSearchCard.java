package org.mykola.kindershop2.entity.tmpDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNameEntity;
import org.mykola.kindershop2.entity.CatCategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "oc_product")
@ToString(of = {"id", "name", "categories" })
//@EqualsAndHashCode(of = {"id", "name"})
@SecondaryTables({
		@SecondaryTable(name = "oc_product_description",
				pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id")),
//		@SecondaryTable(name = "oc_product_to_category",
//				pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))
})
public class ProductSearchCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(table = "oc_product",name = "product_id")
	private Long id;
	
	@Column(name = "name", table = "oc_product_description")
	private String name;
	
//	@Column(name = "category_id", table = "oc_product_to_category")
//	private Long catId;
	
	@Column(name = "image")
	private String image;
	
	//	@JsonIgnore
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manufacturer_id")
	private ManIdNameEntity manufacturer;
//
	@ManyToMany()
//	@JsonIgnore
//	@JsonManagedReference
	@LazyCollection(LazyCollectionOption.TRUE)
	@JoinTable(name = "oc_product_to_category",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<CatRanged> categories = new ArrayList<>();//change target cat entity
	

}
