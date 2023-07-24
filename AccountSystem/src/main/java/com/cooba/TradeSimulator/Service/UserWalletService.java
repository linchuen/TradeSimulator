package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.CurrencyDataAcccess;
import com.cooba.TradeSimulator.DataLayer.WalletDataAccess;
import com.cooba.TradeSimulator.Exception.InsufficientBalanceException;
import com.cooba.TradeSimulator.Exception.NotSupportCurrencyException;
import com.cooba.TradeSimulator.Object.Asset;
import com.cooba.TradeSimulator.Object.Wallet;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Object.asset.StockInfoAsset;
import com.cooba.TradeSimulator.Object.wallet.CurrencyWallet;
import com.cooba.TradeSimulator.Object.wallet.StockWallet;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserWalletService implements WalletService {
    @Autowired
    WalletDataAccess walletDataAccess;
    @Autowired
    CurrencyDataAcccess currencyDataAcccess;

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
    public List<Wallet> getWallets(Integer userId) {
        List<CurrencyAsset> currencyAssetList = walletDataAccess.selectCurrencyAssetList(userId);
        List<StockInfoAsset> stockAssetList = walletDataAccess.selectStockAssetList(userId);
        CurrencyWallet currencyWallet = CurrencyWallet.builder().userId(userId).assets(currencyAssetList).build();
        StockWallet stockWallet = StockWallet.builder().userId(userId).assets(stockAssetList).build();
        return List.of(currencyWallet, stockWallet);
    }

    @Override
    public CurrencyAsset assessAssetByUnit(Integer userId, Integer currencyId) throws NotSupportCurrencyException {
        List<Asset> totalAssets = new LinkedList<>();
        for (Wallet wallet : getWallets(userId)) {
            totalAssets.addAll(wallet.getAssets());
        }
        CurrencyAsset result = CurrencyAsset.builder().currencyId(currencyId).amount(BigDecimal.ZERO).build();
        for (Asset asset : totalAssets) {
            BigDecimal amount = exchange(asset, CurrencyAsset.builder().currencyId(currencyId).build()).getAmount();
            result.setAmount(result.getAmount().add(amount));
        }
        return result;
    }

    @Override
    public CurrencyAsset exchange(Asset input, CurrencyAsset output) throws NotSupportCurrencyException {
        if (input instanceof CurrencyAsset) {
            Integer inId = ((CurrencyAsset) input).getCurrencyId();
            BigDecimal fromRate = currencyDataAcccess.getCurrencyRate(inId);
            if (fromRate == null) throw new NotSupportCurrencyException();

            Integer outId = output.getCurrencyId();
            BigDecimal toRate = currencyDataAcccess.getCurrencyRate(outId);
            if (toRate == null) throw new NotSupportCurrencyException();

            output.setAmount(input.getAmount().multiply(fromRate).divide(toRate, 5, RoundingMode.FLOOR));
            return output;
        }
        if (input instanceof StockInfoAsset) {
            BigDecimal closingPrice = ((StockInfoAsset) input).getClosingPrice();

            Integer outId = output.getCurrencyId();
            BigDecimal toRate = currencyDataAcccess.getCurrencyRate(outId);
            if (toRate == null) throw new NotSupportCurrencyException();

            output.setAmount(input.getAmount().multiply(closingPrice).divide(toRate, 5, RoundingMode.FLOOR));
            return output;
        }
        throw new NotSupportCurrencyException();
    }
}
