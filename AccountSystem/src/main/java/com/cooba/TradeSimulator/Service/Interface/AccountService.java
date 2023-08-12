package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Object.AccountDto;

import java.util.Optional;

public interface AccountService {
    String createAccount(String name);

    void updateAccountIfExist(String uuid, String name);

    void deleteAccountIfExist(String uuid);

    Optional<AccountDto> getAccount(String uuid);
}
