package org.mykola.kindershop2.dto.projections.manufacturer;

import lombok.Data;

@Data
public class ManIdNameFilter {
    private Long id;
    private String name;
    public ManIdNameFilter(Long id, String name){
        this.id=id;
        this.name=name;
    }
}
