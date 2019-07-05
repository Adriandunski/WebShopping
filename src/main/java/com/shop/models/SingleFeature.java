package com.shop.models;

import com.shop.commons.extras.CheckBoxProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SingleFeature {

    private String specName;
    private String typeSpec;
    private List<CheckBoxProduct> specsProduct;

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
