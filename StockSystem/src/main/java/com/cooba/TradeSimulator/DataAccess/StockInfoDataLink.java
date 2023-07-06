package com.cooba.TradeSimulator.DataAccess;

import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Mapper.CustStockInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockInfoDataLink implements BaseMapper<StockInfo> {
    @Autowired
    CustStockInfoMapper custStockInfoMapper;

    @Override
    public List<StockInfo> find(StockInfo entity) {
        return custStockInfoMapper.select(entity);
    }

    @Override
    public List<StockInfo> findAll() {
        return custStockInfoMapper.select(StockInfo.builder().build());
    }

    @Override
    public StockInfo save(StockInfo entity) {
        custStockInfoMapper.update(entity);
        return entity;
    }

    @Override
    public List<StockInfo> saveAll(List<StockInfo> entities) {
        custStockInfoMapper.updateAll(entities);
        return entities;
    }

    @Override
    public StockInfo insert(StockInfo entity) {
        custStockInfoMapper.insert(List.of(entity));
        return entity;
    }

    @Override
    public List<StockInfo> insertAll(List<StockInfo> entities) {
        custStockInfoMapper.insert(entities);
        return entities;
    }

    @Override
    public StockInfo delete(StockInfo entity) {
        custStockInfoMapper.delete(entity);
        return entity;
    }

    @Override
    public List<StockInfo> deleteAll(List<StockInfo> entities) {
        entities.forEach(stockInfo -> custStockInfoMapper.delete(stockInfo));
        return entities;
    }
}
