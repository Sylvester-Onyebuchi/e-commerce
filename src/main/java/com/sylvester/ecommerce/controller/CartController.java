package com.sylvester.ecommerce.controller;

import com.sylvester.ecommerce.dto.CartRequest;
import com.sylvester.ecommerce.entity.Cart;
import com.sylvester.ecommerce.entity.CartItems;
import com.sylvester.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<Cart> addCart(@RequestBody CartRequest cartRequest, @RequestParam Long userId) {
        Cart cart = cartService.addItem(cartRequest, userId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItems> updateCart(@PathVariable Long id, @RequestParam int quantity) {
        CartItems cartItems = cartService.updateItem(id, quantity);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/cart")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

}
