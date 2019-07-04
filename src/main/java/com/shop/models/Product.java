package com.shop.models;

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
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "product_des")
    private String productDes;

    @Column(name = "product_producer")
    private String productProducer;

    @Column(name = "product_kolor")
    private String productKolor;

    @Column(name = "product_procesor")
    private String productProcesor;

    @Column(name = "product_procesor_mhz")
    private String productProcesorMhz;

    @Column(name = "product_procesor_cores")
    private String product_procesor_Cores;

    @Column(name = "product_memory_ram")
    private Integer product_memory_ram;

    @Column(name = "product_disk")
    private Double product_disk;

    @Column(name = "product_graphic_card")
    private String product_graphic_card;

    @Column(name = "product_screen_cal")
    private Double product_screen_cal;

    @Column(name = "product_screen_resolution")
    private String product_screen_resolution;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_category"))
    private Set<Category> categories = new HashSet<>();
}
