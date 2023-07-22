package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Mapper.StockInfoMapper;
import com.cooba.TradeSimulator.Request.StockInfoReq;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.cooba.TradeSimulator.Mapper.StockInfoDynamicSqlSupport.stockInfo;

@Component
public class StockInfoDataAccess implements BaseMapper<StockInfo, StockInfoReq> {
    @Autowired
    StockInfoMapper stockInfoMapper;

    @Override
    public List<StockInfo> find(StockInfoReq request) {
        return null;
    }

    @Override
    public List<StockInfo> findAll() {
        SelectStatementProvider query = SqlBuilder.select(StockInfoMapper.selectList)
                .from(stockInfo)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return stockInfoMapper.selectMany(query);
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
        return stockInfoMapper.insertMultiple(entities) > 0;
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
