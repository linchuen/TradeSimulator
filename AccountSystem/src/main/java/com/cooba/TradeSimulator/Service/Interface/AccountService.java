package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Object.Account;

public interface AccountService {
    void createAccount(String name);

    void updateAccountIfExist(String uuid, String name);

    void deleteAccountIfExist(String uuid);

    Account showAccount(String uuid);
}
