package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.AccountDB;
import com.cooba.TradeSimulator.Entity.Account;
import com.cooba.TradeSimulator.Exception.InsufficientException;
import com.cooba.TradeSimulator.Exception.NotExistException;
import com.cooba.TradeSimulator.Object.AccountDto;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Service.Interface.AccountService;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountService implements AccountService {
    @Autowired
    private AccountDB accountDB;
    @Autowired
    private WalletService walletService;

    @Override
    public String createAccount(String name) {
        String uuid = UUID.randomUUID().toString();
        Account account = Account.builder()
                .name(name)
                .uuid(uuid)
                .build();
        accountDB.insert(account);
        return uuid;
    }

    @Override
    public void updateAccountIfExist(String uuid, String name) {
        Optional<Account> accountOptional = accountDB.select(uuid);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setName(name);
            accountDB.update(account);
        }
    }

    @Override
    public void deleteAccountIfExist(String uuid) {
        Optional<Account> accountOptional = accountDB.select(uuid);
        accountOptional.ifPresent(account -> accountDB.delete(uuid));
    }

    @Override
    public Optional<AccountDto> getAccount(String uuid) {
        Optional<Account> accountOptional = accountDB.select(uuid);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return Optional.of(AccountDto.builder()
                    .uuid(account.getUuid())
                    .name(account.getName())
                    .password(account.getPassword())
                    .wallets(walletService.getWallets(account.getId()))
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public Optional<AccountDto> getAccount(Integer userId) {
        Optional<Account> accountOptional = accountDB.selectById(userId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return Optional.of(AccountDto.builder()
                    .uuid(account.getUuid())
                    .name(account.getName())
                    .password(account.getPassword())
                    .wallets(walletService.getWallets(account.getId()))
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public void checkAccount(Integer userId) throws NotExistException {
        if (getAccount(userId).isEmpty()) throw new NotExistException();
    }

    @Override
    public void deposit(Integer userId, Integer currencyId, BigDecimal amount) throws NotExistException {
        checkAccount(userId);

        CurrencyAsset currencyAsset = CurrencyAsset.builder()
                .currencyId(currencyId)
                .amount(amount)
                .build();
        try {
            walletService.assetChange(userId, currencyAsset, true);
        } catch (InsufficientException ignored) {
        }
    }

    @Override
    public void withdraw(Integer userId, Integer currencyId, BigDecimal amount) throws InsufficientException, NotExistException {
        checkAccount(userId);

        CurrencyAsset currencyAsset = CurrencyAsset.builder()
                .currencyId(currencyId)
                .amount(amount)
                .build();
        walletService.assetChange(userId, currencyAsset, false);
    }
}
