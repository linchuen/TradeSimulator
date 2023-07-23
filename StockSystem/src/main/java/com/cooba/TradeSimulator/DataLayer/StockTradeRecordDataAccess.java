package com.cooba.TradeSimulator.DataLayer;

import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

import static com.cooba.TradeSimulator.Mapper.StockTradeRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Component
public class StockTradeRecordDataAccess{
    @Autowired
    StockTradeRecordMapper stockTradeRecordMapper;

    public Optional<StockTradeRecord> findByStockCodeAndDate(String stockcode, LocalDate date) {
        SelectStatementProvider query = SqlBuilder.select(SkipDateMapper.selectList)
                .from(stockTradeRecord)
                .where(StockTradeRecordDynamicSqlSupport.stockcode, isEqualTo(stockcode))
                .and(StockTradeRecordDynamicSqlSupport.date,isEqualTo(date))
                .build().render(RenderingStrategies.MYBATIS3);
        return stockTradeRecordMapper.selectOne(query);
    }

    public boolean insert(StockTradeRecord entity) {
        return stockTradeRecordMapper.insert(entity) == 1;
    }
}
