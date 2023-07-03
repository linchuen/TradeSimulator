package com.cooba.TradeSimulator.DataAccess;

import java.util.List;

public interface BaseMapper<T> {
    List<T> find(T entity);

    List<T> findAll();

    T save(T entity);

    List<T> saveAll(List<T> entities);

    T insert(T entity);

    List<T> insertAll(List<T> entities);

    T delete(T entity);

    List<T> deleteAll(List<T> entities);
}
