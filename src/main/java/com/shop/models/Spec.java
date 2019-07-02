package com.shop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "specs")
public class Spec {

    @Id
    @Column(name = "spec_id")
    private Long specId;

    @Column(name = "spec_name")
    private String specName;

    @JsonIgnore
    @ManyToMany(mappedBy = "specs")
    private Set<Type> items = new HashSet<>();
}
