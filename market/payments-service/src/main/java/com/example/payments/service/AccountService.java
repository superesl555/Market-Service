package com.example.payments.service;

import com.example.payments.model.Account;
import com.example.payments.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account createAccount(Long userId) {
        if (accountRepository.findByUserId(userId).isPresent()) {
            throw new IllegalArgumentException("Account already exists for user " + userId);
        }
        return accountRepository.save(Account.builder()
                .userId(userId)
                .balance(BigDecimal.ZERO)
                .build());
    }

    @Transactional
    public Account topUpAccount(Long userId, BigDecimal amount) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for user " + userId));

        account.setBalance(account.getBalance().add(amount));
        return account;
    }

    public BigDecimal getBalance(Long userId) {
        return accountRepository.findByUserId(userId)
                .map(Account::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for user " + userId));
    }

    @Transactional
    public boolean withdrawIfSufficient(Long userId, BigDecimal amount) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for user " + userId));

        if (account.getBalance().compareTo(amount) >= 0) {
            account.setBalance(account.getBalance().subtract(amount));
            return true;
        }

        return false;
    }
}
