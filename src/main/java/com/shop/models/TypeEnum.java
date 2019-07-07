package com.shop.models;

import lombok.Data;

public enum  TypeEnum {

    PRODUCER("producers", "Producenci"),
    COLOR("colors", "Kolory:"),
    RAM("rams", "Pamięć Ram:"),
    CORE("cores", "Rdzenie:"),
    RESOLU("resolutions", "Rozdzielczość:");

    private String typeSpec;
    private String showInWeb;

    TypeEnum(String typeSpec, String showInWeb) {
        this.typeSpec = typeSpec;
        this.showInWeb = showInWeb;
    }

    public String getTypeSpec() {
        return typeSpec;
    }

    public String getShowInWeb() {
        return showInWeb;
    }
}
