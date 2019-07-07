package com.shop.commons.extras;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

    private List<String> productType;
    private List<String> productProducer;
    private List<String> productKolor;
    private List<String> product_memory_ram;
    private List<String> product_procesor_cores;
    private List<String> product_screen_resolution;
}
