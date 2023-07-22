package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Object.Asset;
import com.cooba.TradeSimulator.Object.wallet.Wallet;
import com.cooba.TradeSimulator.Service.Interface.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAssetService implements AssetService {
    @Autowired
    private UserWalletService userWalletService;

    @Override
    public void showAssets(List<Wallet> wallets) {
        for (Wallet wallet : wallets) {
            userWalletService.showAsset(wallet);
        }
    }

    @Override
    public void showAssetsByUnit(List<Wallet> wallets, Asset asset) {
        for (Wallet wallet : wallets) {
            userWalletService.showAsset(wallet);
        }
    }

    @Override
    public void exchange(Asset input, Asset output) {

    }
}
