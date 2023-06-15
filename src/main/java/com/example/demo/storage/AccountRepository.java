package com.example.demo.storage;

import com.example.demo.domain.model.Account;
import com.example.demo.domain.port.StoragePort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountRepository implements StoragePort {

    private static List<Account> DATABASE = new ArrayList<>();

    static {
        Account account = new Account();
        account.setIban("FR123");
        account.setBalance(BigDecimal.ZERO);
        DATABASE.add(account);
    }

    @Override
    public boolean saveAccount(Account account) {
        return false;
    }

    @Override
    public Optional<Account> findAccount(String iban) {
        return DATABASE.stream().filter(a -> a.getIban().equals(iban)).findFirst();
    }
}
