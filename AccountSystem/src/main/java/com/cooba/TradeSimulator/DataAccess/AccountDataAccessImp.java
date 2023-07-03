package com.cooba.TradeSimulator.DataAccess;


import com.cooba.TradeSimulator.Mapper.AccountMapper;
import com.cooba.TradeSimulator.Object.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountDataAccessImp implements AccountDataAccess {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void saveAll(List<Account> accounts) {
        accountMapper.insertAccount(accounts);
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public List<Account> find(Account account) {
        return null;
    }

    @Override
    public Account selectAccountById(Account account) {
        return null;
    }

    @Override
    public void delete(Account account) {

    }
}
