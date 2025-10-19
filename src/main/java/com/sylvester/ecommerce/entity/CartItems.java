package com.sylvester.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;

    private int quantity;
}
