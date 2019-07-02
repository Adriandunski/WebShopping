package com.shop.repositories;

import com.shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositories extends JpaRepository<Product, Long> {

}
