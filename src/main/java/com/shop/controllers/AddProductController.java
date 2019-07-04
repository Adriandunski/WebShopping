package com.shop.controllers;

import com.shop.services.ProductService;
import org.springframework.stereotype.Controller;

@Controller
public class AddProductController {

    private ProductService productService;

    public AddProductController(ProductService productService) {
        this.productService = productService;
    }

}
