package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Exception.InsufficientBalanceException;
import com.cooba.TradeSimulator.Object.Asset;
import com.cooba.TradeSimulator.Object.Wallet;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Object.wallet.CurrencyWallet;

import java.math.BigDecimal;

public interface WalletService {
    void deposit(Integer userId, Integer currencyId, BigDecimal amount);

    void withdraw(Integer userId, Integer currencyId, BigDecimal amount) throws InsufficientBalanceException;

    void showAsset(Wallet wallet);
}
