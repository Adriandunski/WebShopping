package com.shop.controllers;

import com.shop.commons.extras.CheckBoxProduct;
import com.shop.commons.extras.Company;
import com.shop.commons.extras.Filters;
import com.shop.models.Content;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.Product;
import com.shop.models.SingleFeature;
import com.shop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HomeController {

    private ProductService productService;

    public HomeController(ProductService productService, Filters filters) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String homePage() {
        return "HomePage";
    }

    @RequestMapping(value = "/choosed/{param}")
    public String choosedPage(@PathVariable(value = "param") String typOfProduct, Model model) {

        Content content = new Content(typOfProduct, productService.getProductsByCategoryDto(typOfProduct));
        model.addAttribute("content", content);

        List<SingleFeature> features = productService.getFeatursByProduct(typOfProduct);
        model.addAttribute("features", features);

        return "choosed";
    }

    @PostMapping("/choosed/{param}/filters")
    public String postDrinks(@ModelAttribute("filters") Company filters,@PathVariable(value = "param") String product, Model model) {

        List<SingleFeature> features = productService.getUpdatedFeatures(product, filters);
        Content filtredContent = new Content(product, productService.getFiltredProductDto(filters, product));

        model.addAttribute("content", filtredContent);
        model.addAttribute("features", features);

        return "choosed";
    }
}
