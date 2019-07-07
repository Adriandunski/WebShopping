package com.shop.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "features_of_type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Column(name = "type_name")
    private String typeName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "features_type",joinColumns = @JoinColumn(name = "it_type"), inverseJoinColumns = @JoinColumn(name = "features_id"))
    private Set<Features> features = new HashSet<>();
}
