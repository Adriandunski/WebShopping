package com.shop.commons.mapper;

import com.shop.models.DtoModels.TypeDto;
import com.shop.models.Spec;
import com.shop.models.Type;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TypeMapper implements Mapper<Type, TypeDto> {

    @Override
    public TypeDto map(Type from) {

        List<String> specs = from.getSpecs()
                .stream()
                .map(SpecToStringList.INSTANCE)
                .collect(Collectors.toList());

        return TypeDto
                .builder()
                .specs(specs)
                .typeName(from.getTypeName())
                .build();
    }

    @Override
    public Type reverseMap(TypeDto to) {
        return null;
    }

    private enum SpecToStringList implements Function<Spec, String> {
        INSTANCE;

        @Override
        public String apply(Spec spec) {
            return spec.getSpecName();
        }
    }
}
