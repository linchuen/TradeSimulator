package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Config.Configuration;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Object.asset.StockInfoAsset;
import org.instancio.Instancio;
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
@ContextConfiguration(classes = {Configuration.class, WalletDB.class})
class WalletDBTest {
    @Autowired
    WalletDB walletDB;

    @Test
    void insertCurrencyWallet() {
        CurrencyAsset currencyAsset = Instancio.of(CurrencyAsset.class).create();
        boolean isSuccess = walletDB.insertWallet(2, currencyAsset);
        assertTrue(isSuccess);
    }

    @Test
    void insertStockWallet() {
        StockInfoAsset stockInfoAsset = Instancio.of(StockInfoAsset.class).create();
        boolean isSuccess = walletDB.insertWallet(2, stockInfoAsset);
        assertTrue(isSuccess);
    }

    @Test
    void updateCurrencyAssetAmount() {
        CurrencyAsset currencyAsset = CurrencyAsset.builder().currencyId(1).amount(BigDecimal.TEN).build();
        walletDB.updateAssetAmount(1, currencyAsset);

        Optional<CurrencyAsset> currencyAssetOptional = walletDB.selectCurrencyAsset(1, 1);
        assertTrue(currencyAssetOptional.isPresent());
        CurrencyAsset dbAsset = currencyAssetOptional.get();
        assertEquals(0, BigDecimal.TEN.compareTo(dbAsset.getAmount()));
    }

    @Test
    void updateStockAssetAmount() {
        StockInfoAsset stockInfoAsset = StockInfoAsset.builder().stockId(1).amount(BigDecimal.TEN).build();
        walletDB.updateAssetAmount(1, stockInfoAsset);

        Optional<StockInfoAsset> stockInfoAssetOptional = walletDB.selectStockAsset(1, 1);
        assertTrue(stockInfoAssetOptional.isPresent());
        StockInfoAsset sbAsset = stockInfoAssetOptional.get();
        assertEquals(0, BigDecimal.TEN.compareTo(stockInfoAsset.getAmount()));
    }

    @Test
    void selectDefaultCurrencyAsset() {
        Optional<CurrencyAsset> currencyAssetOptional = walletDB.selectCurrencyAsset(1, 1);
        assertTrue(currencyAssetOptional.isPresent());
        CurrencyAsset currencyAsset = currencyAssetOptional.get();
        assertEquals(0, BigDecimal.ZERO.compareTo(currencyAsset.getAmount()));
    }

    @Test
    void selectNotExistCurrencyAsset() {
        Optional<CurrencyAsset> currencyAssetOptional = walletDB.selectCurrencyAsset(-1, -1);
        assertTrue(currencyAssetOptional.isEmpty());
    }


    @Test
    void selectDefaultStockAsset() {
        Optional<StockInfoAsset> stockInfoAssetOptional = walletDB.selectStockAsset(1, 1);
        assertTrue(stockInfoAssetOptional.isPresent());
        StockInfoAsset stockInfoAsset = stockInfoAssetOptional.get();
        assertEquals(0, BigDecimal.ZERO.compareTo(stockInfoAsset.getAmount()));
    }

    @Test
    void selectNotExistStockAsset() {
        Optional<StockInfoAsset> stockInfoAssetOptional = walletDB.selectStockAsset(-1, -1);
        assertTrue(stockInfoAssetOptional.isEmpty());
    }

}