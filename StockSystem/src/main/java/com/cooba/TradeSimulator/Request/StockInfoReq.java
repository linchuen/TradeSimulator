package com.cooba.TradeSimulator.Request;

import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Object.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StockInfoReq extends StockInfo implements Request {

}