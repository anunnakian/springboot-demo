package com.example.demo.domain.model;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccountTest {

    @Test
    void shouldAddBalance() {
        Account account = new Account();
        account.setIban("FR12345");
        account.setBalance(BigDecimal.valueOf(10));

        account.deposit(BigDecimal.valueOf(20.50));

        assertThat(account.getBalance(), equalTo(BigDecimal.valueOf(30.50)));
    }
}
