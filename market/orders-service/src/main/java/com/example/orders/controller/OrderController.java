package com.example.orders.controller;

import com.example.orders.dto.CreateOrderRequest;
import com.example.orders.model.Order;
import com.example.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order create(@RequestHeader("user_id") Long userId,
                        @RequestBody CreateOrderRequest request) {
        return orderService.createOrder(userId, request);
    }

    @GetMapping
    public List<Order> list(@RequestHeader("user_id") Long userId) {
        return orderService.getOrders(userId);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
}
