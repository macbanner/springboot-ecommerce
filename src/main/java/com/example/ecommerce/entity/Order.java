package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    // Getters & Setters
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Siparişe ait benzersiz kod.
    private String orderCode = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private BigDecimal totalPrice = BigDecimal.ZERO;

    // Sipariş toplamını orderItem’lara göre hesapla
    public void recalculateTotal() {
        totalPrice = orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
