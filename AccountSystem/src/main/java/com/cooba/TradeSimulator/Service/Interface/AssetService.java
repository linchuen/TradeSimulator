package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Object.Asset;
import com.cooba.TradeSimulator.Object.Wallet;

import java.util.List;

public interface AssetService {
    void assessAssetByUnit(List<Wallet> wallets, Asset asset);
    void exchange(Asset input, Asset output);
}
