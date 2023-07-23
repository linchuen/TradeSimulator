package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.WalletDataAccess;
import com.cooba.TradeSimulator.Exception.InsufficientBalanceException;
import com.cooba.TradeSimulator.Object.Asset;
import com.cooba.TradeSimulator.Object.Wallet;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Object.wallet.CurrencyWallet;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import com.cooba.TradeSimulator.Util.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
public class UserWalletService implements WalletService {
    @Autowired
    private WalletDataAccess walletDataAccess;

    @Override
    public void deposit(Integer userId, Integer currencyId, BigDecimal amount) {
        Optional<CurrencyAsset> currencyWalletOptional = walletDataAccess.selectCurrencyAsset(userId, currencyId);
        if (currencyWalletOptional.isEmpty()) {
            CurrencyAsset currencyAsset = CurrencyAsset.builder()
                    .currencyId(currencyId)
                    .amount(BigDecimal.ZERO).build();
            walletDataAccess.insertWallet(userId, currencyAsset);
        } else {
            CurrencyAsset currencyAsset = currencyWalletOptional.get();
            currencyAsset.setAmount(currencyAsset.getAmount().add(amount));
            walletDataAccess.updateAssetAmount(userId, currencyAsset);
        }
    }

    @Override
    public void withdraw(Integer userId, Integer currencyId, BigDecimal amount) throws InsufficientBalanceException {
        Optional<CurrencyAsset> currencyWalletOptional = walletDataAccess.selectCurrencyAsset(userId, currencyId);
        if (currencyWalletOptional.isEmpty()) {
            CurrencyAsset currencyAsset = CurrencyAsset.builder()
                    .currencyId(currencyId)
                    .amount(BigDecimal.ZERO).build();
            walletDataAccess.insertWallet(userId, currencyAsset);
            throw new InsufficientBalanceException();
        } else {
            CurrencyAsset currencyAsset = currencyWalletOptional.get();
            BigDecimal result = currencyAsset.getAmount().subtract(amount);
            if (result.compareTo(BigDecimal.ZERO) < 0) throw new InsufficientBalanceException();
            currencyAsset.setAmount(result);
            walletDataAccess.updateAssetAmount(userId, currencyAsset);
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
