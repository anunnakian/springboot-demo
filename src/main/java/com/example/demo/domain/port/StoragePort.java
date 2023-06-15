package com.example.demo.domain.port;

import com.example.demo.domain.model.Account;

import java.util.Optional;

public interface StoragePort {

    boolean saveAccount(Account account);

    Optional<Account> findAccount(String iban);
}
