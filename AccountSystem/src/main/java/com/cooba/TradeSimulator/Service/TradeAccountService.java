package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.AccountDataAccess;
import com.cooba.TradeSimulator.Object.Account;
import com.cooba.TradeSimulator.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TradeAccountService implements AccountService {
    @Autowired
    private AccountDataAccess accountDataAccess;

    @Override
    public void createAccount(Account account) {
        UUID uuid = UUID.randomUUID();
        account.setUuid(uuid.toString());
        accountDataAccess.saveAll(List.of(account));
    }

    @Override
    public void updateAccount(Account account) {
        accountDataAccess.updateAccount(account);
    }

    @Override
    public void deleteAccount(Account account) {
        accountDataAccess.delete(account);
    }
}
