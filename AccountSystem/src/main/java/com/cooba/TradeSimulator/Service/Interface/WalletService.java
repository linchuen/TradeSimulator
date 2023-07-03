package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Exception.InsufficientBalanceException;
import com.cooba.TradeSimulator.Object.Asset;
import com.cooba.TradeSimulator.Object.wallet.Wallet;

public interface WalletService {
    void deposit(Wallet wallet, Asset asset);
    void withdraw(Wallet wallet, Asset asset) throws InsufficientBalanceException;
    void showAsset(Wallet wallet);
}
