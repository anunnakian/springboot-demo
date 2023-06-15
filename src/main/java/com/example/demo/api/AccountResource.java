package com.example.demo.api;

import com.example.demo.domain.AccountService;
import com.example.demo.domain.model.Account;
import com.example.demo.domain.port.StoragePort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class AccountResource {

    private StoragePort storagePort;
    private AccountService accountService;

    @GetMapping("/account/{iban}")
    public ResponseEntity<Account> getAccount(@PathVariable("iban") String iban) {
        Optional<Account> account = storagePort.findAccount(iban);

        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/account/{iban}")
    public Account deposit(
            @PathVariable("iban") String iban,
            @RequestParam("amount") BigDecimal amount) {

        return accountService.deposit(iban, amount);
    }
}
