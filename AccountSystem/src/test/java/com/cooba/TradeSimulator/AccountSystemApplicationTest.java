package com.cooba.TradeSimulator;

import com.cooba.TradeSimulator.Service.Interface.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountSystemApplicationTest {
    @Autowired
    WalletService walletService;

    @Test
    public void context(){
        walletService.getWallets(1);
    }
}
