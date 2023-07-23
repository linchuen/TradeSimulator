package com.cooba.TradeSimulator.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStockWallet {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer accountId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer stockId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal amount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime createdTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime updatedTime;
}