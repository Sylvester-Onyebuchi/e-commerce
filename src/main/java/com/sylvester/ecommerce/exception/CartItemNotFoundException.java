package com.sylvester.ecommerce.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String s) {
        super(s);
    }
}
