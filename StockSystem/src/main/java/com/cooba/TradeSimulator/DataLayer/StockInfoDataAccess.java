package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Mapper.CustStockInfoMapper;
import com.cooba.TradeSimulator.Request.StockInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockInfoDataAccess implements BaseMapper<StockInfo, StockInfoReq> {
    @Autowired
    CustStockInfoMapper custStockInfoMapper;

    @Override
    public List<StockInfo> find(StockInfoReq request) {
        return null;
    }

    @Override
    public List<StockInfo> findAll() {
        return null;
    }

    @Override
    public boolean save(StockInfo entity) {
        return false;
    }

    @Override
    public boolean saveAll(List<StockInfo> entities) {
        return false;
    }

    @Override
    public boolean insert(StockInfo entity) {
        return false;
    }

    @Override
    public boolean insertAll(List<StockInfo> entities) {
        return custStockInfoMapper.insertAll(entities)==1;
    }

    @Override
    public boolean delete(StockInfoReq request) {
        return false;
    }

    @Override
    public boolean deleteAll(List<StockInfo> entities) {
        return false;
    }
}
