package com.example.demo.domain;

import com.example.demo.domain.exception.AccountNotFoundException;
import com.example.demo.domain.exception.InsufficientFundsException;
import com.example.demo.domain.model.Account;
import com.example.demo.domain.model.AccountStatement;
import com.example.demo.domain.model.Operation;
import com.example.demo.domain.port.StoragePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private StoragePort storagePort;

    public Account deposit(String iban, BigDecimal amount) {
        Optional<Account> accountOptional = storagePort.findAccount(iban);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException();
        }

        Account account = accountOptional.get();
        account.deposit(amount);

        storagePort.saveAccount(account);

        return account;
    }

    public Account withdraw(String iban, BigDecimal amount) {
        Optional<Account> accountOptional = storagePort.findAccount(iban);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException();
        }

        Account account = accountOptional.get();

        if (insufficientFunds(amount, account)) {
            throw new InsufficientFundsException();
        }

        AccountStatement accountStatement = new AccountStatement();
        accountStatement.setOperation(Operation.WITHDRAW);
        accountStatement.setAmount(amount);
        accountStatement.setBalance(account.getBalance());
        accountStatement.setDate(ZonedDateTime.now());

        account.getStatements().add(accountStatement);
        account.setBalance(account.getBalance().subtract(amount));

        storagePort.saveAccount(account);

        return account;
    }

    private static boolean insufficientFunds(BigDecimal amount, Account account) {
        return amount.compareTo(account.getBalance()) > 0;
    }
}
