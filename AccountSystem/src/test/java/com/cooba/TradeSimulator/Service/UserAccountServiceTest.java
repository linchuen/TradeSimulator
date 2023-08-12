package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.AccountDataAccess;
import com.cooba.TradeSimulator.Entity.Account;
import com.cooba.TradeSimulator.Object.AccountDto;
import com.cooba.TradeSimulator.Config.Configuration;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {Configuration.class, UserAccountService.class, AccountDataAccess.class, WalletService.class})
class UserAccountServiceTest {
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    private AccountDataAccess accountDataAccess;
    @MockBean
    private WalletService walletService;

    @Test
    void createAccount() {
        String uuid = userAccountService.createAccount("Aiden");
        System.out.println(uuid);
        assertNotNull(uuid);

        Optional<Account> accountOptional = accountDataAccess.selectAccount(uuid);
        assertTrue(accountOptional.isPresent());
        assertEquals("Aiden", accountOptional.get().getName());
    }

    @Test
    void updateAccountIfExist() {
        String uuid = userAccountService.createAccount("Aiden");
        userAccountService.updateAccountIfExist(uuid, "new name");

        Optional<Account> accountOptional = accountDataAccess.selectAccount(uuid);
        assertTrue(accountOptional.isPresent());
        assertEquals("new name", accountOptional.get().getName());
    }

    @Test
    void deleteAccountIfExist() {
        String uuid = userAccountService.createAccount("Aiden");
        Optional<Account> accountOptional = accountDataAccess.selectAccount(uuid);
        assertTrue(accountOptional.isPresent());

        userAccountService.deleteAccountIfExist(uuid);
        Optional<Account> afterDelete = accountDataAccess.selectAccount(uuid);
        assertFalse(afterDelete.isPresent());
    }

    @Test
    void getAccount() {
        String uuid = userAccountService.createAccount("Aiden");
        Optional<AccountDto> accountDto = userAccountService.getAccount(uuid);
        assertTrue(accountDto.isPresent());
        System.out.println(accountDto.get());
    }
}