package com.sylvester.ecommerce.mapper;

import com.sylvester.ecommerce.dto.ProductRequest;
import com.sylvester.ecommerce.dto.ProductResponse;
import com.sylvester.ecommerce.entity.Category;
import com.sylvester.ecommerce.entity.Product;

public class ProductMapper {
    public static ProductResponse toDto(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductName(product.getProductName());
        productResponse.setProductDescription(product.getProductDescription());
        productResponse.setProductPrice(product.getProductPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setCategory(product.getCategory().getName());
        productResponse.setAvailable(product.isAvailable());
        return productResponse;
    }

    public static Product toEntity(ProductRequest productRequest, Category category) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductPrice(productRequest.getProductPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setCategory(category);
        product.setAvailable(true);
        return product;
    }

}
