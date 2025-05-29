package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{customerId}")
    public Cart getCart(@PathVariable Long customerId) {
        return cartService.getCart(customerId);
    }

    @PostMapping("/add/{customerId}/{productId}/{quantity}")
    public Cart addProductToCart(@PathVariable Long customerId, @PathVariable Long productId, @PathVariable int quantity) {
        return cartService.addProductToCart(customerId, productId, quantity);
    }

    @DeleteMapping("/remove/{customerId}/{productId}")
    public Cart removeProductFromCart(@PathVariable Long customerId, @PathVariable Long productId) {
        return cartService.removeProductFromCart(customerId, productId);
    }

    @DeleteMapping("/empty/{customerId}")
    public Cart emptyCart(@PathVariable Long customerId) {
        return cartService.emptyCart(customerId);
    }
}
