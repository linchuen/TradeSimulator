package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Mapper.SkipDateMapper;
import com.cooba.TradeSimulator.Mapper.StockTradeRecordMapper;
import com.cooba.TradeSimulator.Request.StockTradeRecordReq;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.cooba.TradeSimulator.Mapper.StockTradeRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Component
public class StockTradeRecordDataAccess implements BaseMapper<StockTradeRecord, StockTradeRecordReq> {
    @Autowired
    StockTradeRecordMapper stockTradeRecordMapper;

    @Override
    public List<StockTradeRecord> find(StockTradeRecordReq request) {
        SelectStatementProvider query = SqlBuilder.select(SkipDateMapper.selectList)
                .from(stockTradeRecord)
                .where(stockcode, isEqualTo(request.getStockcode()))
                .and(date,isEqualTo(request.getDate()))
                .build().render(RenderingStrategies.MYBATIS3);
        return stockTradeRecordMapper.selectMany(query);
    }

    @Override
    public List<StockTradeRecord> findAll() {
        SelectStatementProvider query = SqlBuilder.select(SkipDateMapper.selectList)
                .from(stockTradeRecord)
                .build().render(RenderingStrategies.MYBATIS3);
        return stockTradeRecordMapper.selectMany(query);
    }

    @Override
    public boolean save(StockTradeRecord entity) {
        return false;
    }

    @Override
    public boolean saveAll(List<StockTradeRecord> entities) {
        return false;
    }

    @Override
    public boolean insert(StockTradeRecord entity) {
        return stockTradeRecordMapper.insert(entity) == 1;
    }

    @Override
    public boolean insertAll(List<StockTradeRecord> entities) {
        return false;
    }

    @Override
    public boolean delete(StockTradeRecordReq request) {
        return false;
    }

    @Override
    public boolean deleteAll(List<StockTradeRecord> entities) {
        return false;
    }
}
