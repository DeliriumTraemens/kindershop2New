package org.mykola.kindershop2.dto.projections;

import org.springframework.beans.factory.annotation.Value;

public interface ManIdName {
	Long getId();
//	@Value("#{target.name +' '+target.id}")
	String getName();
}
