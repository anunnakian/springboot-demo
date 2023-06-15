package com.example.demo.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Account {

    private String iban;
    private BigDecimal balance;
    private List<AccountStatement> statements = new ArrayList<>();

    public void deposit(BigDecimal amount) {
        AccountStatement statement = new AccountStatement();
        statement.setDate(ZonedDateTime.now());
        statement.setAmount(amount);
        statement.setBalance(balance);
        statement.setOperation(Operation.DEPOSIT);

        balance = balance.add(amount);

        statements.add(statement);
    }
}
