package com.cooba.TradeSimulator.DataLayer;


import com.cooba.TradeSimulator.Mapper.AccountMapper;
import com.cooba.TradeSimulator.Object.Account;
import com.cooba.TradeSimulator.Request.AccountReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountDataAccess implements BaseMapper<Account, AccountReq> {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> find(AccountReq request) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public boolean save(Account entity) {
        return false;
    }

    @Override
    public boolean saveAll(List<Account> entities) {
        return false;
    }

    @Override
    public boolean insert(Account entity) {
        return false;
    }

    @Override
    public boolean insertAll(List<Account> entities) {
        return false;
    }

    @Override
    public boolean delete(AccountReq request) {
        return false;
    }

    @Override
    public boolean deleteAll(List<Account> entities) {
        return false;
    }
}
