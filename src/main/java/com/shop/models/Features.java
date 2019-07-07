package com.shop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "features")
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long featuresId;

    @Column(name = "product_feature")
    private String productFeature;

    @Column(name = "order_feature")
    private Long order;

    @JsonIgnore
    @ManyToMany(mappedBy = "features")
    Set<Type> types = new HashSet<>();
}
