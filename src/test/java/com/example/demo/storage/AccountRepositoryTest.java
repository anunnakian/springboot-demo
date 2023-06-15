package com.example.demo.storage;

import com.example.demo.domain.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

public class AccountRepositoryTest {

    private AccountRepository accountRepository = new AccountRepository();

    @BeforeEach
    void setup() {
        Account account = new Account();
        account.setIban("FR123");
        account.setBalance(BigDecimal.ZERO);
        accountRepository.addAccount(account);
    }

    @Test
    void shouldReturnAccount() {
        Optional<Account> account = accountRepository.findAccount("FR123");

        Assertions.assertTrue(account.isPresent());
    }
}
