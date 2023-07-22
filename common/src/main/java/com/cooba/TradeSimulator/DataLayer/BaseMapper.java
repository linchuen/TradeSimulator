package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Object.Request;

import java.util.List;

public interface BaseMapper<T, R extends Request> {
    List<T> find(R request);

    List<T> findAll();

    boolean save(T entity);

    boolean saveAll(List<T> entities);

    boolean insert(T entity);

    boolean insertAll(List<T> entities);

    boolean delete(R request);

    boolean deleteAll(List<T> entities);
}
