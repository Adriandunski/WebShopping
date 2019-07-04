package com.shop.commons.mapper;

public interface MapperDto<F, T> {

    T map (F from);
    F reverse (T to);
}
