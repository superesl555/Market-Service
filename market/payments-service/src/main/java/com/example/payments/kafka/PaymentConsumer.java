package com.example.payments.kafka;

import com.example.payments.dto.OrderPaymentRequest;
import com.example.payments.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final AccountService accountService;

    @KafkaListener(topics = "order-payments", groupId = "payments-service")
    public void consume(OrderPaymentRequest request) {
        log.info("Received payment request for orderId={} from userId={}, amount={}",
                request.getOrderId(), request.getUserId(), request.getAmount());

        boolean success = accountService.withdrawIfSufficient(request.getUserId(), request.getAmount());

        if (success) {
            log.info("Payment for order {} processed successfully.", request.getOrderId());
            // TODO: Отправить событие "payment-success"
        } else {
            log.warn("Payment for order {} failed: insufficient funds.", request.getOrderId());
            // TODO: Отправить событие "payment-failure"
        }
    }
}
