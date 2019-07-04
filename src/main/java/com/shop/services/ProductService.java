package com.shop.services;

import com.shop.commons.extras.CheckBoxProduct;
import com.shop.commons.mapper.ProductMapper;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.Product;
import com.shop.repositories.ProductRepositories;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepositories productRepositories;
    private ProductMapper productMapper;

    public ProductService(ProductRepositories productRepositories, ProductMapper productMapper) {
        this.productRepositories = productRepositories;
        this.productMapper = productMapper;
    }

    public List<Product> getAllProducts() {
        return productRepositories.findAll();
    }

    public List<Product> getProductsByCategory(String type) {
        return productRepositories.findProductByProductType(type);
    }

    /**  DTO */

    public List<ProductDto> getProductsDto() {
        return productRepositories
                .findAll()
                .stream().map(productMapper::map)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductsByCategoryDto(String type) {
        return getProductsByCategory(type)
                .stream()
                .map(productMapper::map)
                .collect(Collectors.toList());
    }

    public List<CheckBoxProduct> getProducersByProduct(String typeOfProduct) {

        return getProductsByCategoryDto(typeOfProduct)
                .stream()
                .distinct()
                .map( productDto -> {
                    return CheckBoxProduct
                            .builder()
                            .nameCheckBox(productDto.getProductProducer())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<ProductDto> getFiltredProductDto(List<String> filtr, String productType) {

        List<String> filtrs = new ArrayList<>(filtr);
        System.out.println(Arrays.toString(filtrs.toArray()));

        return getProductsByCategoryDto(productType)
                .stream()
                .filter( laptop -> filtrs.contains(laptop.getProductProducer()))
                .collect(Collectors.toList());
    }
}
