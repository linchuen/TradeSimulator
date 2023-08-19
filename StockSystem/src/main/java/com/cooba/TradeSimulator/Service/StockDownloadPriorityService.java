package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Service.Interface.StockDataDownloadService;
import com.cooba.TradeSimulator.Util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class StockDownloadPriorityService {
    @Autowired
    private TWSEDataDownloadService twseDataDownloadService;
    @Autowired
    private GoodInfoDataDownloadService goodInfoDataDownloadService;
    @Autowired
    private AnueDataDownloadService anueDataDownloadService;
    @Autowired
    private YahooDataDownloadService yahooDataDownloadService;

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
        return RandomUtil.randomByWeight(downloadWeightMap);
    }
}
