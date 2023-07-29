package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Mapper.SkipDateMapper;
import com.cooba.TradeSimulator.Mapper.StockTradeRecordDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.StockTradeRecordMapper;
import lombok.AllArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static com.cooba.TradeSimulator.Mapper.StockTradeRecordDynamicSqlSupport.stockTradeRecord;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
@AllArgsConstructor
public class StockTradeRecordDataAccess {
    private final StockTradeRecordMapper stockTradeRecordMapper;

    public Optional<StockTradeRecord> findByStockCodeAndDate(String stockcode, LocalDate date) {
        SelectStatementProvider query = SqlBuilder.select(StockTradeRecordMapper.selectList)
                .from(stockTradeRecord)
                .where(StockTradeRecordDynamicSqlSupport.stockcode, isEqualTo(stockcode))
                .and(StockTradeRecordDynamicSqlSupport.date, isEqualTo(date))
                .build().render(RenderingStrategies.MYBATIS3);
        return stockTradeRecordMapper.selectOne(query);
    }

    public boolean insert(StockTradeRecord entity) {
        return stockTradeRecordMapper.insert(entity) == 1;
    }

    public boolean insertAll(List<StockTradeRecord> entitys) {
        return stockTradeRecordMapper.insertMultiple(entitys) != 0;
    }
}
