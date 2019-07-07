package com.shop.models.DtoModels;

import com.shop.models.TypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class TypeDto {

    private String typeName;
    private List<TypeEnum> features;
}
