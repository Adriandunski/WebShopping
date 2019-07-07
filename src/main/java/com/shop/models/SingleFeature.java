package com.shop.models;

import com.shop.commons.extras.CheckBoxProduct;
import com.shop.commons.mapper.TypeMapper;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.DtoModels.TypeDto;
import com.shop.services.FeaturesService;
import com.shop.services.ProductService;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SingleFeature{

    private String specName;
    private String typeSpec;
    private List<CheckBoxProduct> specsProduct;

    public SingleFeature(TypeEnum obecnaCecha, List<ProductDto> produkty) {
        this.specName = obecnaCecha.getShowInWeb();
        this.typeSpec = obecnaCecha.getTypeSpec();
        znajdzWszystkieCechyProduktu(produkty);
    }


    private void znajdzWszystkieCechyProduktu(List<ProductDto> produkty) {

        if (typeSpec.equals("producers")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaProducenta(produkty));
        } else if (typeSpec.equals("colors")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaKoloru(produkty));
        } else if (typeSpec.equals("rams")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaRamu(produkty));
        } else if (typeSpec.equals("cores")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaRdzeni(produkty));
        } else if (typeSpec.equals("resolutions")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaRozdzielczosci(produkty));
        }
    }

    private List<CheckBoxProduct> dodajFeatureDlaKoloru(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(p.getProductKolor())
                    .build();

            String kolor = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {
                if (obecnyProdukt.getProductKolor().equals(kolor)) {
                    c++;
                }
            }

            temp.setQuantityOfProduct(c);

            return temp;
       }).distinct().filter(p -> {
           if(p.getNameCheckBox() != null) {
                return true;}
           else {
               return false;
           }
       }).distinct().filter(p -> { if (p.getNameCheckBox().equals("null")) { return false; }
        else { return true; } }).collect(Collectors.toList());
    }

    private List<CheckBoxProduct> dodajFeatureDlaProducenta(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(p.getProductProducer())
                    .build();

            String producent = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {
                if (obecnyProdukt.getProductProducer().equals(producent)) {
                    c++;
                }
            }

            temp.setQuantityOfProduct(c);

            return temp;
        }).distinct().filter(p -> {
            if(p.getNameCheckBox() != null) {
                return true;}
            else {
                return false;
            }
        }).distinct().filter(p -> { if (p.getNameCheckBox().equals("null")) { return false; }
        else { return true; } }).collect(Collectors.toList());
    }

    private List<CheckBoxProduct> dodajFeatureDlaRamu(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(String.valueOf(p.getProduct_memory_ram()))
                    .build();

            String producent = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {
                if (String.valueOf(obecnyProdukt.getProduct_memory_ram()).equals(producent)) {
                    c++;
                }
            }

            temp.setQuantityOfProduct(c);

            return temp;
        }).distinct().filter(p -> { if (p.getNameCheckBox().equals("null")) { return false; }
            else { return true; } }).collect(Collectors.toList());
    }

    private List<CheckBoxProduct> dodajFeatureDlaRdzeni(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(p.getProduct_procesor_Cores())
                    .build();

            String producent = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {
                if(obecnyProdukt.getProduct_procesor_Cores().equals(producent)) {
                    c++;
                }
            }

            temp.setQuantityOfProduct(c);

            return temp;
        }).distinct().filter(p -> { if (p.getNameCheckBox().equals("null")) { return false; }
        else { return true; } }).collect(Collectors.toList());
    }

    private List<CheckBoxProduct> dodajFeatureDlaRozdzielczosci(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(p.getProduct_screen_resolution())
                    .build();

            String producent = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {
                if(obecnyProdukt.getProduct_screen_resolution().equals(producent)) {
                    c++;
                }
            }

            temp.setQuantityOfProduct(c);

            return temp;
        }).distinct().filter(p -> { if (p.getNameCheckBox().equals("null")) { return false; }
        else { return true; } }).collect(Collectors.toList());
    }

    public void comapreTwoSingleFeature(SingleFeature posortowaneCechy) {

        List<CheckBoxProduct> posortowaneCheckoxy = posortowaneCechy.getSpecsProduct();

        for (int i = 0; i < specsProduct.size(); i++) {
            specsProduct.get(i).setQuantityOfProduct(0);
        }

        for (int i = 0; i < posortowaneCheckoxy.size(); i++) {

            CheckBoxProduct temp = posortowaneCheckoxy.get(i);

            int box = specsProduct.indexOf(temp);
            specsProduct.get(box).setQuantityOfProduct(temp.getQuantityOfProduct());
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleFeature that = (SingleFeature) o;
        return Objects.equals(typeSpec, that.typeSpec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeSpec);
    }
}
