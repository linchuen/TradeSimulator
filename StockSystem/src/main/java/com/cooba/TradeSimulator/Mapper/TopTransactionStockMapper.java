package com.cooba.TradeSimulator.Mapper;

import static com.cooba.TradeSimulator.Mapper.TopTransactionStockDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.cooba.TradeSimulator.Entity.TopTransactionStock;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface TopTransactionStockMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, rank, stockcode, name, date, tradingVolume, transaction, openingPrice, highestPrice, lowestPrice, closingPrice, createdTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TopTransactionStock> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TopTransactionStock> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TopTransactionStockResult")
    Optional<TopTransactionStock> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TopTransactionStockResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
        @Result(column="stockcode", property="stockcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE),
        @Result(column="trading_volume", property="tradingVolume", jdbcType=JdbcType.DECIMAL),
        @Result(column="transaction", property="transaction", jdbcType=JdbcType.DECIMAL),
        @Result(column="opening_price", property="openingPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="highest_price", property="highestPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="lowest_price", property="lowestPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="closing_price", property="closingPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TopTransactionStock> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, topTransactionStock, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, topTransactionStock, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TopTransactionStock record) {
        return MyBatis3Utils.insert(this::insert, record, topTransactionStock, c ->
            c.map(id).toProperty("id")
            .map(rank).toProperty("rank")
            .map(stockcode).toProperty("stockcode")
            .map(name).toProperty("name")
            .map(date).toProperty("date")
            .map(tradingVolume).toProperty("tradingVolume")
            .map(transaction).toProperty("transaction")
            .map(openingPrice).toProperty("openingPrice")
            .map(highestPrice).toProperty("highestPrice")
            .map(lowestPrice).toProperty("lowestPrice")
            .map(closingPrice).toProperty("closingPrice")
            .map(createdTime).toProperty("createdTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TopTransactionStock> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, topTransactionStock, c ->
            c.map(id).toProperty("id")
            .map(rank).toProperty("rank")
            .map(stockcode).toProperty("stockcode")
            .map(name).toProperty("name")
            .map(date).toProperty("date")
            .map(tradingVolume).toProperty("tradingVolume")
            .map(transaction).toProperty("transaction")
            .map(openingPrice).toProperty("openingPrice")
            .map(highestPrice).toProperty("highestPrice")
            .map(lowestPrice).toProperty("lowestPrice")
            .map(closingPrice).toProperty("closingPrice")
            .map(createdTime).toProperty("createdTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TopTransactionStock record) {
        return MyBatis3Utils.insert(this::insert, record, topTransactionStock, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(rank).toPropertyWhenPresent("rank", record::getRank)
            .map(stockcode).toPropertyWhenPresent("stockcode", record::getStockcode)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(date).toPropertyWhenPresent("date", record::getDate)
            .map(tradingVolume).toPropertyWhenPresent("tradingVolume", record::getTradingVolume)
            .map(transaction).toPropertyWhenPresent("transaction", record::getTransaction)
            .map(openingPrice).toPropertyWhenPresent("openingPrice", record::getOpeningPrice)
            .map(highestPrice).toPropertyWhenPresent("highestPrice", record::getHighestPrice)
            .map(lowestPrice).toPropertyWhenPresent("lowestPrice", record::getLowestPrice)
            .map(closingPrice).toPropertyWhenPresent("closingPrice", record::getClosingPrice)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TopTransactionStock> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, topTransactionStock, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TopTransactionStock> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, topTransactionStock, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TopTransactionStock> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, topTransactionStock, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TopTransactionStock> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, topTransactionStock, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TopTransactionStock record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(rank).equalTo(record::getRank)
                .set(stockcode).equalTo(record::getStockcode)
                .set(name).equalTo(record::getName)
                .set(date).equalTo(record::getDate)
                .set(tradingVolume).equalTo(record::getTradingVolume)
                .set(transaction).equalTo(record::getTransaction)
                .set(openingPrice).equalTo(record::getOpeningPrice)
                .set(highestPrice).equalTo(record::getHighestPrice)
                .set(lowestPrice).equalTo(record::getLowestPrice)
                .set(closingPrice).equalTo(record::getClosingPrice)
                .set(createdTime).equalTo(record::getCreatedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TopTransactionStock record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(rank).equalToWhenPresent(record::getRank)
                .set(stockcode).equalToWhenPresent(record::getStockcode)
                .set(name).equalToWhenPresent(record::getName)
                .set(date).equalToWhenPresent(record::getDate)
                .set(tradingVolume).equalToWhenPresent(record::getTradingVolume)
                .set(transaction).equalToWhenPresent(record::getTransaction)
                .set(openingPrice).equalToWhenPresent(record::getOpeningPrice)
                .set(highestPrice).equalToWhenPresent(record::getHighestPrice)
                .set(lowestPrice).equalToWhenPresent(record::getLowestPrice)
                .set(closingPrice).equalToWhenPresent(record::getClosingPrice)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TopTransactionStock record) {
        return update(c ->
            c.set(rank).equalTo(record::getRank)
            .set(stockcode).equalTo(record::getStockcode)
            .set(name).equalTo(record::getName)
            .set(date).equalTo(record::getDate)
            .set(tradingVolume).equalTo(record::getTradingVolume)
            .set(transaction).equalTo(record::getTransaction)
            .set(openingPrice).equalTo(record::getOpeningPrice)
            .set(highestPrice).equalTo(record::getHighestPrice)
            .set(lowestPrice).equalTo(record::getLowestPrice)
            .set(closingPrice).equalTo(record::getClosingPrice)
            .set(createdTime).equalTo(record::getCreatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TopTransactionStock record) {
        return update(c ->
            c.set(rank).equalToWhenPresent(record::getRank)
            .set(stockcode).equalToWhenPresent(record::getStockcode)
            .set(name).equalToWhenPresent(record::getName)
            .set(date).equalToWhenPresent(record::getDate)
            .set(tradingVolume).equalToWhenPresent(record::getTradingVolume)
            .set(transaction).equalToWhenPresent(record::getTransaction)
            .set(openingPrice).equalToWhenPresent(record::getOpeningPrice)
            .set(highestPrice).equalToWhenPresent(record::getHighestPrice)
            .set(lowestPrice).equalToWhenPresent(record::getLowestPrice)
            .set(closingPrice).equalToWhenPresent(record::getClosingPrice)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}