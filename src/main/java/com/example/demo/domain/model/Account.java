package com.example.demo.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {

    private String iban;
    private BigDecimal balance;

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }
}
