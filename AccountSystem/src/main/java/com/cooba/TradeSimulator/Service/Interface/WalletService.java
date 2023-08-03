package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Exception.InsufficientBalanceException;
import com.cooba.TradeSimulator.Exception.InsufficientException;
import com.cooba.TradeSimulator.Exception.InsufficientStockException;
import com.cooba.TradeSimulator.Exception.NotSupportCurrencyException;
import com.cooba.TradeSimulator.Object.Asset;
import com.cooba.TradeSimulator.Object.Wallet;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
    void deposit(Integer userId, Integer currencyId, BigDecimal amount);

    void withdraw(Integer userId, Integer currencyId, BigDecimal amount) throws InsufficientException;

    List<Wallet> getWallets(Integer userId);

    CurrencyAsset assessAssetByUnit(Integer userId, Integer currencyId) throws NotSupportCurrencyException;

    CurrencyAsset exchange(Asset input, CurrencyAsset output) throws NotSupportCurrencyException;

    void assetChange(Integer userId, Asset asset,boolean isPlus) throws InsufficientException;
}
