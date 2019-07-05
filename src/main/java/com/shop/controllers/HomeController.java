package com.shop.controllers;

import com.shop.commons.extras.CheckBoxProduct;
import com.shop.commons.extras.Company;
import com.shop.models.Content;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.Product;
import com.shop.models.SingleFeature;
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
    public String choosedPage(@PathVariable(value = "param") String typOfProduct, Model model) {

        Content content = new Content(typOfProduct, productService.getProductsByCategoryDto(typOfProduct));
        model.addAttribute("content", content);

        List<SingleFeature> features = productService.getFeatursByProduct(typOfProduct);
        model.addAttribute("features", features);

        return "choosed";
    }

    @PostMapping("/choosed/{param}/filters")
    public String postDrinks(@ModelAttribute("filters") Company filters,@PathVariable(value = "param") String product, Model model) {


        Content filtredContent = new Content(product, productService.getProductsByCategoryDto(product));
        List<SingleFeature> features = productService.getFeatursByProduct(product);
        boolean changes = false;

        if (filters.getProducers() != null) {
            changes = true;
            List<CheckBoxProduct> producerChecks = filters.getProducers().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            for (int i = 0; i < features.size(); i++) {

                if (features.get(i).getTypeSpec().equals("producers")) {

                    for(int j = 0; j < features.get(i).getSpecsProduct().size(); j++) {

                        if(producerChecks.contains(features.get(i).getSpecsProduct().get(j))) {
                            features.get(i).getSpecsProduct().get(j).setIsCheck(true);
                        }
                    }
                }
            }
        }

        if (filters.getColors() != null) {
            changes = true;
            List<CheckBoxProduct> producerChecks = filters.getColors().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            for (int i = 0; i < features.size(); i++) {

                if (features.get(i).getTypeSpec().equals("colors")) {

                    for(int j = 0; j < features.get(i).getSpecsProduct().size(); j++) {

                        if(producerChecks.contains(features.get(i).getSpecsProduct().get(j))) {
                            features.get(i).getSpecsProduct().get(j).setIsCheck(true);
                        }
                    }
                }
            }
        }

        if (filters.getRams() != null) {
            changes = true;
            List<CheckBoxProduct> producerChecks = filters.getRams().stream().map(x -> {
                return CheckBoxProduct.builder().nameCheckBox(x).isCheck(true).build();
            }).collect(Collectors.toList());

            for (int i = 0; i < features.size(); i++) {

                if (features.get(i).getTypeSpec().equals("rams")) {

                    for(int j = 0; j < features.get(i).getSpecsProduct().size(); j++) {

                        if(producerChecks.contains(features.get(i).getSpecsProduct().get(j))) {
                            features.get(i).getSpecsProduct().get(j).setIsCheck(true);
                        }
                    }
                }
            }
        }



        filtredContent = new Content(product, productService.getFiltredProductDtoTest(filters, product));


        model.addAttribute("content", filtredContent);
        model.addAttribute("features", features);

        return "choosed";
    }


}
