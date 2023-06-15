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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    public static final String IBAN = "FR12345";
    @InjectMocks
    private AccountService accountService;
    @Mock
    private StoragePort storagePort;

    @Test
    void shouldDepositAnAmount() {
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(30));
        account.setIban(IBAN);
        when(storagePort.findAccount(IBAN)).thenReturn(Optional.of(account));

        accountService.deposit(IBAN, BigDecimal.valueOf(30.50));

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        verify(storagePort, times(1)).saveAccount(captor.capture());
        assertThat(captor.getValue().getIban(), CoreMatchers.equalTo(IBAN));
        assertThat(captor.getValue().getBalance(), CoreMatchers.equalTo(BigDecimal.valueOf(60.50)));
        assertNotNull(captor.getValue().getStatements().get(0).getDate());
        assertThat(captor.getValue().getStatements().get(0).getBalance(), equalTo(BigDecimal.valueOf(60.50)));
        assertThat(captor.getValue().getStatements().get(0).getAmount(), equalTo(BigDecimal.valueOf(30.50)));
    }
}
