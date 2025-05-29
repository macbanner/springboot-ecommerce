package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place/{customerId}")
    public Order placeOrder(@PathVariable Long customerId) {
        return orderService.placeOrder(customerId);
    }

    @GetMapping("/{orderCode}")
    public Order getOrderForCode(@PathVariable String orderCode) {
        return orderService.getOrderForCode(orderCode);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getAllOrdersForCustomer(@PathVariable Long customerId) {
        return orderService.getAllOrdersForCustomer(customerId);
    }
}
