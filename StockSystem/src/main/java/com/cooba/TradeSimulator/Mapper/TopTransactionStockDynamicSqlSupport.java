package com.cooba.TradeSimulator.Mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TopTransactionStockDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TopTransactionStock topTransactionStock = new TopTransactionStock();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = topTransactionStock.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> rank = topTransactionStock.rank;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> stockcode = topTransactionStock.stockcode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = topTransactionStock.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDate> date = topTransactionStock.date;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> tradingVolume = topTransactionStock.tradingVolume;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> transaction = topTransactionStock.transaction;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> openingPrice = topTransactionStock.openingPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> highestPrice = topTransactionStock.highestPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> lowestPrice = topTransactionStock.lowestPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> closingPrice = topTransactionStock.closingPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> turnover = topTransactionStock.turnover;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> createdTime = topTransactionStock.createdTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TopTransactionStock extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> rank = column("rank", JDBCType.INTEGER);

        public final SqlColumn<String> stockcode = column("stockcode", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<LocalDate> date = column("date", JDBCType.DATE);

        public final SqlColumn<BigDecimal> tradingVolume = column("trading_volume", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> transaction = column("transaction", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> openingPrice = column("opening_price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> highestPrice = column("highest_price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> lowestPrice = column("lowest_price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> closingPrice = column("closing_price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> turnover = column("turnover", JDBCType.DECIMAL);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public TopTransactionStock() {
            super("top_transaction_stock");
        }
    }
}