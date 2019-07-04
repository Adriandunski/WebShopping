package com.shop.controllers;

import com.shop.commons.extras.CheckBoxProduct;
import com.shop.commons.extras.Company;
import com.shop.models.Content;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.Product;
import com.shop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private ProductService productService;
    private TemplateEngine templateEngine;

    public HomeController(ProductService productService, TemplateEngine templateEngine) {
        this.productService = productService;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/")
    public String homePage() {
        return "HomePage";
    }

    @RequestMapping(value = "/choosed/{param}")
    public String choosedPage(@PathVariable(value = "param") String product, Model model) {

        Content content = new Content(product, productService.getProductsByCategoryDto(product));
        model.addAttribute("content", content);

        List<CheckBoxProduct> producers = productService.getProducersByProduct(product);
        model.addAttribute("producers", producers);

        return "choosed";
    }

    @PostMapping("/choosed/{param}/filters")
    public String postDrinks(@ModelAttribute("company") Company companys,@PathVariable(value = "param") String product, Model model) {


        Content filtredContent = new Content(product, productService.getProductsByCategoryDto(product));
        List<CheckBoxProduct> producers = productService.getProducersByProduct(product);



        List<CheckBoxProduct> filtrs;

        if (companys.getCompa() != null) {
            filtredContent = new Content(product, productService.getFiltredProductDto(companys.getCompa(), product));

            filtrs = companys.getCompa().stream().map( filtr -> {
                return CheckBoxProduct
                        .builder()
                        .nameCheckBox(filtr)
                        .isCheck(true)
                        .build();
            }).collect(Collectors.toList());


            for(int i = 0; i < producers.size(); i++) {
                if(filtrs.contains(producers.get(i))) {
                    producers.get(i).setIsCheck(true);
                }
            }
        }

        model.addAttribute("content", filtredContent);
        model.addAttribute("producers", producers);

        return "choosed";
    }


}
