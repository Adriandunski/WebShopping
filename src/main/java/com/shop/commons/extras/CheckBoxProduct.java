package com.shop.commons.extras;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckBoxProduct {

    private String nameCheckBox;
    private int quantityOfProduct;
    private Boolean isCheck = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckBoxProduct that = (CheckBoxProduct) o;
        return Objects.equals(nameCheckBox, that.nameCheckBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameCheckBox);
    }
}
