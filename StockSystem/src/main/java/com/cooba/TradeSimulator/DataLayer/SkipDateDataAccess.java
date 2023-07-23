package com.cooba.TradeSimulator.DataLayer;

import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Component
public class SkipDateDataAccess {
    @Autowired
    SkipDateMapper skipDateMapper;

    public Optional<SkipDate> findByDate(LocalDate date) {
        SelectStatementProvider query = SqlBuilder.select(SkipDateMapper.selectList)
                .from(SkipDateDynamicSqlSupport.skipDate)
                .where(SkipDateDynamicSqlSupport.date, isEqualTo(date))
                .build().render(RenderingStrategies.MYBATIS3);
        return skipDateMapper.selectOne(query);
    }
}
