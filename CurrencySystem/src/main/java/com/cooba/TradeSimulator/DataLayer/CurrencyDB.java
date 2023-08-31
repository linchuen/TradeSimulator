package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.Currency;
import com.cooba.TradeSimulator.Enum.DefaultCurrency;
import com.cooba.TradeSimulator.Mapper.CurrencyDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.CurrencyMapper;
import com.cooba.TradeSimulator.Util.RedisUtil;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.cooba.TradeSimulator.Mapper.CurrencyDynamicSqlSupport.currency;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class CurrencyDB {
    @Autowired
    private CurrencyMapper currencyMapper;
    @Autowired
    private RedisUtil redisUtil;

    private final String redisKey = "currency";

    public boolean insert(Currency currency) {
        Map<String, DefaultCurrency> defaultCurrencyMap = DefaultCurrency.getCurrencyNameMap();
        DefaultCurrency defaultCurrency = defaultCurrencyMap.get(currency.getName());
        currency.setId(defaultCurrency == null ? null : defaultCurrency.getId());
        currency.setCreatedTime(LocalDateTime.now());
        currency.setUpdatedTime(LocalDateTime.now());
        redisUtil.put(redisKey, currency.getName(), currency.getRate().toPlainString());
        return currencyMapper.insert(currency) == 1;
    }

    public boolean update(Currency currency) {
        UpdateStatementProvider query = SqlBuilder.update(CurrencyDynamicSqlSupport.currency)
                .set(CurrencyDynamicSqlSupport.rate).equalTo(currency.getRate())
                .set(CurrencyDynamicSqlSupport.updatedTime).equalTo(LocalDateTime.now())
                .where(CurrencyDynamicSqlSupport.name, isEqualTo(currency.getName()))
                .build().render(RenderingStrategies.MYBATIS3);
        redisUtil.put(redisKey, currency.getName(), currency.getRate().toPlainString());
        return currencyMapper.update(query) == 1;
    }

    public List<Currency> selectAll() {
        SelectStatementProvider query = SqlBuilder.select(CurrencyMapper.selectList)
                .from(currency)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return currencyMapper.selectMany(query);
    }

    public Optional<Currency> selectById(Integer currencyId) {
        return currencyMapper.selectByPrimaryKey(currencyId);
    }

    public BigDecimal getRateByName(String currencyName) {
        String rate = redisUtil.get(redisKey, currencyName);
        if (rate != null) return new BigDecimal(rate);

        SelectStatementProvider query = SqlBuilder.select(currency.rate)
                .from(currency)
                .where(CurrencyDynamicSqlSupport.name, isEqualTo(currencyName))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<Currency> currencyOptional = currencyMapper.selectOne(query);
        return currencyOptional.map(Currency::getRate).orElse(null);
    }
}
