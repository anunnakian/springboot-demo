package com.example.demo.storage;

import com.example.demo.domain.AccountService;
import com.example.demo.domain.model.Account;
import com.example.demo.domain.port.StoragePort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountRepository implements StoragePort {

    private List<Account> database = new ArrayList<>();

    @Override
    public boolean saveAccount(Account account) {
        return false;
    }

    @Override
    public Optional<Account> findAccount(String iban) {
        return database.stream().filter(a -> a.getIban().equals(iban)).findFirst();
    }

    public void addAccount(Account account) {
        database.add(account);
    }
}
