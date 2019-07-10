package com.shop.controllers;

import com.shop.commons.extras.Company;
import com.shop.models.*;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.DtoModels.TypeDto;
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

    @GetMapping("admin/AllProducts")
    public String getProductsPage(Model model) {

        Content content = new Content("admin", productService.getProductsByCategoryDto("admin"));
        model.addAttribute("content", content);

        List<SingleFeature> features = productService.getFeatursByProduct("admin");
        model.addAttribute("features", features);

        return "choosedAdmin";
    }

    @PostMapping("/admin/addProduct/{typeOfProduct}")
    public String dodajPrzedmiot(@PathVariable(value = "typeOfProduct") String typeOfProduct,
                             @ModelAttribute("product")ProductDto product, Model model) {

        product.setType(typeOfProduct);
        productService.saveProduct(product);

        return "redirect:/admin/AllProducts";
    }

    @PostMapping("/admin/{param}/filters")
    public String postDrinks(@ModelAttribute("filters") Company filters, @PathVariable(value = "param") String product, Model model) {

        List<SingleFeature> features = productService.getUpdatedFeatures(product, filters);
        Content filtredContent = new Content(product, productService.getFiltredProductDto(filters, product));

        model.addAttribute("content", filtredContent);
        model.addAttribute("features", features);

        return "choosedAdmin";
    }

    @GetMapping("/admin/delete")
    public String deletePlanet(@RequestParam(value = "product") String productName) {
        productService.deleteProductByName(productName);
        return "redirect:/admin/AllProducts";
    }


    @GetMapping("admin/addProduct/{typPrzedmiotu}")
    public String dodajPrzedmiotStrona(@PathVariable(name = "typPrzedmiotu") String typPrzedmiotu, Model model) {

        TypeDto listaCech = featuresService.getOneFeatureByNameDto(typPrzedmiotu);
        model.addAttribute("cechy", listaCech);

        return "addProduct";
    }


    @GetMapping("admin/update")
    public String upDateProductPage(@RequestParam(value = "product") String productName, Model model) {

        TypeDto cechylista = productService.getPrdoductForUpdate(productName);
        ProductDto produkt = productService.getProdyctProductDtoByName(productName);

        model.addAttribute("cechy", cechylista);
        model.addAttribute("product", produkt);

        return "updateProduct";
    }

    @PostMapping("admin/update")
    public String upDateProductPage(@ModelAttribute(name = "product") ProductDto product) {

        productService.updateProduct(product);

        return "redirect:/admin/AllProducts";
    }
}
