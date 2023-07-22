package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.WalletDataAccess;
import com.cooba.TradeSimulator.Exception.InsufficientBalanceException;
import com.cooba.TradeSimulator.Object.Asset;
import com.cooba.TradeSimulator.Object.wallet.Wallet;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import com.cooba.TradeSimulator.Util.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserWalletService implements WalletService {
    @Autowired
    private DistributedLock lock;
    @Autowired
    private WalletDataAccess walletDataAccess;

    @Override
    public void deposit(Wallet wallet, Asset asset) {
        lock.getLock();

        try {
            lock.lock();
            Map<String, Asset> assetMap = wallet.getAssets();

            assetMap.compute(asset.getType(), (k, a) -> {
                if (a == null) {
                    return asset;
                } else {
                    a.setAmount(a.getAmount() + asset.getAmount());
                    return a;
                }
            });
            walletDataAccess.save(wallet);
        } catch (Exception ignored) {

        } finally {
            lock.unlock();
        }
    }

    @Override
    public void withdraw(Wallet wallet, Asset asset) throws InsufficientBalanceException {
        lock.getLock();

        try {
            lock.lock();
            Map<String, Asset> assetMap = wallet.getAssets();


            Asset walletAsset = assetMap.get(asset.getType());
            if (walletAsset == null) {
                throw new InsufficientBalanceException(asset.getType());
            }

            if (walletAsset.getAmount() < asset.getAmount()) {
                throw new InsufficientBalanceException(asset.getType());
            }
            walletAsset.setAmount(walletAsset.getAmount() - asset.getAmount());
            assetMap.put(asset.getType(), walletAsset);
            walletDataAccess.updateWallet(wallet);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void showAsset(Wallet wallet) {
        Map<String, Asset> assetMap = wallet.getAssets();
        for (Map.Entry<String, Asset> entry : assetMap.entrySet()) {
            Asset asset = entry.getValue();

            System.out.println(asset.getType() + ":" + asset.getAmount());
        }
    }
}
