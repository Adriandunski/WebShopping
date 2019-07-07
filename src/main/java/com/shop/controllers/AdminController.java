package com.shop.controllers;

import com.shop.commons.extras.Company;
import com.shop.models.Content;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.DtoModels.TypeDto;
import com.shop.models.SingleFeature;
import com.shop.models.Type;
import com.shop.models.TypeEnum;
import com.shop.services.FeaturesService;
import com.shop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private ProductService productService;
    private FeaturesService featuresService;

    public AdminController(ProductService productService, FeaturesService featuresService) {
        this.productService = productService;
        this.featuresService = featuresService;
    }

    @GetMapping("admin/addProduct")
    public String getAddProductPage() {
        return "addProduct";
    }

    @GetMapping("admin/AllProducts")
    public String getProductsPage(Model model) {

        Content content = new Content("admin", productService.getProductsByCategoryDto("admin"));
        model.addAttribute("content", content);

        List<SingleFeature> features = productService.getFeatursByProduct("admin");
        model.addAttribute("features", features);

        return "choosedAdmin";
    }

    @GetMapping("admin/addProduct/{typPrzedmiotu}")
    public String dodajPrzedmiotStrina(@PathVariable(name = "typPrzedmiotu") String typPrzedmiotu, Model model) {

        TypeDto listaCech = featuresService.getOneFeatureByNameDto(typPrzedmiotu);
        model.addAttribute("cechy", listaCech);

        return "addProduct";
    }

    @PostMapping("/admin/addProduct/{typeOfProduct}")
    public String postDrinks(@PathVariable(value = "typeOfProduct") String typeOfProduct,
                             @ModelAttribute("product")ProductDto product, Model model) {

        Content content = new Content("admin", productService.getProductsByCategoryDto("admin"));
        model.addAttribute("content", content);

        List<SingleFeature> features = productService.getFeatursByProduct("admin");
        model.addAttribute("features", features);

        product.setType(typeOfProduct);
        productService.saveProduct(product);

        return "choosedAdmin";
    }

    @PostMapping("/choosedAdmin/{param}/filters")
    public String postDrinks(@ModelAttribute("filters") Company filters, @PathVariable(value = "param") String product, Model model) {

        List<SingleFeature> features = productService.getUpdatedFeatures(product, filters);
        Content filtredContent = new Content(product, productService.getFiltredProductDto(filters, product));

        model.addAttribute("content", filtredContent);
        model.addAttribute("features", features);

        return "choosedAdmin";
    }
}
