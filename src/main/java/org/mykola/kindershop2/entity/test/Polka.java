package org.mykola.kindershop2.entity.test;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="ac_polka")
@Data
public class Polka {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long polka_id;
	private String name;
	
	@ManyToMany(mappedBy="polkiSet")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Tovar> tovarySet=new HashSet<>();
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (! (o instanceof Polka)) {
			return false;
		}
		Polka polka = (Polka) o;
		return getPolka_id().equals(polka.getPolka_id()) &&
				       getName().equals(polka.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getPolka_id(), getName());
	}
	
//	@Override
//	public String toString() {
//		return "\nPolka{" +
//				       "polka_id=" + polka_id +
//				       ", name='" + name + '\'' +
//				       ", \n\ttovarySet=" + tovarySet +
//				       '}';
//	}
}
