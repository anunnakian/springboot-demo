package com.example.demo.domain.service;

import com.example.demo.domain.AccountService;
import com.example.demo.domain.model.Account;
import com.example.demo.domain.port.StoragePort;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;
    @Mock
    private StoragePort storagePort;

    @Test
    void shouldDepositAnAmount() {
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(30));
        account.setIban("FR12345");
        when(storagePort.findAccount("FR12345")).thenReturn(Optional.of(account));

        accountService.deposit("FR12345", BigDecimal.valueOf(30.50));

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        verify(storagePort, times(1)).saveAccount(captor.capture());
        assertThat(captor.getValue().getIban(), CoreMatchers.equalTo("FR12345"));
        assertThat(captor.getValue().getBalance(), CoreMatchers.equalTo(BigDecimal.valueOf(60.50)));
    }
}
