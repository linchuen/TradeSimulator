package com.cooba.TradeSimulator.Mapper.generated;

import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Entity.StockInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockInfoMapper {
    long countByExample(StockInfoExample example);

    int deleteByExample(StockInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StockInfo record);

    int insertSelective(StockInfo record);

    List<StockInfo> selectByExample(StockInfoExample example);

    StockInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StockInfo record, @Param("example") StockInfoExample example);

    int updateByExample(@Param("record") StockInfo record, @Param("example") StockInfoExample example);

    int updateByPrimaryKeySelective(StockInfo record);

    int updateByPrimaryKey(StockInfo record);
}