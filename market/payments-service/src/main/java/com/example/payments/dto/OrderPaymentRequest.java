package com.example.payments.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPaymentRequest {
    private Long userId;
    private Long orderId;
    private BigDecimal amount;
}
