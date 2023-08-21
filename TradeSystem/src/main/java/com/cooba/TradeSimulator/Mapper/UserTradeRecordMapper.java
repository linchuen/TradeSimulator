package com.cooba.TradeSimulator.Mapper;

import static com.cooba.TradeSimulator.Mapper.UserTradeRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.cooba.TradeSimulator.Entity.UserTradeRecord;
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
public interface UserTradeRecordMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, billId, accountId, stockId, stockDate, price, amount, currencyId, money, status, errMsg, createdTime, updatedTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<UserTradeRecord> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserTradeRecord> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserTradeRecordResult")
    Optional<UserTradeRecord> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserTradeRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="bill_id", property="billId", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.INTEGER),
        @Result(column="stock_id", property="stockId", jdbcType=JdbcType.INTEGER),
        @Result(column="stock_date", property="stockDate", jdbcType=JdbcType.DATE),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="currency_id", property="currencyId", jdbcType=JdbcType.INTEGER),
        @Result(column="money", property="money", jdbcType=JdbcType.DECIMAL),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="err_msg", property="errMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UserTradeRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(UserTradeRecord record) {
        return MyBatis3Utils.insert(this::insert, record, userTradeRecord, c ->
            c.map(id).toProperty("id")
            .map(billId).toProperty("billId")
            .map(accountId).toProperty("accountId")
            .map(stockId).toProperty("stockId")
            .map(stockDate).toProperty("stockDate")
            .map(price).toProperty("price")
            .map(amount).toProperty("amount")
            .map(currencyId).toProperty("currencyId")
            .map(money).toProperty("money")
            .map(status).toProperty("status")
            .map(errMsg).toProperty("errMsg")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<UserTradeRecord> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userTradeRecord, c ->
            c.map(id).toProperty("id")
            .map(billId).toProperty("billId")
            .map(accountId).toProperty("accountId")
            .map(stockId).toProperty("stockId")
            .map(stockDate).toProperty("stockDate")
            .map(price).toProperty("price")
            .map(amount).toProperty("amount")
            .map(currencyId).toProperty("currencyId")
            .map(money).toProperty("money")
            .map(status).toProperty("status")
            .map(errMsg).toProperty("errMsg")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(UserTradeRecord record) {
        return MyBatis3Utils.insert(this::insert, record, userTradeRecord, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(billId).toPropertyWhenPresent("billId", record::getBillId)
            .map(accountId).toPropertyWhenPresent("accountId", record::getAccountId)
            .map(stockId).toPropertyWhenPresent("stockId", record::getStockId)
            .map(stockDate).toPropertyWhenPresent("stockDate", record::getStockDate)
            .map(price).toPropertyWhenPresent("price", record::getPrice)
            .map(amount).toPropertyWhenPresent("amount", record::getAmount)
            .map(currencyId).toPropertyWhenPresent("currencyId", record::getCurrencyId)
            .map(money).toPropertyWhenPresent("money", record::getMoney)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(errMsg).toPropertyWhenPresent("errMsg", record::getErrMsg)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserTradeRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserTradeRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserTradeRecord> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserTradeRecord> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userTradeRecord, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(UserTradeRecord record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(billId).equalTo(record::getBillId)
                .set(accountId).equalTo(record::getAccountId)
                .set(stockId).equalTo(record::getStockId)
                .set(stockDate).equalTo(record::getStockDate)
                .set(price).equalTo(record::getPrice)
                .set(amount).equalTo(record::getAmount)
                .set(currencyId).equalTo(record::getCurrencyId)
                .set(money).equalTo(record::getMoney)
                .set(status).equalTo(record::getStatus)
                .set(errMsg).equalTo(record::getErrMsg)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserTradeRecord record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(billId).equalToWhenPresent(record::getBillId)
                .set(accountId).equalToWhenPresent(record::getAccountId)
                .set(stockId).equalToWhenPresent(record::getStockId)
                .set(stockDate).equalToWhenPresent(record::getStockDate)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(amount).equalToWhenPresent(record::getAmount)
                .set(currencyId).equalToWhenPresent(record::getCurrencyId)
                .set(money).equalToWhenPresent(record::getMoney)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(errMsg).equalToWhenPresent(record::getErrMsg)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(UserTradeRecord record) {
        return update(c ->
            c.set(billId).equalTo(record::getBillId)
            .set(accountId).equalTo(record::getAccountId)
            .set(stockId).equalTo(record::getStockId)
            .set(stockDate).equalTo(record::getStockDate)
            .set(price).equalTo(record::getPrice)
            .set(amount).equalTo(record::getAmount)
            .set(currencyId).equalTo(record::getCurrencyId)
            .set(money).equalTo(record::getMoney)
            .set(status).equalTo(record::getStatus)
            .set(errMsg).equalTo(record::getErrMsg)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(UserTradeRecord record) {
        return update(c ->
            c.set(billId).equalToWhenPresent(record::getBillId)
            .set(accountId).equalToWhenPresent(record::getAccountId)
            .set(stockId).equalToWhenPresent(record::getStockId)
            .set(stockDate).equalToWhenPresent(record::getStockDate)
            .set(price).equalToWhenPresent(record::getPrice)
            .set(amount).equalToWhenPresent(record::getAmount)
            .set(currencyId).equalToWhenPresent(record::getCurrencyId)
            .set(money).equalToWhenPresent(record::getMoney)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(errMsg).equalToWhenPresent(record::getErrMsg)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}