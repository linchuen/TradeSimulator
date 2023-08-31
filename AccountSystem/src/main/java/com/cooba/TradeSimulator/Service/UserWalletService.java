package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Annotation.TransactionLock;
import com.cooba.TradeSimulator.DataLayer.CurrencyDB;
import com.cooba.TradeSimulator.DataLayer.WalletDB;
import com.cooba.TradeSimulator.Exception.InsufficientException;
import com.cooba.TradeSimulator.Object.asset.Asset;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Object.asset.StockInfoAsset;
import com.cooba.TradeSimulator.Object.wallet.CurrencyWallet;
import com.cooba.TradeSimulator.Object.wallet.StockWallet;
import com.cooba.TradeSimulator.Object.wallet.Wallet;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UserWalletService implements WalletService {
    @Autowired
    private WalletDB walletDB;
    @Autowired
    private CurrencyDB currencyDB;

    @Override
    public List<Wallet> getWallets(Integer userId) {
        List<CurrencyAsset> currencyAssetList = walletDB.selectCurrencyAssetList(userId);
        List<StockInfoAsset> stockAssetList = walletDB.selectStockAssetList(userId);
        CurrencyWallet currencyWallet = CurrencyWallet.builder().userId(userId).assets(currencyAssetList).build();
        StockWallet stockWallet = StockWallet.builder().userId(userId).assets(stockAssetList).build();
        return List.of(currencyWallet, stockWallet);
    }

    @Override
    public CurrencyAsset assessAssetByUnit(Integer userId, Integer currencyId) {
        List<Asset> totalAssets = new LinkedList<>();
        for (Wallet wallet : getWallets(userId)) {
            totalAssets.addAll(wallet.getAssets());
        }
        CurrencyAsset result = CurrencyAsset.builder().currencyId(currencyId).amount(BigDecimal.ZERO).build();
        for (Asset asset : totalAssets) {
            BigDecimal amount = asset.exchange(currencyId, currencyDB).getAmount();
            result.setAmount(result.getAmount().add(amount));
        }
        return result;
    }

    @Override
    public CurrencyAsset exchange(Asset input, Integer currencyId) {
        return (CurrencyAsset) input.exchange(currencyId, currencyDB);
    }

    @Override
    @TransactionLock
    public void assetChange(Integer userId, Asset asset, boolean isPlus) throws InsufficientException {
        if (asset instanceof CurrencyAsset) {
            int currencyId = ((CurrencyAsset) asset).getCurrencyId();
            assetChange(userId, asset, isPlus, () -> walletDB.selectCurrencyAsset(userId, currencyId));
        }
        if (asset instanceof StockInfoAsset) {
            int stockId = ((StockInfoAsset) asset).getStockId();
            assetChange(userId, asset, isPlus, () -> walletDB.selectStockAsset(userId, stockId));
        }
    }

    private <T extends Asset> void assetChange(Integer userId, Asset asset, boolean isPlus, Supplier<Optional<T>> supplier) throws InsufficientException {
        BigDecimal amount = asset.getAmount();

        Optional<T> assetOptional = supplier.get();
        if (assetOptional.isEmpty()) {
            BigDecimal result = isPlus ? asset.getAmount() : BigDecimal.ZERO;
            asset.setAmount(result);
            walletDB.insertWallet(userId, asset);

            if (result.compareTo(BigDecimal.ZERO) == 0) throw new InsufficientException();
        } else {
            Asset dbAsset = assetOptional.get();
            BigDecimal result = dbAsset.getAmount().add(isPlus ? amount : amount.negate());
            if (result.compareTo(BigDecimal.ZERO) < 0) throw new InsufficientException();

            dbAsset.setAmount(result);
            walletDB.updateAssetAmount(userId, dbAsset);
        }
    }
}
