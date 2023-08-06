package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Mapper.StockInfoMapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.cooba.TradeSimulator.Mapper.StockInfoDynamicSqlSupport.stockInfo;

@Component
public class StockInfoDataAccess {
    @Autowired
    StockInfoMapper stockInfoMapper;

    public List<StockInfo> findAll() {
        SelectStatementProvider query = SqlBuilder.select(StockInfoMapper.selectList)
                .from(stockInfo)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return stockInfoMapper.selectMany(query);
    }

    public Optional<StockInfo> findById(int id) {
        return stockInfoMapper.selectByPrimaryKey(id);
    }

    public boolean insertAll(List<StockInfo> entities) {
        return stockInfoMapper.insertMultiple(entities) > 0;
    }
}
