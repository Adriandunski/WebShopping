package com.shop.commons.extras;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

    private List<String> producers;
    private List<String> colors;
    private List<String> rams;


    public boolean isSomethingInFilter() {

        if (producers != null || colors != null || rams != null) {
            return true;
        } else {
            return false;
        }
    }
}
