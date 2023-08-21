package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Config.Configuration;
import com.cooba.TradeSimulator.DataLayer.WalletDB;
import com.cooba.TradeSimulator.Enum.DefaultCurrency;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Object.asset.StockInfoAsset;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import com.cooba.TradeSimulator.Util.SimulateMultiThread;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {Configuration.class, UserWalletService.class})
class MultiThreadsWalletServiceTest {
    @Autowired
    WalletService walletService;
    @Autowired
    WalletDB walletDB;

    @Test
    void assetAddStockWallet() {
        StockInfoAsset asset = StockInfoAsset.builder()
                .stockId(DefaultCurrency.TWD.getId())
                .amount(BigDecimal.valueOf(1000))
                .build();

        SimulateMultiThread simulateMultiThread = new SimulateMultiThread("",3000,1000);
        simulateMultiThread.runWithLock(5,
                () -> walletService.assetChange(1, asset, true));

        Optional<StockInfoAsset> wallet = walletDB.selectStockAsset(1, 1);
        assertTrue(wallet.isPresent());
        BigDecimal expectedAmount = new BigDecimal(5000);
        BigDecimal resultAmount = wallet.get().getAmount();
        System.out.println(resultAmount);
        assertEquals(0, expectedAmount.compareTo(resultAmount));
    }

    @Test
    void assetMinusStockWallet() {
        CurrencyAsset asset = CurrencyAsset.builder()
                .currencyId(DefaultCurrency.TWD.getId())
                .amount(BigDecimal.valueOf(1))
                .build();

        SimulateMultiThread simulateMultiThread = new SimulateMultiThread("",3000,1000);
        simulateMultiThread.runWithLock(100,
                () -> walletService.assetChange(1, asset, false));

        Optional<CurrencyAsset> wallet = walletDB.selectCurrencyAsset(1, 1);
        assertTrue(wallet.isPresent());
        BigDecimal expectedAmount = new BigDecimal(0);
        BigDecimal resultAmount = wallet.get().getAmount();
        System.out.println(resultAmount);
        assertEquals(0, expectedAmount.compareTo(resultAmount));
    }
}