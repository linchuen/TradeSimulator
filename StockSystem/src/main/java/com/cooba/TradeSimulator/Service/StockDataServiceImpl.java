package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.StockInfoDB;
import com.cooba.TradeSimulator.DataLayer.StockTradeRecordDB;
import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Exception.DownloadException;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Service.Interface.StockDataDownloadService;
import com.cooba.TradeSimulator.Service.Interface.StockDataService;
import com.cooba.TradeSimulator.Util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class StockDataServiceImpl implements StockDataService {
    @Autowired
    private SkipDateService skipDateService;
    @Autowired
    private StockTradeRecordDB stockTradeRecordDB;
    @Autowired
    private StockDownloadPriorityService stockDownloadPriorityService;
    @Autowired
    private StockInfoDB stockInfoDB;

    private final Map<Integer, String> stockCodeMap = new HashMap<>();

    @PostConstruct
    public void init() {
        List<StockInfo> stockInfos = stockInfoDB.findAll();
        for (StockInfo stockInfo : stockInfos) {
            stockCodeMap.put(stockInfo.getId(), stockInfo.getStockcode());
        }
    }

    @Override
    public StockTradeRecord getTodayStockData(String stockcode) throws Exception {
        LocalDate date = findLastTradeDate();

        Optional<StockTradeRecord> optionalStockTradeRecord = findTradeRecordByDate(stockcode, date);
        if (optionalStockTradeRecord.isPresent()) {
            return optionalStockTradeRecord.get();
        }

        downloadStockData(stockcode);
        return findTradeRecordByDate(stockcode, date).orElseThrow(DownloadException::new);
    }

    @Override
    public StockTradeRecord getTodayStockData(Integer stockId) throws Exception {
        String stockCode = stockCodeMap.get(stockId);
        return getTodayStockData(stockCode);
    }

    @NotNull
    private Optional<StockTradeRecord> findTradeRecordByDate(String stockcode, LocalDate date) {
        return stockTradeRecordDB.findByStockCodeAndDate(stockcode, date);
    }

    @NotNull
    private LocalDate findLastTradeDate() {
        LocalDateTime now = DateUtil.now();
        LocalDate date = now.toLocalDate();

        if (now.isBefore(date.atTime(10, 0))) {
            date = date.minusDays(1);
        }

        while (skipDateService.isSkipDate(date)) {
            date = date.minusDays(1);
        }
        return date;
    }

    private void downloadStockData(String stockcode) {
        List<StockDataDownloadService> downloadServices = stockDownloadPriorityService.getDownloadPriorityList();
        for (StockDataDownloadService downloadService : downloadServices) {
            try {
                downloadService.downloadData(stockcode, LocalDate.now());
                break;
            } catch (Exception e) {
                log.error("Error downloading stock data Code:{}, Date:{}", stockcode, LocalDate.now());
            }
        }
    }
}
