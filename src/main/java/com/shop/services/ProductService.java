package com.shop.services;

import com.shop.commons.extras.CheckBoxProduct;
import com.shop.commons.extras.Company;
import com.shop.commons.mapper.ProductMapper;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.Product;
import com.shop.models.SingleFeature;
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

    public List<SingleFeature> getFeatursByProduct(String typeOfProduct) {

        SingleFeature singleFeature = new SingleFeature("Producent", "producers" ,getProductsByCategory(typeOfProduct)
                .stream().map( product -> {
                    return CheckBoxProduct
                            .builder()
                            .nameCheckBox(product.getProductProducer())
                            .build();
                }).collect(Collectors.toList()));

        SingleFeature singleFeature2 = new SingleFeature("Kolor", "colors" , getProductsByCategory(typeOfProduct)
                .stream().map( product -> {
                    return CheckBoxProduct
                            .builder()
                            .nameCheckBox(product.getProductKolor())
                            .build();
                }).collect(Collectors.toList()));

        SingleFeature singleFeature3 = new SingleFeature("Pamięć Ram", "rams" ,getProductsByCategory(typeOfProduct)
                .stream().map( product -> {
                    return CheckBoxProduct
                            .builder()
                            .nameCheckBox(String.valueOf(product.getProduct_memory_ram()))
                            .build();
                }).collect(Collectors.toList()));


        List<SingleFeature> features = new ArrayList<>();
        features.add(singleFeature);
        features.add(singleFeature2);
        features.add(singleFeature3);

        return features;
    }


    public List<ProductDto> getFiltredProductDto(List<String> filtr, String productType) {

        List<String> filtrs = new ArrayList<>(filtr);
        System.out.println(Arrays.toString(filtrs.toArray()));

        return getProductsByCategoryDto(productType)
                .stream()
                .filter( laptop -> filtrs.contains(laptop.getProductProducer()))
                .collect(Collectors.toList());
    }

    public List<ProductDto> getFiltredProductDtoTest(Company filtres, String productType) {

        return getProductsByCategoryDto(productType)
                .stream()
                .filter( product -> {
                    if (filtres.getProducers() != null) {
                        return filtres.getProducers().contains(product.getProductProducer());
                    } else {
                        return true;
                    }
                })
                .filter( product -> {
                    if (filtres.getColors() != null) {
                        return filtres.getColors().contains(product.getProductKolor());
                    } else {
                        return true;
                    }
                })
                .filter( product -> {
                    if (filtres.getRams() != null) {
                        return filtres.getRams().contains(String.valueOf(product.getProduct_memory_ram()));
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }
}
