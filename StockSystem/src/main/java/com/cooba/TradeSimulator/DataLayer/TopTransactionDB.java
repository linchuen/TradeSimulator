package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.TopTransactionStock;
import com.cooba.TradeSimulator.Mapper.TopTransactionStockDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.TopTransactionStockMapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.cooba.TradeSimulator.Mapper.TopTransactionStockDynamicSqlSupport.topTransactionStock;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class TopTransactionDB {
    @Autowired
    private TopTransactionStockMapper topTransactionStockMapper;

    public boolean insertAll(List<TopTransactionStock> entitys) {
        return topTransactionStockMapper.insertMultiple(entitys) != 0;
    }

    public List<TopTransactionStock> findByDate(LocalDate date) {
        SelectStatementProvider query = SqlBuilder.select(TopTransactionStockMapper.selectList)
                .from(topTransactionStock)
                .where(TopTransactionStockDynamicSqlSupport.date, isEqualTo(date))
                .build().render(RenderingStrategies.MYBATIS3);
        return topTransactionStockMapper.selectMany(query);
    }
}
