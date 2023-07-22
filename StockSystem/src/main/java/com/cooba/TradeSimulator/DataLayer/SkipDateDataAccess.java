package com.cooba.TradeSimulator.DataLayer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cooba.TradeSimulator.Entity.SkipDate;
import com.cooba.TradeSimulator.Mapper.SkipDateDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.SkipDateMapper;
import com.cooba.TradeSimulator.Request.SkipDateReq;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Component
public class SkipDateDataAccess implements BaseMapper<SkipDate, SkipDateReq> {
    @Autowired
    SkipDateMapper skipDateMapper;

    @Override
    public List<SkipDate> find(SkipDateReq request) {
        SelectStatementProvider query = SqlBuilder.select(SkipDateMapper.selectList)
                .from(SkipDateDynamicSqlSupport.skipDate)
                .where(SkipDateDynamicSqlSupport.date, isEqualTo(request.getDate()))
                .build().render(RenderingStrategies.MYBATIS3);
        return skipDateMapper.selectMany(query);
    }

    @Override
    public List<SkipDate> findAll() {
        return null;
    }

    @Override
    public boolean save(SkipDate entity) {
        return false;
    }

    @Override
    public boolean saveAll(List<SkipDate> entities) {
        return false;
    }

    @Override
    public boolean insert(SkipDate entity) {
        return false;
    }

    @Override
    public boolean insertAll(List<SkipDate> entities) {
        return false;
    }

    @Override
    public boolean delete(SkipDateReq request) {
        return false;
    }

    @Override
    public boolean deleteAll(List<SkipDate> entities) {
        return false;
    }
}
