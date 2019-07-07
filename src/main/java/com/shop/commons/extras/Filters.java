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

        if (filters.getProducers() != null) {
            List<CheckBoxProduct> producerChecks = filters.getProducers().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            for (int i = 0; i < features.size(); i++) {

                if (features.get(i).getTypeSpec().equals("producers")) {

                    for(int j = 0; j < features.get(i).getSpecsProduct().size(); j++) {

                        if(producerChecks.contains(features.get(i).getSpecsProduct().get(j))) {
                            features.get(i).getSpecsProduct().get(j).setIsCheck(true);
                        }
                    }
                }
            }
        }

        if (filters.getColors() != null) {
            List<CheckBoxProduct> producerChecks = filters.getColors().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            for (int i = 0; i < features.size(); i++) {

                if (features.get(i).getTypeSpec().equals("colors")) {

                    for(int j = 0; j < features.get(i).getSpecsProduct().size(); j++) {

                        if(producerChecks.contains(features.get(i).getSpecsProduct().get(j))) {
                            features.get(i).getSpecsProduct().get(j).setIsCheck(true);
                        }
                    }
                }
            }
        }

        if (filters.getRams() != null) {
            List<CheckBoxProduct> producerChecks = filters.getRams().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            for (int i = 0; i < features.size(); i++) {

                if (features.get(i).getTypeSpec().equals("rams")) {

                    for(int j = 0; j < features.get(i).getSpecsProduct().size(); j++) {

                        if(producerChecks.contains(features.get(i).getSpecsProduct().get(j))) {
                            features.get(i).getSpecsProduct().get(j).setIsCheck(true);
                        }
                    }
                }
            }
        }

        if (filters.getCores() != null) {
            List<CheckBoxProduct> producerChecks = filters.getCores().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            for (int i = 0; i < features.size(); i++) {

                if (features.get(i).getTypeSpec().equals("cores")) {

                    for(int j = 0; j < features.get(i).getSpecsProduct().size(); j++) {

                        if(producerChecks.contains(features.get(i).getSpecsProduct().get(j))) {
                            features.get(i).getSpecsProduct().get(j).setIsCheck(true);
                        }
                    }
                }
            }
        }

        if (filters.getResolutions() != null) {
            List<CheckBoxProduct> producerChecks = filters.getResolutions().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            for (int i = 0; i < features.size(); i++) {

                if (features.get(i).getTypeSpec().equals("resolutions")) {

                    for(int j = 0; j < features.get(i).getSpecsProduct().size(); j++) {

                        if(producerChecks.contains(features.get(i).getSpecsProduct().get(j))) {
                            features.get(i).getSpecsProduct().get(j).setIsCheck(true);
                        }
                    }
                }
            }
        }

        return features;
    }
}
