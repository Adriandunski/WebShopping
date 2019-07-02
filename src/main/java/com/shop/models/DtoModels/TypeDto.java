package com.shop.models.DtoModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeDto {

    private String typeName;
    private List<String> items;
    private List<String> specs = new ArrayList<>();
}
