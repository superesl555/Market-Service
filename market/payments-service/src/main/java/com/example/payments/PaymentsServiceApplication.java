package com.example.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;



@SpringBootApplication
public class PaymentsServiceApplication {

    @Value("${spring.kafka.bootstrap-servers}")
    private static String kafkaServers;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PaymentsServiceApplication.class, args);
        String bootstrapServers = context.getEnvironment().getProperty("spring.kafka.bootstrap-servers");
        System.out.println("🛰 KAFKA BOOTSTRAP SERVERS = " + bootstrapServers);
    }
}