package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Service.Interface.StockDataDownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockDownloadPriorityService {
    private final TWSEDataDownloadService twseDataDownloadService;
    private final GoodInfoDataDownloadService goodInfoDataDownloadService;
    private final AnueDataDownloadService anueDataDownloadService;
    private final YahooDataDownloadService yahooDataDownloadService;

    final Map<Integer, StockDataDownloadService> downloadPriorityMap = new HashMap<>();
    List<StockDataDownloadService> downloadPriorityList;
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

        downloadPriorityList = downloadPriorityMap.entrySet().stream()
                .sorted((Map.Entry.comparingByKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<StockDataDownloadService> getDownloadPriorityList() {
        return downloadPriorityList;
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
