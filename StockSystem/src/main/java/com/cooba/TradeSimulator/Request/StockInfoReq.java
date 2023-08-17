package com.cooba.TradeSimulator.Request;

import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Object.Request;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@Data
@Builder
public class StockInfoReq implements Request {
    private StockInfo stockInfo;
}