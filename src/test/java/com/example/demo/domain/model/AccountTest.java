package com.example.demo.domain.model;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountTest {

    @Test
    void shouldAddStatement() {
        Account account = new Account();
        account.setIban("FR12345");
        account.setBalance(BigDecimal.valueOf(10));

        account.deposit(BigDecimal.valueOf(20.50));

        assertThat(account.getBalance(), equalTo(BigDecimal.valueOf(30.50)));
        assertNotNull(account.getStatements().get(0).getDate());
        assertThat(account.getStatements().get(0).getBalance(), equalTo(BigDecimal.valueOf(10)));
        assertThat(account.getStatements().get(0).getAmount(), equalTo(BigDecimal.valueOf(20.50)));
    }

    @Test
    void shouldAddAnotherStatement() {
        Account account = new Account();
        account.setIban("FR12345");
        account.setBalance(BigDecimal.valueOf(10));
        account.getStatements().add(getAccountStatement());

        account.deposit(BigDecimal.valueOf(20.50));

        assertThat(account.getBalance(), equalTo(BigDecimal.valueOf(30.50)));
        assertNotNull(account.getStatements().get(1).getDate());
        assertThat(account.getStatements().get(1).getBalance(), equalTo(BigDecimal.valueOf(10)));
        assertThat(account.getStatements().get(1).getAmount(), equalTo(BigDecimal.valueOf(20.50)));
    }

    private AccountStatement getAccountStatement() {
        AccountStatement accountStatement = new AccountStatement();
        accountStatement.setDate(ZonedDateTime.now());
        accountStatement.setAmount(BigDecimal.valueOf(10));
        accountStatement.setBalance(BigDecimal.ZERO);

        return accountStatement;
    }
}
