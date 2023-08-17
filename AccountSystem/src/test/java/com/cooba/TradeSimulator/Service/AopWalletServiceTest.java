package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.AOP.LockAspect;
import com.cooba.TradeSimulator.Config.Configuration;
import com.cooba.TradeSimulator.DataLayer.WalletDataAccess;
import com.cooba.TradeSimulator.Enum.DefaultCurrency;
import com.cooba.TradeSimulator.Exception.InsufficientException;
import com.cooba.TradeSimulator.Object.asset.StockInfoAsset;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {Configuration.class, UserWalletService.class, LockAspect.class, LockFactory.class, ReentrantLockService.class})
class AopWalletServiceTest {
    @Autowired
    WalletService walletService;
    @Autowired
    WalletDataAccess walletDataAccess;

    @Test
    void assetAddStockWallet() throws InsufficientException {
        StockInfoAsset asset = StockInfoAsset.builder()
                .stockId(DefaultCurrency.TWD.getId())
                .amount(BigDecimal.valueOf(1000))
                .build();

        for (int i = 0; i < 5; i++) {
            walletService.assetChange(1, asset, true);
        }

        Optional<StockInfoAsset> wallet = walletDataAccess.selectStockAsset(1, 1);
        assertTrue(wallet.isPresent());
        BigDecimal expectedAmount = new BigDecimal(5000);
        BigDecimal resultAmount = wallet.get().getAmount();
        System.out.println(resultAmount);
        assertEquals(0, expectedAmount.compareTo(resultAmount));
    }
}