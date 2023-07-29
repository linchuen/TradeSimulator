package com.cooba.TradeSimulator.Mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class StockTradeRecordDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final StockTradeRecord stockTradeRecord = new StockTradeRecord();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = stockTradeRecord.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> stockcode = stockTradeRecord.stockcode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDate> date = stockTradeRecord.date;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> tradingVolume = stockTradeRecord.tradingVolume;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> transaction = stockTradeRecord.transaction;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> openingPrice = stockTradeRecord.openingPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> highestPrice = stockTradeRecord.highestPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> lowestPrice = stockTradeRecord.lowestPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> closingPrice = stockTradeRecord.closingPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> turnover = stockTradeRecord.turnover;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> createdTime = stockTradeRecord.createdTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class StockTradeRecord extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> stockcode = column("stockcode", JDBCType.VARCHAR);

        public final SqlColumn<LocalDate> date = column("date", JDBCType.DATE);

        public final SqlColumn<BigDecimal> tradingVolume = column("trading_volume", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> transaction = column("transaction", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> openingPrice = column("opening_price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> highestPrice = column("highest_price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> lowestPrice = column("lowest_price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> closingPrice = column("closing_price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> turnover = column("turnover", JDBCType.DECIMAL);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public StockTradeRecord() {
            super("stock_trade_record");
        }
    }
}