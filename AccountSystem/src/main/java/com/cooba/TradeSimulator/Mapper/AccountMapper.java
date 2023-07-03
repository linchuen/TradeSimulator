package com.cooba.TradeSimulator.Mapper;


import com.cooba.TradeSimulator.Object.Account;

import java.util.List;

public interface AccountMapper {
    int insertAccount(List<Account> accounts);
    int updateAccount(Account account);
    List<Account> selectAccount(Account account);
    Account selectAccountById(Account account);
    int deleteAccount(Account account);
}
