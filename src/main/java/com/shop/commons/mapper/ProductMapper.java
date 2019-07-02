package com.shop.commons.mapper;

import com.shop.models.DtoModels.ItemDto;
import com.shop.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ItemDto>{

    @Override
    public ItemDto map(Product from) {

        String type = from.getProductType().getTypeName();

        return ItemDto
                .builder()
                .itemName(from.getProductName())
                .type(type)
                .build();
    }

    @Override
    public Product reverseMap(ItemDto to) {
        return null;
    }
}
