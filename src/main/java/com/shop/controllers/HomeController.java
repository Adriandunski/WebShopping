package com.shop.controllers;

import com.shop.commons.extras.Company;
import com.shop.commons.extras.Filters;
import com.shop.models.Content;
import com.shop.models.SingleFeature;
import com.shop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
public class HomeController {

    private ProductService productService;

    public HomeController(ProductService productService, Filters filters) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String homePage(Model model, Principal principal) {

        model.addAttribute("name", getUserName(principal));
        return "HomePage";
    }

    @RequestMapping(value = "/choosed/{param}")
    public String choosedPage(@PathVariable(value = "param") String typOfProduct, Model model, Principal principal) {

        Content content = new Content(typOfProduct, productService.getProductsByCategoryDto(typOfProduct));
        model.addAttribute("content", content);

        List<SingleFeature> features = productService.getFeatursByProduct(typOfProduct);
        model.addAttribute("features", features);
        model.addAttribute("name", getUserName(principal));

        return "choosed";
    }

    @PostMapping("/choosed/{param}/filters")
    public String postDrinks(@ModelAttribute("filters") Company filters,@PathVariable(value = "param") String product, Model model, Principal principal) {

        List<SingleFeature> features = productService.getUpdatedFeatures(product, filters);
        Content filtredContent = new Content(product, productService.getFiltredProductDto(filters, product));

        model.addAttribute("content", filtredContent);
        model.addAttribute("features", features);
        model.addAttribute("name", getUserName(principal));

        return "choosed";
    }

    private String getUserName(Principal principal) {

        String welcome;

        if (principal != null) {
            welcome = principal.getName();
        } else {
            welcome = "null";
        }

        return welcome;
    }
}
