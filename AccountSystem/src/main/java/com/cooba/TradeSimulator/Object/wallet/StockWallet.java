package com.cooba.TradeSimulator.Object.wallet;

import com.cooba.TradeSimulator.Object.Wallet;
import com.cooba.TradeSimulator.Object.asset.StockInfoAsset;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StockWallet extends Wallet<StockInfoAsset> {
}
