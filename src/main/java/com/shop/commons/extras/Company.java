package com.shop.commons.extras;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

    private List<String> typeOfProduct;
    private List<String> producers;
    private List<String> colors;
    private List<String> rams;
    private List<String> cores;
    private List<String> resolutions;
}
