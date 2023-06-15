package com.example.demo.api;

import com.example.demo.domain.model.Account;
import com.example.demo.domain.port.StoragePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountResource.class)
public class AccountResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoragePort storagePort;

    @Test
    public void shouldGetAccount() throws Exception {
        Account account = new Account();
        account.setIban("FR123");
        account.setBalance(BigDecimal.valueOf(30));
        when(storagePort.findAccount("FR123")).thenReturn(Optional.of(account));

        this.mockMvc.perform(get("/api/v1/account/FR123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("{\"iban\":\"FR123\",\"balance\":30,\"statements\":[]}")));
    }
}