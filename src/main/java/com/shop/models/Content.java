package com.shop.models;

import com.shop.models.DtoModels.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Content {

    private String contentName;
    private List<ProductDto> products;
}
