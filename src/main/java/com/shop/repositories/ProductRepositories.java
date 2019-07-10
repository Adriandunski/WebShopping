package com.shop.repositories;

import com.shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepositories extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.productType = ?1")
    List<Product> findProductByProductType(String typ);

    @Query("select p from Product p where p.productName = ?1")
    Product findProductByProductName(String productName);

    @Transactional //spring
    @Modifying
    @Query("delete from Product p where p.productName = ?1")
    int deleteProductByName(String productName);
}
