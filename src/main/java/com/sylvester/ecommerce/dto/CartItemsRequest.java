package com.sylvester.ecommerce.dto;

import lombok.Data;

@Data
public class CartItemsRequest {
    private Long productId;
    private int quantity;
}
