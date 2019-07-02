package com.shop.services;

import com.shop.commons.mapper.ProductMapper;
import com.shop.models.DtoModels.ItemDto;
import com.shop.models.Product;
import com.shop.models.Type;
import com.shop.repositories.ItemRepositories;
import com.shop.repositories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private TypeRepository typeRepository;
    private ItemRepositories itemRepositories;
    private ProductMapper productMapper;

    public ItemService(TypeRepository typeRepository, ItemRepositories itemRepositories, ProductMapper productMapper) {
        this.typeRepository = typeRepository;
        this.itemRepositories = itemRepositories;
        this.productMapper = productMapper;
    }

    public Type getTypeByName(String typeName) {
        return typeRepository.findTypeByName(typeName);
    }

    public List<Product> getItemByName() {
        return itemRepositories.findAll();
    }

    public List<ItemDto> getItemsDto() {
        return itemRepositories.findAll()
                .stream()
                .map(productMapper::map)
                .collect(Collectors.toList());
    }
}
