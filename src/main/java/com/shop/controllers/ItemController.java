package com.shop.controllers;

import com.shop.models.DtoModels.ItemDto;
import com.shop.models.Product;
import com.shop.models.Type;
import com.shop.services.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/type")
    public Type getType(@RequestParam(name = "typeName") String typeName)  {
        return itemService.getTypeByName(typeName);
    }

    @GetMapping("/items")
    public List<Product> getItems() {
        return itemService.getItemByName();
    }

    @GetMapping("/items/dto")
    public List<ItemDto> getItemsDto() {
        return itemService.getItemsDto();
    }
}
