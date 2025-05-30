package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class OrderItem extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long productId;
    private String productName;
    private BigDecimal priceAtPurchase;
    private Integer quantity;

    // Toplam fiyat ürün fiyatı * miktar
    public BigDecimal getTotalPrice() {
        return priceAtPurchase.multiply(new BigDecimal(quantity));
    }

}
