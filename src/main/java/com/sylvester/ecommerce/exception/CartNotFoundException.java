package com.sylvester.ecommerce.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String cartNotFound) {
        super(cartNotFound);
    }
}
