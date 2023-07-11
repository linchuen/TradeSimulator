package com.cooba.TradeSimulator.Object;

import com.cooba.TradeSimulator.Entity.SkipDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class SkipDateReq extends SkipDate implements Request {

}