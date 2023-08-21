package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.AccountDB;
import com.cooba.TradeSimulator.DataLayer.WalletDB;
import com.cooba.TradeSimulator.Entity.Account;
import com.cooba.TradeSimulator.Exception.InsufficientException;
import com.cooba.TradeSimulator.Exception.NotExistException;
import com.cooba.TradeSimulator.Object.AccountDto;
import com.cooba.TradeSimulator.Config.Configuration;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Service.Interface.AccountService;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {Configuration.class, UserAccountService.class, AccountDB.class, UserWalletService.class})
class UserAccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountDB accountDB;
    @Autowired
    private WalletService walletService;

    @Test
    void createAccount() {
        String uuid = accountService.createAccount("Aiden");
        System.out.println(uuid);
        assertNotNull(uuid);

        Optional<Account> accountOptional = accountDB.selectAccount(uuid);
        assertTrue(accountOptional.isPresent());
        assertEquals("Aiden", accountOptional.get().getName());
    }

    @Test
    void updateAccountIfExist() {
        String uuid = accountService.createAccount("Aiden");
        accountService.updateAccountIfExist(uuid, "new name");

        Optional<Account> accountOptional = accountDB.selectAccount(uuid);
        assertTrue(accountOptional.isPresent());
        assertEquals("new name", accountOptional.get().getName());
    }

    @Test
    void deleteAccountIfExist() {
        String uuid = accountService.createAccount("Aiden");
        Optional<Account> accountOptional = accountDB.selectAccount(uuid);
        assertTrue(accountOptional.isPresent());

        accountService.deleteAccountIfExist(uuid);
        Optional<Account> afterDelete = accountDB.selectAccount(uuid);
        assertFalse(afterDelete.isPresent());
    }

    @Test
    void getAccount() {
        String uuid = accountService.createAccount("Aiden");
        Optional<AccountDto> accountDto = accountService.getAccount(uuid);
        assertTrue(accountDto.isPresent());
        System.out.println(accountDto.get());
    }

    @Test
    void checkAccount() {
        assertThrows(NotExistException.class, () -> accountService.checkAccount(0));
    }

    @Test
    void depositDefaultAccount(@Autowired WalletDB walletDB) throws NotExistException {
        accountService.deposit(1, 1, BigDecimal.TEN);

        Optional<CurrencyAsset> wallet = walletDB.selectCurrencyAsset(1, 1);
        assertTrue(wallet.isPresent());
        BigDecimal expectedAmount = new BigDecimal(110);
        assertEquals(0, expectedAmount.compareTo(wallet.get().getAmount()));
    }

    @Test
    void depositNotExistAccount() {
        assertThrows(NotExistException.class, () -> accountService.deposit(2, 1, BigDecimal.TEN));
    }

    @Test
    void withdrawDefaultAccount(@Autowired WalletDB walletDB) throws NotExistException, InsufficientException {
        accountService.withdraw(1, 1, BigDecimal.TEN);

        Optional<CurrencyAsset> wallet = walletDB.selectCurrencyAsset(1, 1);
        assertTrue(wallet.isPresent());
        BigDecimal expectedAmount = new BigDecimal(90);
        assertEquals(0, expectedAmount.compareTo(wallet.get().getAmount()));
    }

    @Test
    void withdrawNotExistAccount() {
        assertThrows(NotExistException.class, () -> accountService.withdraw(2, 1, BigDecimal.TEN));
    }
}