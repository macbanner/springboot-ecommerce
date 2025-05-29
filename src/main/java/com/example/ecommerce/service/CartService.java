package com.example.ecommerce.service;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    // Müşteriye ait sepeti getir yoksa oluştur.
    public Cart getCart(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    @Transactional
    public Cart addProductToCart(Long customerId, Long productId, int quantity) {
        Cart cart = getCart(customerId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Yeterli stok yok!");
        }

        // Sepette ürün var mı kontrol et, varsa miktarı güncelle yoksa yeni CartItem ekle
        CartItem item = cart.getItems().stream()
                .filter(ci -> ci.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            item = new CartItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setCart(cart);
            cart.getItems().add(item);
        }
        cart.recalculateTotalPrice();
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeProductFromCart(Long customerId, Long productId) {
        Cart cart = getCart(customerId);
        cart.getItems().removeIf(ci -> ci.getProduct().getId().equals(productId));
        cart.recalculateTotalPrice();
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart emptyCart(Long customerId) {
        Cart cart = getCart(customerId);
        cart.getItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        return cartRepository.save(cart);
    }
}
