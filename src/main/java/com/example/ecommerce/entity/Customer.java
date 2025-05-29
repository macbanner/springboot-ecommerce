package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter

public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
}