package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Mapper.StockTradeRecordDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.StockTradeRecordMapper;
import lombok.AllArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static com.cooba.TradeSimulator.Mapper.StockTradeRecordDynamicSqlSupport.stockTradeRecord;
import static org.mybatis.dynamic.sql.SqlBuilder.isBetween;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
@AllArgsConstructor
public class StockTradeRecordDB {
    private final StockTradeRecordMapper stockTradeRecordMapper;

    public Optional<StockTradeRecord> findByStockCodeAndDate(String stockcode, LocalDate date) {
        SelectStatementProvider query = SqlBuilder.select(StockTradeRecordMapper.selectList)
                .from(stockTradeRecord)
                .where(StockTradeRecordDynamicSqlSupport.stockcode, isEqualTo(stockcode))
                .and(StockTradeRecordDynamicSqlSupport.date, isEqualTo(date))
                .build().render(RenderingStrategies.MYBATIS3);
        return stockTradeRecordMapper.selectOne(query);
    }

    public List<StockTradeRecord> findByStockCodeAndMonth(String stockcode, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        SelectStatementProvider query = SqlBuilder.select(StockTradeRecordMapper.selectList)
                .from(stockTradeRecord)
                .where(StockTradeRecordDynamicSqlSupport.stockcode, isEqualTo(stockcode))
                .and(StockTradeRecordDynamicSqlSupport.date, isBetween(startDate).and(endDate))
                .build().render(RenderingStrategies.MYBATIS3);
        return stockTradeRecordMapper.selectMany(query);
    }

    public boolean insert(StockTradeRecord entity) {
        return stockTradeRecordMapper.insert(entity) == 1;
    }

    public boolean insertAll(List<StockTradeRecord> entitys) {
        return stockTradeRecordMapper.insertMultiple(entitys) != 0;
    }

    public boolean update(StockTradeRecord entity) {
        return stockTradeRecordMapper.updateByPrimaryKeySelective(entity)== 1;
    }
}
