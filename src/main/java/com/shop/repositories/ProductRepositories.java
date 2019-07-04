package com.shop.repositories;

import com.shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositories extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.productType = ?1")
    List<Product> findProductByProductType(String typ);
}
