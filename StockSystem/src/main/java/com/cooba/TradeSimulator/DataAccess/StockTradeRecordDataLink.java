package com.cooba.TradeSimulator.DataAccess;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Mapper.StockTradeRecordMapper;
import com.cooba.TradeSimulator.Object.StockTradeRecordReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockTradeRecordDataLink implements BaseMapper<StockTradeRecord, StockTradeRecordReq> {
    @Autowired
    StockTradeRecordMapper stockTradeRecordMapper;

    @Override
    public List<StockTradeRecord> find(StockTradeRecordReq request) {
        QueryWrapper<StockTradeRecord> query = new QueryWrapper<>();
        query.eq("stockcode", request.getStockcode()).eq("date", request.getDate());
        return stockTradeRecordMapper.selectList(query);
    }

    @Override
    public List<StockTradeRecord> findAll() {
        return null;
    }

    @Override
    public StockTradeRecord save(StockTradeRecord entity) {
        return null;
    }

    @Override
    public List<StockTradeRecord> saveAll(List<StockTradeRecord> entities) {
        return null;
    }

    @Override
    public StockTradeRecord insert(StockTradeRecord entity) {
        return null;
    }

    @Override
    public List<StockTradeRecord> insertAll(List<StockTradeRecord> entities) {
        return null;
    }

    @Override
    public StockTradeRecord delete(StockTradeRecordReq request) {
        return null;
    }

    @Override
    public List<StockTradeRecord> deleteAll(List<StockTradeRecord> entities) {
        return null;
    }
}
