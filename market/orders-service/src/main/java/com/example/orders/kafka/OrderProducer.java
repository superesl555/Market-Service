package com.example.orders.kafka;

import com.example.orders.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPaymentRequest(Order order) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", order.getUserId());
        payload.put("orderId", order.getId());
        payload.put("amount", order.getAmount());

        kafkaTemplate.send("order-payments", payload);
    }
}
