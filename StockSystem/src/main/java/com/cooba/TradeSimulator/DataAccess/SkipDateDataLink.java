package com.cooba.TradeSimulator.DataAccess;

import com.cooba.TradeSimulator.Entity.SkipDate;
import com.cooba.TradeSimulator.Object.SkipDateReq;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkipDateDataLink implements BaseMapper<SkipDate, SkipDateReq> {

    @Override
    public List<SkipDate> find(SkipDateReq request) {

        return null;
    }

    @Override
    public List<SkipDate> findAll() {
        return null;
    }

    @Override
    public SkipDate save(SkipDate entity) {
        return null;
    }

    @Override
    public List<SkipDate> saveAll(List<SkipDate> entities) {
        return null;
    }

    @Override
    public SkipDate insert(SkipDate entity) {
        return null;
    }

    @Override
    public List<SkipDate> insertAll(List<SkipDate> entities) {
        return null;
    }

    @Override
    public SkipDate delete(SkipDateReq request) {
        return null;
    }

    @Override
    public List<SkipDate> deleteAll(List<SkipDate> entities) {
        return null;
    }
}
