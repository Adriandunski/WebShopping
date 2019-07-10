package com.shop.models;

import com.shop.commons.extras.CheckBoxProduct;
import com.shop.commons.mapper.TypeMapper;
import com.shop.models.DtoModels.ProductDto;
import com.shop.models.DtoModels.TypeDto;
import com.shop.services.FeaturesService;
import com.shop.services.ProductService;
import lombok.*;
import org.hibernate.annotations.Check;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SingleFeature{

    private String specName;
    private String typeSpec;
    private int order;
    private List<CheckBoxProduct> specsProduct;

    public SingleFeature(TypeEnum obecnaCecha, List<ProductDto> produkty) {
        this.specName = obecnaCecha.getShowInWeb();
        this.typeSpec = obecnaCecha.getTypeSpec();
        this.order = obecnaCecha.getOrder();
        znajdzWszystkieCechyProduktu(produkty);
    }


    private void znajdzWszystkieCechyProduktu(List<ProductDto> produkty) {

        if (typeSpec.equals("productProducer")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaProducenta(produkty));
        } else if (typeSpec.equals("productKolor")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaKoloru(produkty));
        } else if (typeSpec.equals("product_memory_ram")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaRamu(produkty));
        } else if (typeSpec.equals("product_procesor_cores")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaRdzeni(produkty));
        } else if (typeSpec.equals("product_screen_resolution")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaRozdzielczosci(produkty));
        } else if (typeSpec.equals("productType")) {
            specsProduct = new ArrayList<>(dodajFeatureDlaTypu(produkty));
        }
    }



    private List<CheckBoxProduct> dodajFeatureDlaKoloru(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(p.getProductKolor())
                    .build();

            String cecha = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {
                if (obecnyProdukt.getProductKolor().equals(cecha)) {
                    c++;
                }
            }

            temp.setQuantityOfProduct(c);

            return temp;
       }).distinct().filter(p -> { if (p.getNameCheckBox().equals("null")) { return false; }
        else { return true; } }).collect(Collectors.toList());
    }

    private List<CheckBoxProduct> dodajFeatureDlaProducenta(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(p.getProductProducer())
                    .build();

            String cecha = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {
                if (obecnyProdukt.getProductProducer().equals(cecha)) {
                    c++;
                }
            }

            temp.setQuantityOfProduct(c);

            return temp;
        }).distinct().filter(p -> { if (p.getNameCheckBox().equals("null")) { return false; }
        else { return true; } }).collect(Collectors.toList());
    }

    private List<CheckBoxProduct> dodajFeatureDlaRamu(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(String.valueOf(p.getProduct_memory_ram()))
                    .build();

            String cecha = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {
                if (String.valueOf(obecnyProdukt.getProduct_memory_ram()).equals(cecha)) {
                    c++;
                }
            }

            temp.setQuantityOfProduct(c);

            return temp;
        }).distinct().filter(p -> { if (p.getNameCheckBox().equals("null")) { return false; }
            else { return true; } }).sorted( (o1, o2) -> {
            if (Integer.parseInt(o1.getNameCheckBox()) > Integer.parseInt(o2.getNameCheckBox())) {
                return 1;
            } else if (Integer.parseInt(o1.getNameCheckBox()) < Integer.parseInt(o2.getNameCheckBox())) {
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());
    }

    private List<CheckBoxProduct> dodajFeatureDlaRdzeni(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(p.getProduct_procesor_Cores())
                    .build();

            String cecha = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {

                try {
                    if(obecnyProdukt.getProduct_procesor_Cores().equals(cecha)) {
                        c++;
                    }
                } catch (NullPointerException e) {
                    obecnyProdukt.setProduct_procesor_Cores("null");
                }
            }

            temp.setQuantityOfProduct(c);

            return temp;
        }).distinct().filter(p -> {

            try {
                if (p.getNameCheckBox().equals("null")) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }

            return true;

        }).sorted(new Comparator<CheckBoxProduct>() {
            @Override
            public int compare(CheckBoxProduct o1, CheckBoxProduct o2) {
                if (Integer.parseInt(o1.getNameCheckBox()) > Integer.parseInt(o2.getNameCheckBox())) {
                    return 1;
                } else if (Integer.parseInt(o1.getNameCheckBox()) < Integer.parseInt(o2.getNameCheckBox())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }).collect(Collectors.toList());
    }

    private List<CheckBoxProduct> dodajFeatureDlaRozdzielczosci(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(p.getProduct_screen_resolution())
                    .build();

            String cecha = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {
                if(obecnyProdukt.getProduct_screen_resolution().equals(cecha)) {
                    c++;
                }
            }

            temp.setQuantityOfProduct(c);

            return temp;
        }).distinct().filter(p -> { if (p.getNameCheckBox().equals("null")) { return false; }
        else { return true; } }).collect(Collectors.toList());
    }

    private List<CheckBoxProduct> dodajFeatureDlaTypu(List<ProductDto> produkty) {

        return produkty.stream().map( p -> {

            CheckBoxProduct temp = CheckBoxProduct
                    .builder()
                    .nameCheckBox(p.getType())
                    .build();

            String cecha = temp.getNameCheckBox();
            int c = 0;

            for (ProductDto obecnyProdukt : produkty) {
                if(obecnyProdukt.getType().equals(cecha)) {
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
            try {
                specsProduct.get(box).setQuantityOfProduct(temp.getQuantityOfProduct());
            } catch (Exception e) {

            }
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
