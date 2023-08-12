package com.cooba.TradeSimulator.Service;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.context.annotation.Configurations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {Configurations.class,UserWalletService.class})
class UserWalletServiceTest {

    @Test
    void deposit() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void getWallets() {
    }

    @Test
    void assessAssetByUnit() {
    }

    @Test
    void exchange() {
    }

    @Test
    void assetChange() {
    }
}