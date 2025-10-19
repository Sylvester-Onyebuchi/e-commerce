package com.sylvester.ecommerce.dto;

import com.sylvester.ecommerce.entity.Category;
import lombok.Data;

@Data
public class ProductResponse {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private int quantity;
    private boolean isAvailable;
    private String category;
}
