package com.shop.services;

import com.shop.commons.mapper.TypeMapper;
import com.shop.models.DtoModels.TypeDto;
import com.shop.models.SingleFeature;
import com.shop.models.Type;
import com.shop.repositories.FeaturesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeaturesService {

    private FeaturesRepository featuresRepository;
    private TypeMapper typeMapper;

    public FeaturesService(FeaturesRepository featuresRepository, TypeMapper typeMapper) {
        this.featuresRepository = featuresRepository;
        this.typeMapper = typeMapper;
    }

    public List<Type> getFeaturesAll() {
        return featuresRepository.findAll();
    }

    public Type getOneFeatureByName(String type) {
        return featuresRepository.getFeaturesOfTypeByName(type);
    }

    public List<TypeDto> getFeaturesAllDto() {

        return getFeaturesAll().stream().map(typeMapper::map).collect(Collectors.toList());
    }

    public TypeDto getOneFeatureByNameDto(String type) {
        return typeMapper.map(getOneFeatureByName(type));
    }



}
