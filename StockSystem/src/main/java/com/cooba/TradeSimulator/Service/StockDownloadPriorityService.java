package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Service.Interface.StockDataDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class StockDownloadPriorityService {
    @Autowired
    TWSEDataDownloadService twseDataDownloadService;
    @Autowired
    GoodInfoDataDownloadService goodInfoDataDownloadService;
    @Autowired
    AnueDataDownloadService anueDataDownloadService;
    @Autowired
    YahooDataDownloadService yahooDataDownloadService;

    final Map<Integer, StockDataDownloadService> downloadPriorityMap = new HashMap<>();
    final Map<StockDataDownloadService, Integer> downloadWeightMap = new HashMap<>();

    @PostConstruct
    public void init() {
        downloadPriorityMap.put(1, yahooDataDownloadService);
        downloadPriorityMap.put(2, anueDataDownloadService);
        downloadPriorityMap.put(3, goodInfoDataDownloadService);
        downloadPriorityMap.put(4, twseDataDownloadService);

        downloadWeightMap.put(yahooDataDownloadService, 50);
        downloadWeightMap.put(anueDataDownloadService, 25);
        downloadWeightMap.put(goodInfoDataDownloadService, 15);
        downloadWeightMap.put(twseDataDownloadService, 10);
    }

    public StockDataDownloadService getDownloadService(int priority) {
        return downloadPriorityMap.get(priority);
    }

    public StockDataDownloadService getDownloadServiceByWeight() {
        Random random = new SecureRandom();
        int rand = random.nextInt(downloadWeightMap.values().stream().reduce(0, Integer::sum)) + 1;
        for (Map.Entry<StockDataDownloadService, Integer> entry : downloadWeightMap.entrySet()) {
            rand = rand - entry.getValue();

            if (rand <= 0) return entry.getKey();
        }
        return null;
    }
}
