package com.sylvester.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleProductAlreadyExistException(ProductAlreadyExistException ex) {
        Map<String,String> error = new HashMap<>();
        error.put("message", "Product Already Exist");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException ex) {
        Map<String,String> error = new HashMap<>();
        error.put("message", "Product Not Found");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String,String> error = new HashMap<>();
        error.put("message", "User Not Found");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        Map<String,String> error = new HashMap<>();
        error.put("message", "User Already Exist");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartItemNotFoundException(CartItemNotFoundException ex) {
        Map<String,String> error = new HashMap<>();
        error.put("message", "Cart Item Not Found");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartNotFoundException(CartNotFoundException ex) {
        Map<String,String> error = new HashMap<>();
        error.put("message", "Cart Not Found");
        return ResponseEntity.badRequest().body(error);
    }
}
