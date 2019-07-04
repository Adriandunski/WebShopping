package com.shop.repositories;

import com.shop.models.Category;
import com.shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepositories extends JpaRepository<Category, Long> {
}
