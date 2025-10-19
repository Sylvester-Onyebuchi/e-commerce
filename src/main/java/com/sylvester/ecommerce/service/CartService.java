package com.sylvester.ecommerce.service;

import com.sylvester.ecommerce.dto.CartItemsRequest;
import com.sylvester.ecommerce.dto.CartRequest;
import com.sylvester.ecommerce.dto.UserResponse;
import com.sylvester.ecommerce.entity.Cart;
import com.sylvester.ecommerce.entity.CartItems;
import com.sylvester.ecommerce.entity.Product;
import com.sylvester.ecommerce.entity.User;
import com.sylvester.ecommerce.exception.CartItemNotFoundException;
import com.sylvester.ecommerce.exception.CartNotFoundException;
import com.sylvester.ecommerce.exception.ProductNotFoundException;
import com.sylvester.ecommerce.exception.UserNotFoundException;
import com.sylvester.ecommerce.repository.CartItemsRepository;
import com.sylvester.ecommerce.repository.CartRepository;
import com.sylvester.ecommerce.repository.ProductRepository;
import com.sylvester.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private final CartItemsRepository cartItemsRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Cart addItem(CartRequest cartRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found with id: " + userId)
        );
        Cart newCart = new Cart();
        newCart.setUser(user);
        List<CartItems> cartItems =  cartRequest.getItems().stream()
                .map(itemReq -> {
                    Product product = productRepository.findById(itemReq.getProductId()).orElseThrow(
                            () -> new ProductNotFoundException("Product not found with id: " + itemReq.getProductId())
                    );
                    CartItems cartItem = new CartItems();
                    cartItem.setCart(newCart);
                    cartItem.setProduct(product);
                    cartItem.setQuantity(itemReq.getQuantity());
                    return cartItem;

                }).toList();
        newCart.setCartItems(cartItems);
        return cartRepository.save(newCart);

    }

    public CartItems updateItem(Long cartItemId, int newQuantity) {
        CartItems cartItems = cartItemsRepository.findById(cartItemId).orElseThrow(
                () -> new CartItemNotFoundException("Item not found in the cart")
        );
        if (newQuantity <= 0){
            cartItemsRepository.delete(cartItems);
            return null;
        }
        cartItems.setQuantity(newQuantity);
        return cartItemsRepository.save(cartItems);
    }

    public void deleteItem(Long cartItemId) {
        CartItems cartItems = cartItemsRepository.findById(cartItemId).orElseThrow(
                () -> new CartItemNotFoundException("Item not found in the cart")
        );
        cartItemsRepository.delete(cartItems);
    }

    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new CartNotFoundException("Cart not found")
        );
        cartRepository.delete(cart);
    }

}
