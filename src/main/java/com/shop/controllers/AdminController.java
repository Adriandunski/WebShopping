package com.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("admin/addProduct")
    public String getAddProductPage() {
        return "addProduct";
    }

    @GetMapping("admin/AllProducts")
    public String getProductsPage() {



        return "choosed";
    }
}
