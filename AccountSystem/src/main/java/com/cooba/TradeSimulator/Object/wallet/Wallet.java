package com.cooba.TradeSimulator.Object.wallet;

import com.cooba.TradeSimulator.Object.Asset;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Wallet {

    private Map<String, Asset> assets;

    public void setAssets(List<Asset> assets) {
        this.assets = assets.stream().collect(Collectors.toMap(Asset::getType, asset -> asset));
    }

    public Map<String, Asset> getAssets() {
        return this.assets;
    }
}
