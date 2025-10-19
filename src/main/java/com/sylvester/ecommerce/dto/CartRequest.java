package com.sylvester.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartRequest {
    private List<CartItemsRequest> items;

}
