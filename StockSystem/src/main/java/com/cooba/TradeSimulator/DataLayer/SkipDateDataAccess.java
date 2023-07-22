package com.cooba.TradeSimulator.DataLayer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cooba.TradeSimulator.Entity.SkipDate;
import com.cooba.TradeSimulator.Mapper.SkipDateMapper;
import com.cooba.TradeSimulator.Request.SkipDateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkipDateDataAccess implements BaseMapper<SkipDate, SkipDateReq> {
    @Autowired
    SkipDateMapper skipDateMapper;

    @Override
    public List<SkipDate> find(SkipDateReq request) {
        QueryWrapper<SkipDate> query = new QueryWrapper<>();
        query.eq("skip_date", request.getSkipDate());
        return skipDateMapper.selectList(query);
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
