package com.shop.models;

import lombok.Data;

public enum  TypeEnum {

    TYPEPRODUCT("productType", "Typ Produktu:", "", 1),
    PRODUCER("productProducer", "Producenci", "", 2),
    COLOR("productKolor", "Kolory:", "", 3),
    RAM("product_memory_ram", "Pamięć Ram:", "", 4),
    CORE("product_procesor_cores", "Rdzenie:", "", 5),
    RESOLU("product_screen_resolution", "Rozdzielczość:", "", 6);

    private String typeSpec;
    private String showInWeb;
    private String field;
    private int order;

    TypeEnum(String typeSpec, String showInWeb, String field, int order) {
        this.typeSpec = typeSpec;
        this.showInWeb = showInWeb;
        this.field = field;
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public String getTypeSpec() {
        return typeSpec;
    }

    public String getShowInWeb() {
        return showInWeb;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
