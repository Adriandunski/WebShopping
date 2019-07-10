package com.shop.services;

import com.shop.commons.extras.CheckBoxProduct;
import com.shop.commons.extras.Company;
import com.shop.commons.extras.Filters;
import com.shop.commons.mapper.ProductMapper;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.DtoModels.ProductUpdateDto;
import com.shop.models.DtoModels.TypeDto;
import com.shop.models.Product;
import com.shop.models.SingleFeature;
import com.shop.models.TypeEnum;
import com.shop.repositories.FeaturesRepository;
import com.shop.repositories.ProductRepositories;
import com.sun.org.apache.xerces.internal.util.FeatureState;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepositories productRepositories;
    private ProductMapper productMapper;
    private FeaturesService featuresService;

    public ProductService(ProductRepositories productRepositories, ProductMapper productMapper, FeaturesService featuresService) {
        this.productRepositories = productRepositories;
        this.productMapper = productMapper;
        this.featuresService = featuresService;
    }

    public List<Product> getAllProducts() {
        return productRepositories.findAll();
    }

    public List<Product> getProductsByCategory(String type) {
        return productRepositories.findProductByProductType(type);
    }

    public Product saveProduct(ProductDto product) {
        return productRepositories.save(productMapper.reverse(product));
    }

    /**  DTO */

    public List<ProductDto> getProductsDto() {
        return productRepositories
                .findAll()
                .stream().map(productMapper::map)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductsByCategoryDto(String type) {

        if (type.equals("admin")) {
            return getProductsDto();
        } else {
            return getProductsByCategory(type)
                    .stream()
                    .map(productMapper::map)
                    .collect(Collectors.toList());
        }
    }

    public List<SingleFeature> znajdzCechyDlaProdutkowTEST(List<ProductDto> produkty, String typProduktu) {

        List<TypeEnum> listaCechDanegoProduktu = featuresService.getOneFeatureByNameDto(typProduktu).getFeatures();

        List<ProductDto> wszystkieProduktyDanejKategori = getProductsByCategoryDto(typProduktu);

        List<SingleFeature> cechyDlaPrzeFiltrowanychProduktów = new ArrayList<>();
        List<SingleFeature> cechyDlaWszystkichProdoktowDanejKategori = new ArrayList<>();

        for (TypeEnum obecnaCecha : listaCechDanegoProduktu) {
            cechyDlaPrzeFiltrowanychProduktów.add(new SingleFeature(obecnaCecha, produkty));
            cechyDlaWszystkichProdoktowDanejKategori.add(new SingleFeature(obecnaCecha, wszystkieProduktyDanejKategori));
        }

        for (int i = 0; i < cechyDlaWszystkichProdoktowDanejKategori.size(); i++) {
            cechyDlaWszystkichProdoktowDanejKategori.get(i).comapreTwoSingleFeature(cechyDlaPrzeFiltrowanychProduktów.get(i));
        }

        Comparator<SingleFeature> com = Comparator.comparing(x -> x.getOrder());

        return cechyDlaWszystkichProdoktowDanejKategori.stream().sorted(com).collect(Collectors.toList());
    }


    public List<SingleFeature> getFeatursByProduct(String typeOfProduct) {

        List<ProductDto> produkty = getProductsByCategoryDto(typeOfProduct);

        return znajdzCechyDlaProdutkowTEST(produkty, typeOfProduct);
    }

    public List<ProductDto> getFiltredProductDto(Company filtres, String productType) {

        return getProductsByCategoryDto(productType)
                .stream()
                .filter( product -> {
                    if (filtres.getProductProducer() != null) {
                        return filtres.getProductProducer().contains(product.getProductProducer());
                    } else {
                        return true;
                    }
                })
                .filter( product -> {
                    if (filtres.getProductKolor() != null) {
                        return filtres.getProductKolor().contains(product.getProductKolor());
                    } else {
                        return true;
                    }
                })
                .filter( product -> {
                    if (filtres.getProduct_memory_ram() != null) {
                        return filtres.getProduct_memory_ram().contains(String.valueOf(product.getProduct_memory_ram()));
                    } else {
                        return true;
                    }
                })
                .filter( product -> {
                    if (filtres.getProduct_procesor_cores() != null) {
                        return filtres.getProduct_procesor_cores().contains(String.valueOf(product.getProduct_procesor_Cores()));
                    } else {
                        return true;
                    }
                })
                .filter( product -> {
                    if (filtres.getProduct_screen_resolution() != null) {
                        return filtres.getProduct_screen_resolution().contains(String.valueOf(product.getProduct_screen_resolution()));
                    } else {
                        return true;
                    }
                })
                .filter( product -> {
                    if (filtres.getProductType() != null) {
                        return filtres.getProductType().contains(product.getType());
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<SingleFeature> getUpdatedFeatures(String productType, Company filtersOfFeatures) {
        List<TypeEnum> listaCech = featuresService.getOneFeatureByNameDto(productType).getFeatures();

        List<SingleFeature> features = znajdzCechyDlaProdutkowTEST(getFiltredProductDto(filtersOfFeatures, productType), productType);
        Filters filt = new Filters(features, filtersOfFeatures);
        return filt.getFiltredFeatures();
    }

    public boolean deleteProductByName(String productName) {

        if (productRepositories.deleteProductByName(productName) == 1) {
            return true;
        }
        return false;
    }

    public ProductDto getProdyctProductDtoByName(String productName) {

        return productMapper.map(productRepositories.findProductByProductName(productName));
    }

    public Product getProductByName(String productName) {
        return productRepositories.findProductByProductName(productName);
    }

    public TypeDto getPrdoductForUpdate(String nazwaProduktu) {

        Product produkt = productRepositories.findProductByProductName(nazwaProduktu);
        List<TypeEnum> typy =  featuresService.getOneFeatureByNameDto(produkt.getProductType()).getFeatures();

        typy.stream().map(t -> {

            if (t.getTypeSpec().equals("productType")) {
                t.setField(produkt.getProductType());
            } else if (t.getTypeSpec().equals("productProducer")) {
                t.setField(produkt.getProductProducer());
            } else if (t.getTypeSpec().equals("productKolor")) {
                t.setField(produkt.getProductKolor());
            } else if (t.getTypeSpec().equals("product_memory_ram")) {
                t.setField(String.valueOf(produkt.getProduct_memory_ram()));
            } else if (t.getTypeSpec().equals("product_procesor_cores")) {
                t.setField(produkt.getProduct_procesor_Cores());
            } else if (t.getTypeSpec().equals("product_screen_resolution")) {
                t.setField(produkt.getProduct_screen_resolution());
            }

            return t;
        }).collect(Collectors.toList());

        return TypeDto
                .builder()
                .features(typy)
                .typeName(produkt.getProductType())
                .build();
    }

    public Product updateProduct(ProductDto dto) {

        Product product = getProductByName(dto.getProductName());
        Product newProdyct = productMapper.reverse(dto);
        newProdyct.setProductId(product.getProductId());
        newProdyct.setProductType(product.getProductType());

        return productRepositories.save(newProdyct);
    }
}
