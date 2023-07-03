package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Object.Account;

public interface AccountService {
    void createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Account account);
}
