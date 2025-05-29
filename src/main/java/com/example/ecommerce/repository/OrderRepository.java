package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderCode(String orderCode);
    List<Order> findByCustomerId(Long customerId);
}
