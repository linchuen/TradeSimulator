package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataAccess.StockTradeRecordDataLink;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Service.Interface.StockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StockDataServiceImpl implements StockDataService {
    @Autowired
    SkipDateService skipDateService;
    @Autowired
    StockTradeRecordDataLink stockTradeRecordDataLink;
    @Autowired
    StockDownloadPriorityService stockDownloadPriorityService;

    @Override
    public StockTradeRecord getNowStockData(String stockcode) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = now.toLocalDate();

        if (now.isBefore(date.atTime(10, 0))) {
            date = date.minusDays(1);
        }

        if (skipDateService.isSkipDate(date)) {
            date = date.minusDays(1);
        }

        Optional<StockTradeRecord> optionalStockTradeRecord = stockTradeRecordDataLink
                .find(StockTradeRecord.builder().stockcode(stockcode).date(date).build())
                .stream()
                .findFirst();
        if (optionalStockTradeRecord.isPresent()) return optionalStockTradeRecord.get();


        return null;
    }
}
