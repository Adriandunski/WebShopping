package com.shop.commons.extras;

import com.shop.models.SingleFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Filters {

    private List<SingleFeature> features;
    private Company filters;

    public List<SingleFeature> getFiltredFeatures() {

        if (filters.getProductProducer() != null) {
            List<CheckBoxProduct> productsChecks = filters.getProductProducer().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            loop("productProducer", productsChecks);
        }

        if (filters.getProductKolor() != null) {
            List<CheckBoxProduct> productsChecks = filters.getProductKolor().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            loop("productKolor", productsChecks);
        }

        if (filters.getProduct_memory_ram() != null) {
            List<CheckBoxProduct> productsChecks = filters.getProduct_memory_ram().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            loop("product_memory_ram", productsChecks);
        }

        if (filters.getProduct_procesor_cores() != null) {
            List<CheckBoxProduct> productsChecks = filters.getProduct_procesor_cores().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            loop("product_procesor_cores", productsChecks);
        }

        if (filters.getProduct_screen_resolution() != null) {
            List<CheckBoxProduct> productsChecks = filters.getProduct_screen_resolution().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            loop("product_screen_resolution", productsChecks);
        }

        if (filters.getProductType() != null) {
            List<CheckBoxProduct> productsChecks = filters.getProductType().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            loop("productType", productsChecks);
        }

        return features;
    }

    private void loop (String type, List<CheckBoxProduct> productsChecks) {

        for (int i = 0; i < features.size(); i++) {

            if (features.get(i).getTypeSpec().equals(type)) {

                for(int j = 0; j < features.get(i).getSpecsProduct().size(); j++) {

                    if(productsChecks.contains(features.get(i).getSpecsProduct().get(j))) {
                        features.get(i).getSpecsProduct().get(j).setIsCheck(true);
                    }
                }
            }
        }
    }
}
