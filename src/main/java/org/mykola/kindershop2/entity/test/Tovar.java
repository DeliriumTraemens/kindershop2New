package org.mykola.kindershop2.entity.test;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="ac_tovar")
@Data
public class Tovar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tovar_id;
	private String name;
	
	@ManyToMany
	@JoinTable(name = "ac_tovar_polki_set",
				joinColumns=@JoinColumn(name = "tovary_set_tovar_id"),
				inverseJoinColumns = @JoinColumn(name="polki_set_polka_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Polka> polkiSet=new HashSet<Polka>();
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (! (o instanceof Tovar)) {
			return false;
		}
		Tovar tovar = (Tovar) o;
		return getTovar_id().equals(tovar.getTovar_id()) &&
				       getName().equals(tovar.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getTovar_id(), getName());
	}
	
//	@Override
//	public String toString() {
//		return "\nTovar{" +
//				       "tovar_id=" + tovar_id +
//				       ", name='" + name + '\'' +
////				       ", polkiSet=" + polkiSet +
//				       '}';
//	}
}
