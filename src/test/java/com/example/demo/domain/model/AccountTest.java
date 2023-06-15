package com.example.demo.domain.model;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountTest {

    @Test
    void shouldAddBalance() {
        Account account = new Account();
        account.setIban("FR12345");
        account.setBalance(BigDecimal.valueOf(10));

        account.deposit(BigDecimal.valueOf(20.50));

        assertThat(account.getBalance(), equalTo(BigDecimal.valueOf(30.50)));
        assertNotNull(account.getStatements().get(0).getDate());
        assertThat(account.getStatements().get(0).getBalance(), equalTo(BigDecimal.valueOf(30.50)));
        assertThat(account.getStatements().get(0).getAmount(), equalTo(BigDecimal.valueOf(20.50)));
    }
}
