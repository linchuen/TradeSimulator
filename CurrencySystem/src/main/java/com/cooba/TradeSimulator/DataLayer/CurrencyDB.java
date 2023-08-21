package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.Currency;
import com.cooba.TradeSimulator.Enum.DefaultCurrency;
import com.cooba.TradeSimulator.Mapper.CurrencyDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.CurrencyMapper;
import lombok.AllArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.cooba.TradeSimulator.Mapper.CurrencyDynamicSqlSupport.currency;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
@AllArgsConstructor
public class CurrencyDB {
    private final CurrencyMapper currencyMapper;

    public boolean insert(Currency currency) {
        Map<String, DefaultCurrency> defaultCurrencyMap = DefaultCurrency.getCurrencyNameMap();
        DefaultCurrency defaultCurrency = defaultCurrencyMap.get(currency.getName());
        currency.setId(defaultCurrency == null ? null : defaultCurrency.getId());
        currency.setCreatedTime(LocalDateTime.now());
        currency.setUpdatedTime(LocalDateTime.now());
        return currencyMapper.insert(currency) == 1;
    }

    public boolean update(Currency currency) {
        UpdateStatementProvider query = SqlBuilder.update(CurrencyDynamicSqlSupport.currency)
                .set(CurrencyDynamicSqlSupport.rate).equalTo(currency.getRate())
                .set(CurrencyDynamicSqlSupport.updatedTime).equalTo(LocalDateTime.now())
                .where(CurrencyDynamicSqlSupport.name, isEqualTo(currency.getName()))
                .build().render(RenderingStrategies.MYBATIS3);
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
}
