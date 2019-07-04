package com.shop.controllers;

import com.shop.commons.mapper.ProductMapper;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.Product;
import com.shop.models.Category;
import com.shop.services.ProductService;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/products")
    public List<Product> getProducts(@RequestParam(value = "type", required = false) String type) {

        if (type != null) {
            return productService.getProductsByCategory(type);
        } else {
            return productService.getAllProducts();
        }
    }
}
