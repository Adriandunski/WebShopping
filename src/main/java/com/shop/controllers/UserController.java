package com.shop.controllers;

import com.shop.commons.extras.Filters;
import com.shop.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserController {

    private ProductService productService;

    public UserController(ProductService productService, Filters filters) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String homePage(Model model, Principal principal) {
        model.addAttribute("name", principal.getName());

        return "HomeUser";
    }
}
