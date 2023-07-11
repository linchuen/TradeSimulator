package com.cooba.TradeSimulator.DataAccess;

import com.cooba.TradeSimulator.Object.Request;

import java.util.List;

public interface BaseMapper<T, R extends Request> {
    List<T> find(R request);

    List<T> findAll();

    T save(T entity);

    List<T> saveAll(List<T> entities);

    T insert(T entity);

    List<T> insertAll(List<T> entities);

    T delete(R request);

    List<T> deleteAll(List<T> entities);
}
