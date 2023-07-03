package com.cooba.TradeSimulator.DataAccess;

import com.cooba.TradeSimulator.Object.Account;
import com.cooba.TradeSimulator.Object.wallet.Wallet;


public interface WalletDataAccess {

    Wallet getWallet(Account account);

    void updateWallet(Wallet wallet);
}
