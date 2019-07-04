package com.shop.services;

import com.shop.models.Category;
import com.shop.repositories.CategoryRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepositories categoryRepositories;

    public CategoryService(CategoryRepositories categoryRepositories) {
        this.categoryRepositories = categoryRepositories;
    }

    public List<Category> getCategories() {
        return categoryRepositories.findAll();
    }
}
