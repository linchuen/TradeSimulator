package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.SkipDate;
import com.cooba.TradeSimulator.Mapper.SkipDateDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.SkipDateMapper;
import lombok.AllArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class SkipDateDB {
    @Autowired
    private SkipDateMapper skipDateMapper;

    public Optional<SkipDate> findByDate(LocalDate date) {
        SelectStatementProvider query = SqlBuilder.select(SkipDateMapper.selectList)
                .from(SkipDateDynamicSqlSupport.skipDate)
                .where(SkipDateDynamicSqlSupport.date, isEqualTo(date))
                .build().render(RenderingStrategies.MYBATIS3);
        return skipDateMapper.selectOne(query);
    }

    public boolean insertAll(List<SkipDate> skipDateList) {
        return skipDateMapper.insertMultiple(skipDateList) != 0;
    }
}
