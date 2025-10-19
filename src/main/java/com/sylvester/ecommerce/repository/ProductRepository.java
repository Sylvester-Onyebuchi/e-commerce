package com.sylvester.ecommerce.repository;

import com.sylvester.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductNameIgnoreCase(String productName);
}
