package com.shop.models.DtoModels;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {

    private String showInWebTitle;
    private String typSpecyfikacji;
    private String danezPola;
}
