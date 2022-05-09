package org.mykola.kindershop2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mykola.kindershop2.dto.projections.manufacturer.ManIdNamePick;
import org.mykola.kindershop2.entity.Manufacturer;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ManIdNamePickPageDto {
	private List<ManIdNamePick> manufacturers;//Changed type from manufacturer
	private int currentPage;
	private int totalPages;
}
