package com.example.ecommerce.entity;

public enum OrderStatus {
    PENDING,    // Beklemede
    PROCESSING, // İşleniyor
    SHIPPED,    // Kargolandı
    DELIVERED,  // Teslim Edildi
    CANCELLED   // İptal Edildi
}