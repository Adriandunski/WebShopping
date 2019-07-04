package com.shop.commons.mapper;

import com.shop.models.Category;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements MapperDto<Product, ProductDto>{

    @Override
    public ProductDto map(Product from) {

        List<String> categories = from
                .getCategories()
                .stream()
                .map(categoriesToStringList.INSTANCE)
                .collect(Collectors.toList());

        return ProductDto
                .builder()
                .productName(from.getProductName())
                .productImg(from.getProductImg())
                .productPrice(from.getProductPrice())
                .productProducer(from.getProductProducer())
                .productDes(from.getProductDes())
                .productKolor(from.getProductKolor())
                .type(from.getProductType())
                .productProcesor(from.getProductProcesor())
                .productProcesorMhz(from.getProductProcesorMhz())
                .product_procesor_Cores(from.getProduct_procesor_Cores())
                .product_memory_ram(from.getProduct_memory_ram())
                .product_disk(from.getProduct_disk())
                .product_graphic_card(from.getProduct_graphic_card())
                .product_screen_cal(from.getProduct_screen_cal())
                .product_screen_resolution(from.getProduct_screen_resolution())
                .categories(categories)
                .build();
    }

    @Override
    public Product reverse(ProductDto to) {
        return null;
    }

    private enum categoriesToStringList implements Function<Category, String> {
        INSTANCE;

        @Override
        public String apply(Category category) {
            return category.getCategoryName();
        }
    }
}
