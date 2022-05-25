package org.mykola.kindershop2.entity;

import javax.persistence.*;

@Entity
@Table(name = "aa_cattmp")
public class AaCattmp {
    @Id
    @Column(name = "cattemp_id")
    private Long cattempId;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    public Long getCattempId() {
        return this.cattempId;
    }

    public void setCattempId(Long cattempId) {
        this.cattempId = cattempId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
