package com.shop.controllers;

import com.shop.models.DtoModels.TypeDto;
import com.shop.models.Type;
import com.shop.services.FeaturesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeaturesController {

    private FeaturesService featuresService;

    public FeaturesController(FeaturesService featuresService) {
        this.featuresService = featuresService;
    }

    @GetMapping("/features")
    public List<Type> getFeaturesOfType() {
        return featuresService.getFeaturesAll();
    }

    @GetMapping("/featuresDto")
    public List<TypeDto> getFeaturesOfTypeDto() {

        return featuresService.getFeaturesAllDto();
    }

    @GetMapping("/featurDto")
    public TypeDto getOneFeaturOfTypeDto(@RequestParam(value = "type") String type) {

        return featuresService.getOneFeatureByNameDto(type);
    }
}
