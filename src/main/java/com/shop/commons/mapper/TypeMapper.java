package com.shop.commons.mapper;

import com.shop.models.DtoModels.TypeDto;
import com.shop.models.Type;
import com.shop.models.TypeEnum;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TypeMapper implements MapperDto<Type, TypeDto> {

    @Override
    public TypeDto map(Type from) {

        return TypeDto
                .builder()
                .typeName(from.getTypeName())
                .features(from.getFeatures()
                        .stream()
                        .map(f -> {

                            TypeEnum temp = null;

                            for (TypeEnum cur : TypeEnum.values()) {
                                if (cur.getTypeSpec().equals(f.getProductFeature())) {
                                    temp = cur;
                                }
                            }

                            return temp;

                        })
                        .filter( f -> {
                            if (f != null) {
                                return true;
                            } else {
                                return false;
                            }
                        })
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Type reverse(TypeDto to) {
        return null;
    }
}
