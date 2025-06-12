package com.example.payments.controller;

import com.example.payments.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity.ok(accountService.createAccount(userId));
    }

    @PostMapping("/topup")
    public ResponseEntity<?> topUp(@RequestHeader("X-User-Id") Long userId,
                                   @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.topUpAccount(userId, amount));
    }

    @GetMapping("/balance")
    public ResponseEntity<?> balance(@RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity.ok(Map.of("balance", accountService.getBalance(userId)));
    }
}
