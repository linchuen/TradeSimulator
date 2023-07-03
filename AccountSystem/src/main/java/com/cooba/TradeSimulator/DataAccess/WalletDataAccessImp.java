package com.cooba.TradeSimulator.DataAccess;

import com.cooba.TradeSimulator.Object.Account;
import com.cooba.TradeSimulator.Object.wallet.Wallet;
import org.springframework.stereotype.Repository;

@Repository
public class WalletDataAccessImp implements WalletDataAccess {
    @Override
    public Wallet getWallet(Account account) {
        return null;
    }

    @Override
    public void updateWallet(Wallet wallet) {

    }
}
