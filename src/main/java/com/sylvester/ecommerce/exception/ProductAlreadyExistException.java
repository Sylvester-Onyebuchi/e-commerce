package com.sylvester.ecommerce.exception;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String productAlreadyExist) {
        super(productAlreadyExist);
    }
}
