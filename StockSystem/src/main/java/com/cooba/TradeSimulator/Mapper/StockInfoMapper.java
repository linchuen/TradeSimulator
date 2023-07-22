package com.cooba.TradeSimulator.Mapper;

import static com.cooba.TradeSimulator.Mapper.StockInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.cooba.TradeSimulator.Entity.StockInfo;
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
public interface StockInfoMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, stockcode, name, isinCode, publishDate, marketType, industryType, updatedTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<StockInfo> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<StockInfo> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("StockInfoResult")
    Optional<StockInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="StockInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="stockcode", property="stockcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="ISIN_code", property="isinCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="publish_date", property="publishDate", jdbcType=JdbcType.DATE),
        @Result(column="market_type", property="marketType", jdbcType=JdbcType.VARCHAR),
        @Result(column="industry_type", property="industryType", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<StockInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, stockInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, stockInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(StockInfo record) {
        return MyBatis3Utils.insert(this::insert, record, stockInfo, c ->
            c.map(id).toProperty("id")
            .map(stockcode).toProperty("stockcode")
            .map(name).toProperty("name")
            .map(isinCode).toProperty("isinCode")
            .map(publishDate).toProperty("publishDate")
            .map(marketType).toProperty("marketType")
            .map(industryType).toProperty("industryType")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<StockInfo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, stockInfo, c ->
            c.map(id).toProperty("id")
            .map(stockcode).toProperty("stockcode")
            .map(name).toProperty("name")
            .map(isinCode).toProperty("isinCode")
            .map(publishDate).toProperty("publishDate")
            .map(marketType).toProperty("marketType")
            .map(industryType).toProperty("industryType")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(StockInfo record) {
        return MyBatis3Utils.insert(this::insert, record, stockInfo, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(stockcode).toPropertyWhenPresent("stockcode", record::getStockcode)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(isinCode).toPropertyWhenPresent("isinCode", record::getIsinCode)
            .map(publishDate).toPropertyWhenPresent("publishDate", record::getPublishDate)
            .map(marketType).toPropertyWhenPresent("marketType", record::getMarketType)
            .map(industryType).toPropertyWhenPresent("industryType", record::getIndustryType)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<StockInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, stockInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<StockInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, stockInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<StockInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, stockInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<StockInfo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, stockInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(StockInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(stockcode).equalTo(record::getStockcode)
                .set(name).equalTo(record::getName)
                .set(isinCode).equalTo(record::getIsinCode)
                .set(publishDate).equalTo(record::getPublishDate)
                .set(marketType).equalTo(record::getMarketType)
                .set(industryType).equalTo(record::getIndustryType)
                .set(updatedTime).equalTo(record::getUpdatedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(StockInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(stockcode).equalToWhenPresent(record::getStockcode)
                .set(name).equalToWhenPresent(record::getName)
                .set(isinCode).equalToWhenPresent(record::getIsinCode)
                .set(publishDate).equalToWhenPresent(record::getPublishDate)
                .set(marketType).equalToWhenPresent(record::getMarketType)
                .set(industryType).equalToWhenPresent(record::getIndustryType)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(StockInfo record) {
        return update(c ->
            c.set(stockcode).equalTo(record::getStockcode)
            .set(name).equalTo(record::getName)
            .set(isinCode).equalTo(record::getIsinCode)
            .set(publishDate).equalTo(record::getPublishDate)
            .set(marketType).equalTo(record::getMarketType)
            .set(industryType).equalTo(record::getIndustryType)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(StockInfo record) {
        return update(c ->
            c.set(stockcode).equalToWhenPresent(record::getStockcode)
            .set(name).equalToWhenPresent(record::getName)
            .set(isinCode).equalToWhenPresent(record::getIsinCode)
            .set(publishDate).equalToWhenPresent(record::getPublishDate)
            .set(marketType).equalToWhenPresent(record::getMarketType)
            .set(industryType).equalToWhenPresent(record::getIndustryType)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}