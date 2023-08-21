package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Exception.InsufficientException;
import com.cooba.TradeSimulator.Exception.NotSupportCurrencyException;
import com.cooba.TradeSimulator.Object.asset.Asset;
import com.cooba.TradeSimulator.Object.wallet.Wallet;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;

import java.util.List;

public interface WalletService {

    List<Wallet> getWallets(Integer userId);

    CurrencyAsset assessAssetByUnit(Integer userId, Integer currencyId) throws NotSupportCurrencyException;

    CurrencyAsset exchange(Asset input, Integer currencyId) throws NotSupportCurrencyException;

    void assetChange(Integer userId, Asset asset, boolean isPlus) throws InsufficientException;
}
