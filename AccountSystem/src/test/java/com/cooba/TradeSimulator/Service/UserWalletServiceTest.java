package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Config.Configuration;
import com.cooba.TradeSimulator.DataLayer.WalletDB;
import com.cooba.TradeSimulator.Enum.DefaultCurrency;
import com.cooba.TradeSimulator.Exception.InsufficientException;
import com.cooba.TradeSimulator.Exception.NotSupportCurrencyException;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Object.asset.StockInfoAsset;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
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
class UserWalletServiceTest {
    @Autowired
    WalletService walletService;
    @Autowired
    WalletDB walletDB;

    @Test
    void exchange() throws NotSupportCurrencyException {
        CurrencyAsset input = CurrencyAsset.builder()
                .currencyId(DefaultCurrency.USD.getId())
                .currencyName(DefaultCurrency.USD.getName())
                .amount(BigDecimal.valueOf(1000))
                .build();

        CurrencyAsset output = walletService.exchange(input, DefaultCurrency.TWD.getId());
        assertEquals(0, BigDecimal.valueOf(31000).compareTo(output.getAmount()));
    }

    @Test
    void assetAddStockWallet() throws InsufficientException {
        StockInfoAsset asset = StockInfoAsset.builder()
                .stockId(DefaultCurrency.TWD.getId())
                .amount(BigDecimal.valueOf(1000))
                .build();
        walletService.assetChange(1, asset, true);

        Optional<StockInfoAsset> wallet = walletDB.selectStockAsset(1, 1);
        assertTrue(wallet.isPresent());
        BigDecimal expectedAmount = new BigDecimal(1000);
        assertEquals(0, expectedAmount.compareTo(wallet.get().getAmount()));
    }

    @Test
    void assetMinusStockWallet() {
        StockInfoAsset asset = StockInfoAsset.builder()
                .stockId(DefaultCurrency.TWD.getId())
                .amount(BigDecimal.valueOf(1000))
                .build();

        assertThrows(InsufficientException.class, () -> walletService.assetChange(1, asset, false));
    }
}