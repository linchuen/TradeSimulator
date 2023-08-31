package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Enum.DefaultCurrency;
import com.cooba.TradeSimulator.Enum.RedisKey;
import com.cooba.TradeSimulator.Object.CurrencyInfo;
import com.cooba.TradeSimulator.Util.JsonUtil;
import com.cooba.TradeSimulator.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyDB implements CurrencyDatabase {
    @Autowired
    private RedisUtil redisUtil;

    public BigDecimal getRate(Integer currencyId) {
        String json = redisUtil.get(RedisKey.currency.name(), String.valueOf(currencyId));
        if (json != null) {
            CurrencyInfo currencyInfo = JsonUtil.readJson(json, CurrencyInfo.class);
            assert currencyInfo != null;
            return currencyInfo.getRate();
        }

        return DefaultCurrency.getCurrencyMap().get(currencyId);
    }
}
