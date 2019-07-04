package com.shop.models.DtoModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private String productName;
    private String productImg;
    private Double productPrice;
    private String type;
    private String productDes;
    private String productProducer;
    private String productKolor;
    private String productProcesor;
    private String productProcesorMhz;
    private String product_procesor_Cores;
    private Integer product_memory_ram;
    private Double product_disk;
    private String product_graphic_card;
    private Double product_screen_cal;
    private String product_screen_resolution;
    private List<String> categories = new ArrayList<>();
}
