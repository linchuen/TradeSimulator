package com.cooba.TradeSimulator.DataAccess;

import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Mapper.StockInfoMapper;
import com.cooba.TradeSimulator.Object.StockInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockInfoDataLink implements BaseMapper<StockInfo, StockInfoReq> {
    @Autowired
    StockInfoMapper stockInfoMapper;

    @Override
    public List<StockInfo> find(StockInfoReq request) {
        return null;
    }

    @Override
    public List<StockInfo> findAll() {
        return null;
    }

    @Override
    public StockInfo save(StockInfo entity) {
        return null;
    }

    @Override
    public List<StockInfo> saveAll(List<StockInfo> entities) {
        return null;
    }

    @Override
    public StockInfo insert(StockInfo entity) {
        return null;
    }

    @Override
    public List<StockInfo> insertAll(List<StockInfo> entities) {
        entities.forEach(entity -> {
            try {
                stockInfoMapper.insert(entity);
            } catch (Exception ignored) {
            }
        });
        return entities;
    }

    @Override
    public StockInfo delete(StockInfoReq request) {
        return null;
    }

    @Override
    public List<StockInfo> deleteAll(List<StockInfo> entities) {
        return null;
    }
}
