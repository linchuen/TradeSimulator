package com.cooba.TradeSimulator.Object;

import com.cooba.TradeSimulator.Entity.StockInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StockInfoReq extends StockInfo implements Request {

}