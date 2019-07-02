package com.shop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "types")
public class Type {

    @Id
    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "type_name")
    private String typeName;

    @JsonIgnore
    @OneToMany(mappedBy = "productType")
    List<Product> items;

    @ManyToMany(cascade = {
            CascadeType.ALL})
    @JoinTable(name = "type_spec", joinColumns = @JoinColumn(name = "type_id"), inverseJoinColumns = @JoinColumn(name = "spec_id"))
    private Set<Spec> specs = new HashSet<>();
}
