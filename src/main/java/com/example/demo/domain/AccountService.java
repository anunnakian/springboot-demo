package com.example.demo.domain;

import com.example.demo.domain.exception.AccountNotFoundException;
import com.example.demo.domain.model.Account;
import com.example.demo.domain.port.StoragePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private StoragePort storagePort;

    public void deposit(String iban, BigDecimal amount) {
        Optional<Account> accountOptional = storagePort.findAccount(iban);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException();
        }

        Account account = accountOptional.get();
        account.deposit(amount);

        storagePort.saveAccount(account);
    }
}
