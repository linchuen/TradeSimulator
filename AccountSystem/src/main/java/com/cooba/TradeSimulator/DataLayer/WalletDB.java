package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.UserCurrencyWallet;
import com.cooba.TradeSimulator.Entity.UserStockWallet;
import com.cooba.TradeSimulator.Mapper.UserCurrencyWalletDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.UserCurrencyWalletMapper;
import com.cooba.TradeSimulator.Mapper.UserStockWalletDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.UserStockWalletMapper;
import com.cooba.TradeSimulator.Object.asset.Asset;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Object.asset.StockInfoAsset;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class WalletDB {
    @Autowired
    private UserCurrencyWalletMapper userCurrencyWalletMapper;
    @Autowired
    private UserStockWalletMapper userStockWalletMapper;

    public boolean insertWallet(Integer userId, Asset asset) {
        if (asset instanceof CurrencyAsset) {
            UserCurrencyWallet userCurrencyWallet = UserCurrencyWallet.builder()
                    .accountId(userId)
                    .currencyId(((CurrencyAsset) asset).getCurrencyId())
                    .amount(BigDecimal.ZERO)
                    .createdTime(LocalDateTime.now())
                    .updatedTime(LocalDateTime.now())
                    .build();
            return userCurrencyWalletMapper.insert(userCurrencyWallet) == 1;
        }
        if (asset instanceof StockInfoAsset) {
            UserStockWallet userStockWallet = UserStockWallet.builder()
                    .accountId(userId)
                    .stockId(((StockInfoAsset) asset).getStockId())
                    .amount(BigDecimal.ZERO)
                    .createdTime(LocalDateTime.now())
                    .updatedTime(LocalDateTime.now())
                    .build();
            return userStockWalletMapper.insert(userStockWallet) == 1;
        }
        return false;
    }

    public boolean updateAssetAmount(Integer userId, Asset asset) {
        if (asset instanceof CurrencyAsset) {
            UpdateStatementProvider query = SqlBuilder.update(UserCurrencyWalletDynamicSqlSupport.userCurrencyWallet)
                    .set(UserCurrencyWalletDynamicSqlSupport.amount).equalTo(asset.getAmount())
                    .set(UserCurrencyWalletDynamicSqlSupport.updatedTime).equalTo(LocalDateTime.now())
                    .where(UserCurrencyWalletDynamicSqlSupport.accountId, isEqualTo(userId))
                    .and(UserCurrencyWalletDynamicSqlSupport.currencyId, isEqualTo(((CurrencyAsset) asset).getCurrencyId()))
                    .build().render(RenderingStrategies.MYBATIS3);
            return userCurrencyWalletMapper.update(query) == 1;
        }
        if (asset instanceof StockInfoAsset) {
            UpdateStatementProvider query = SqlBuilder.update(UserStockWalletDynamicSqlSupport.userStockWallet)
                    .set(UserStockWalletDynamicSqlSupport.amount).equalTo(asset.getAmount())
                    .set(UserStockWalletDynamicSqlSupport.updatedTime).equalTo(LocalDateTime.now())
                    .where(UserStockWalletDynamicSqlSupport.accountId, isEqualTo(userId))
                    .and(UserStockWalletDynamicSqlSupport.stockId, isEqualTo(((StockInfoAsset) asset).getStockId()))
                    .build().render(RenderingStrategies.MYBATIS3);
            return userCurrencyWalletMapper.update(query) == 1;
        }
        return false;
    }

    public Optional<CurrencyAsset> selectCurrencyAsset(Integer userId, Integer currencyId) {
        SelectStatementProvider query = SqlBuilder.select(UserCurrencyWalletMapper.selectList)
                .from(UserCurrencyWalletDynamicSqlSupport.userCurrencyWallet)
                .where(UserCurrencyWalletDynamicSqlSupport.accountId, isEqualTo(userId))
                .and(UserCurrencyWalletDynamicSqlSupport.currencyId, isEqualTo(currencyId))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<UserCurrencyWallet> currencyWalletOptional = userCurrencyWalletMapper.selectOne(query);

        if (currencyWalletOptional.isEmpty()) {
            return Optional.empty();
        }

        UserCurrencyWallet userCurrencyWallet = currencyWalletOptional.get();
        return Optional.of(CurrencyAsset.builder()
                .currencyId(userCurrencyWallet.getCurrencyId())
                .amount(userCurrencyWallet.getAmount())
                .build());
    }

    public List<CurrencyAsset> selectCurrencyAssetList(Integer userId) {
        SelectStatementProvider query = SqlBuilder.select(UserCurrencyWalletMapper.selectList)
                .from(UserCurrencyWalletDynamicSqlSupport.userCurrencyWallet)
                .where(UserCurrencyWalletDynamicSqlSupport.accountId, isEqualTo(userId))
                .build().render(RenderingStrategies.MYBATIS3);
        List<UserCurrencyWallet> currencyWalletList = userCurrencyWalletMapper.selectMany(query);

        return currencyWalletList.stream()
                .map(userCurrencyWallet -> CurrencyAsset.builder()
                        .currencyId(userCurrencyWallet.getCurrencyId())
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<StockInfoAsset> selectStockAsset(Integer userId, Integer stockId) {
        SelectStatementProvider query = SqlBuilder.select(UserStockWalletMapper.selectList)
                .from(UserStockWalletDynamicSqlSupport.userStockWallet)
                .where(UserStockWalletDynamicSqlSupport.accountId, isEqualTo(userId))
                .and(UserStockWalletDynamicSqlSupport.stockId, isEqualTo(stockId))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<UserStockWallet> stockWalletOptional = userStockWalletMapper.selectOne(query);

        if (stockWalletOptional.isEmpty()) {
            return Optional.empty();
        }

        UserStockWallet userStockWallet = stockWalletOptional.get();
        return Optional.of(StockInfoAsset.builder()
                .stockId(userStockWallet.getStockId())
                .amount(userStockWallet.getAmount())
                .build());
    }

    public List<StockInfoAsset> selectStockAssetList(Integer userId) {
        SelectStatementProvider query = SqlBuilder.select(UserStockWalletMapper.selectList)
                .from(UserStockWalletDynamicSqlSupport.userStockWallet)
                .where(UserStockWalletDynamicSqlSupport.accountId, isEqualTo(userId))
                .build().render(RenderingStrategies.MYBATIS3);
        List<UserStockWallet> stockWalletList = userStockWalletMapper.selectMany(query);

        return stockWalletList.stream()
                .map(userStockWallet -> StockInfoAsset.builder()
                        .stockId(userStockWallet.getStockId())
                        .build())
                .collect(Collectors.toList());
    }
}
