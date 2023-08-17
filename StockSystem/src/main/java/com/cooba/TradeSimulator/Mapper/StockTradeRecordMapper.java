package com.cooba.TradeSimulator.Mapper;

import static com.cooba.TradeSimulator.Mapper.StockTradeRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.cooba.TradeSimulator.Entity.StockTradeRecord;
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
public interface StockTradeRecordMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, stockcode, date, tradingVolume, transaction, openingPrice, highestPrice, lowestPrice, closingPrice, turnover, createdTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<StockTradeRecord> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<StockTradeRecord> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("StockTradeRecordResult")
    Optional<StockTradeRecord> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="StockTradeRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="stockcode", property="stockcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE),
        @Result(column="trading_volume", property="tradingVolume", jdbcType=JdbcType.DECIMAL),
        @Result(column="transaction", property="transaction", jdbcType=JdbcType.DECIMAL),
        @Result(column="opening_price", property="openingPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="highest_price", property="highestPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="lowest_price", property="lowestPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="closing_price", property="closingPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="turnover", property="turnover", jdbcType=JdbcType.DECIMAL),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<StockTradeRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, stockTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, stockTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(StockTradeRecord record) {
        return MyBatis3Utils.insert(this::insert, record, stockTradeRecord, c ->
            c.map(id).toProperty("id")
            .map(stockcode).toProperty("stockcode")
            .map(date).toProperty("date")
            .map(tradingVolume).toProperty("tradingVolume")
            .map(transaction).toProperty("transaction")
            .map(openingPrice).toProperty("openingPrice")
            .map(highestPrice).toProperty("highestPrice")
            .map(lowestPrice).toProperty("lowestPrice")
            .map(closingPrice).toProperty("closingPrice")
            .map(turnover).toProperty("turnover")
            .map(createdTime).toProperty("createdTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<StockTradeRecord> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, stockTradeRecord, c ->
            c.map(id).toProperty("id")
            .map(stockcode).toProperty("stockcode")
            .map(date).toProperty("date")
            .map(tradingVolume).toProperty("tradingVolume")
            .map(transaction).toProperty("transaction")
            .map(openingPrice).toProperty("openingPrice")
            .map(highestPrice).toProperty("highestPrice")
            .map(lowestPrice).toProperty("lowestPrice")
            .map(closingPrice).toProperty("closingPrice")
            .map(turnover).toProperty("turnover")
            .map(createdTime).toProperty("createdTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(StockTradeRecord record) {
        return MyBatis3Utils.insert(this::insert, record, stockTradeRecord, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(stockcode).toPropertyWhenPresent("stockcode", record::getStockcode)
            .map(date).toPropertyWhenPresent("date", record::getDate)
            .map(tradingVolume).toPropertyWhenPresent("tradingVolume", record::getTradingVolume)
            .map(transaction).toPropertyWhenPresent("transaction", record::getTransaction)
            .map(openingPrice).toPropertyWhenPresent("openingPrice", record::getOpeningPrice)
            .map(highestPrice).toPropertyWhenPresent("highestPrice", record::getHighestPrice)
            .map(lowestPrice).toPropertyWhenPresent("lowestPrice", record::getLowestPrice)
            .map(closingPrice).toPropertyWhenPresent("closingPrice", record::getClosingPrice)
            .map(turnover).toPropertyWhenPresent("turnover", record::getTurnover)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<StockTradeRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, stockTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<StockTradeRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, stockTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<StockTradeRecord> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, stockTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<StockTradeRecord> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, stockTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(StockTradeRecord record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(stockcode).equalTo(record::getStockcode)
                .set(date).equalTo(record::getDate)
                .set(tradingVolume).equalTo(record::getTradingVolume)
                .set(transaction).equalTo(record::getTransaction)
                .set(openingPrice).equalTo(record::getOpeningPrice)
                .set(highestPrice).equalTo(record::getHighestPrice)
                .set(lowestPrice).equalTo(record::getLowestPrice)
                .set(closingPrice).equalTo(record::getClosingPrice)
                .set(turnover).equalTo(record::getTurnover)
                .set(createdTime).equalTo(record::getCreatedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(StockTradeRecord record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(stockcode).equalToWhenPresent(record::getStockcode)
                .set(date).equalToWhenPresent(record::getDate)
                .set(tradingVolume).equalToWhenPresent(record::getTradingVolume)
                .set(transaction).equalToWhenPresent(record::getTransaction)
                .set(openingPrice).equalToWhenPresent(record::getOpeningPrice)
                .set(highestPrice).equalToWhenPresent(record::getHighestPrice)
                .set(lowestPrice).equalToWhenPresent(record::getLowestPrice)
                .set(closingPrice).equalToWhenPresent(record::getClosingPrice)
                .set(turnover).equalToWhenPresent(record::getTurnover)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(StockTradeRecord record) {
        return update(c ->
            c.set(stockcode).equalTo(record::getStockcode)
            .set(date).equalTo(record::getDate)
            .set(tradingVolume).equalTo(record::getTradingVolume)
            .set(transaction).equalTo(record::getTransaction)
            .set(openingPrice).equalTo(record::getOpeningPrice)
            .set(highestPrice).equalTo(record::getHighestPrice)
            .set(lowestPrice).equalTo(record::getLowestPrice)
            .set(closingPrice).equalTo(record::getClosingPrice)
            .set(turnover).equalTo(record::getTurnover)
            .set(createdTime).equalTo(record::getCreatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(StockTradeRecord record) {
        return update(c ->
            c.set(stockcode).equalToWhenPresent(record::getStockcode)
            .set(date).equalToWhenPresent(record::getDate)
            .set(tradingVolume).equalToWhenPresent(record::getTradingVolume)
            .set(transaction).equalToWhenPresent(record::getTransaction)
            .set(openingPrice).equalToWhenPresent(record::getOpeningPrice)
            .set(highestPrice).equalToWhenPresent(record::getHighestPrice)
            .set(lowestPrice).equalToWhenPresent(record::getLowestPrice)
            .set(closingPrice).equalToWhenPresent(record::getClosingPrice)
            .set(turnover).equalToWhenPresent(record::getTurnover)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}