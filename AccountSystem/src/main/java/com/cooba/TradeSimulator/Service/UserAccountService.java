package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.AccountDataAccess;
import com.cooba.TradeSimulator.Entity.Account;
import com.cooba.TradeSimulator.Service.Interface.AccountService;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountService implements AccountService {
    @Autowired
    AccountDataAccess accountDataAccess;

    @Override
    public void createAccount(String name) {
        Account account = Account.builder()
                .name(name)
                .uuid(UUID.randomUUID().toString())
                .build();
        accountDataAccess.insertAccount(account);
    }

    @Override
    public void updateAccountIfExist(String uuid, String name) {
        Optional<Account> accountOptional = accountDataAccess.selectAccount(uuid);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setName(name);
            accountDataAccess.updateAccount(account);
        }
    }

    @Override
    public void deleteAccountIfExist(String uuid) {
        Optional<Account> accountOptional = accountDataAccess.selectAccount(uuid);
        accountOptional.ifPresent(account -> accountDataAccess.deleteAccount(uuid));
    }

    @Override
    public com.cooba.TradeSimulator.Object.Account showAccount(String uuid) {
        return null;
    }
}
