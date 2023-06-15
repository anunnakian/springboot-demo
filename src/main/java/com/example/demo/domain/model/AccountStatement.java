package com.example.demo.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class AccountStatement {

    private Operation operation;
    private ZonedDateTime date;
    private BigDecimal amount;
    private BigDecimal balance;
}
