package com.cooba.TradeSimulator.DataLayer;


import com.cooba.TradeSimulator.Entity.Account;
import com.cooba.TradeSimulator.Mapper.AccountDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.AccountMapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;


@Service
public class AccountDataAccess {
    @Autowired
    private AccountMapper accountMapper;

    public boolean insertAccount(Account account) {
        account.setIsEnable(true);
        account.setCreatedTime(LocalDateTime.now());
        account.setUpdatedTime(LocalDateTime.now());
        return accountMapper.insert(account) == 1;
    }

    public boolean updateAccount(Account account) {
        UpdateStatementProvider query = SqlBuilder.update(AccountDynamicSqlSupport.account)
                .set(AccountDynamicSqlSupport.name).equalTo(account.getName())
                .set(AccountDynamicSqlSupport.updatedTime).equalTo(LocalDateTime.now())
                .where(AccountDynamicSqlSupport.uuid, isEqualTo(account.getUuid()))
                .build().render(RenderingStrategies.MYBATIS3);
        return accountMapper.update(query) == 1;
    }

    public boolean deleteAccount(String uuid) {
        DeleteStatementProvider query = SqlBuilder.deleteFrom(AccountDynamicSqlSupport.account)
                .where(AccountDynamicSqlSupport.uuid, isEqualTo(uuid))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return accountMapper.delete(query) == 1;
    }

    public Optional<Account> selectAccount(String uuid) {
        SelectStatementProvider query = SqlBuilder.select(AccountMapper.selectList)
                .from(AccountDynamicSqlSupport.account)
                .where(AccountDynamicSqlSupport.uuid, isEqualTo(uuid))
                .build().render(RenderingStrategies.MYBATIS3);
        return accountMapper.selectOne(query);
    }

    public Optional<Account> selectById(int userId) {
        return accountMapper.selectByPrimaryKey(userId);
    }

    public Optional<Account> selectDetailAccount(String uuid) {
        SelectStatementProvider query = SqlBuilder.select(AccountMapper.selectList)
                .from(AccountDynamicSqlSupport.account)
                .where(AccountDynamicSqlSupport.uuid, isEqualTo(uuid))
                .build().render(RenderingStrategies.MYBATIS3);
        return accountMapper.selectOne(query);
    }
}
