package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    // Bir sepet bir müşteriye ait. İlişkiye ihtiyaç olduğunda veri çekilir.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    // Sepetteki ürünler. İşlemi ilgili diğer entity'lere de uygula. //Veriyi anında yükle.
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartItem> items = new ArrayList<>();
    //CartItem sepette değilse veritabanından da silinmeli.

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalPrice = BigDecimal.ZERO;


    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
        recalculateTotalPrice();
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null); //
        recalculateTotalPrice();
    }

    public void recalculateTotalPrice() {
        this.totalPrice = items.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}