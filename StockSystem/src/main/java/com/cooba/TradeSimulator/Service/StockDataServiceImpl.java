package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataAccess.StockTradeRecordDataLink;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Object.StockTradeRecordReq;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Service.Interface.StockDataDownloadService;
import com.cooba.TradeSimulator.Service.Interface.StockDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StockDataServiceImpl implements StockDataService {
    @Autowired
    SkipDateService skipDateService;
    @Autowired
    StockTradeRecordDataLink stockTradeRecordDataLink;
    @Autowired
    StockDownloadPriorityService stockDownloadPriorityService;

    @Override
    public StockTradeRecord getNowStockData(String stockcode) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = now.toLocalDate();

        if (now.isBefore(date.atTime(10, 0))) {
            date = date.minusDays(1);
        }

        if (skipDateService.isSkipDate(date)) {
            date = date.minusDays(1);
        }

        Optional<StockTradeRecord> optionalStockTradeRecord = stockTradeRecordDataLink
                .find(StockTradeRecordReq.builder().stockcode(stockcode).date(date).build())
                .stream()
                .findFirst();
        if (optionalStockTradeRecord.isPresent()) return optionalStockTradeRecord.get();

        List<StockDataDownloadService> downloadServices = stockDownloadPriorityService.getDownloadServiceList();
        for (StockDataDownloadService downloadService : downloadServices) {
            try {
                downloadService.downloadData(stockcode, LocalDate.now());
                break;
            } catch (Exception e) {
                log.error("Error downloading stock data Code:{}, Date:{}", stockcode, LocalDate.now());
            }
        }
        return stockTradeRecordDataLink
                .find(StockTradeRecordReq.builder().stockcode(stockcode).date(date).build())
                .stream()
                .findFirst().orElseThrow(Exception::new);
    }
}
