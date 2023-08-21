package com.cooba.TradeSimulator.Object.wallet;

import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CurrencyWallet extends Wallet<CurrencyAsset> {

}
