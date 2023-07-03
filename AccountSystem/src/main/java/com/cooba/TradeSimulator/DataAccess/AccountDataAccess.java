package com.cooba.TradeSimulator.DataAccess;


import com.cooba.TradeSimulator.Object.Account;

import java.util.List;

public interface AccountDataAccess {
    void saveAll(List<Account> accounts);
    void updateAccount(Account account);
    List<Account> find(Account account);
    Account selectAccountById(Account account);
    void delete(Account account);
}
