package com.cooba.TradeSimulator.Mapper;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class StockInfoDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final StockInfo stockInfo = new StockInfo();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = stockInfo.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> stockcode = stockInfo.stockcode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = stockInfo.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> isinCode = stockInfo.isinCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDate> publishDate = stockInfo.publishDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> marketType = stockInfo.marketType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> industryType = stockInfo.industryType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> updatedTime = stockInfo.updatedTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class StockInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> stockcode = column("stockcode", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> isinCode = column("ISIN_code", JDBCType.VARCHAR);

        public final SqlColumn<LocalDate> publishDate = column("publish_date", JDBCType.DATE);

        public final SqlColumn<String> marketType = column("market_type", JDBCType.VARCHAR);

        public final SqlColumn<String> industryType = column("industry_type", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public StockInfo() {
            super("stock_info");
        }
    }
}