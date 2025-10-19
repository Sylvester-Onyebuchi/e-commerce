package com.sylvester.ecommerce.dto;

import com.sylvester.ecommerce.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private int quantity;
    private boolean isAvailable;
    @NotBlank(message = "Category name is required")
    private String categoryName;
}
