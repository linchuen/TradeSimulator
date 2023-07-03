package com.cooba.TradeSimulator;

import com.cooba.TradeSimulator.Mapper.AccountMapper;
import com.cooba.TradeSimulator.Object.Account;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

@Disabled
@Sql("/db/account-schema.sql")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountMapperTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void CRUDTest() {
        Account account = accountMapper.selectAccountById(Account.builder().id(3L).build());
        assert account == null : "資料庫應為空";

        String uuid = UUID.randomUUID().toString();
        Account newAccount = Account.builder().uuid(uuid).name("Aiden").build();
        int result = accountMapper.insertAccount(List.of(newAccount));
        assert result == 1 : "插入失敗";

        List<Account> accounts = accountMapper.selectAccount(Account.builder().name("Aiden").build());
        assert accounts.get(0).getName().equals("Aiden") : "一般查詢失敗";

        Long id = accounts.get(0).getId();
        accountMapper.deleteAccount(accounts.get(0));
        Account emptyAccount = accountMapper.selectAccountById(Account.builder().id(id).build());
        assert emptyAccount == null : "刪除失敗";
    }
}
