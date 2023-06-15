package com.example.demo.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {

    private String iban;
    private BigDecimal balance;
}
