package com.example.orders.service;

import com.example.orders.dto.CreateOrderRequest;
import com.example.orders.kafka.OrderProducer;
import com.example.orders.model.Order;
import com.example.orders.model.OrderStatus;
import com.example.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public Order createOrder(Long userId, CreateOrderRequest request) {
        Order order = Order.builder()
                .userId(userId)
                .amount(request.getAmount())
                .status(OrderStatus.PENDING)
                .build();

        Order saved = orderRepository.save(order);
        orderProducer.sendPaymentRequest(saved);
        return saved;
    }

    public List<Order> getOrders(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
}
