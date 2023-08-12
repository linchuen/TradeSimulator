package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Exception.InsufficientException;
import com.cooba.TradeSimulator.Exception.NotExistException;
import com.cooba.TradeSimulator.Object.AccountDto;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    String createAccount(String name);

    void updateAccountIfExist(String uuid, String name);

    void deleteAccountIfExist(String uuid);

    Optional<AccountDto> getAccount(String uuid);

    Optional<AccountDto> getAccount(Integer userId);

    void checkAccount(Integer userId) throws NotExistException;

    void deposit(Integer userId, Integer currencyId, BigDecimal amount) throws NotExistException;

    void withdraw(Integer userId, Integer currencyId, BigDecimal amount) throws InsufficientException, NotExistException;
}
