package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.StockTradeRecordDB;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Service.Interface.StockDataDownloadService;
import com.cooba.TradeSimulator.Util.DateUtil;
import com.cooba.TradeSimulator.Util.HttpCsvResponse;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TWSEDataDownloadService implements StockDataDownloadService {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private StockTradeRecordDB stockTradeRecordDB;

    @Override
    public void downloadData(String stockcode, LocalDate localDate) throws IOException, CsvException {
        Map<String, String> map = new HashMap<>(Map.of(
                "response", "csv",
                "date", DateUtil.toFormatString(localDate),
                "stockNo", String.valueOf(stockcode)));

        List<StockTradeRecord> stockTradeRecordList = HttpCsvResponse.build(httpUtil)
                .sendHttpRequest("https://www.twse.com.tw/exchangeReport/STOCK_DAY", map)
                .readResponseByCSV()
                .transferRawData(dataRows -> transferFunction(dataRows, stockcode));

        List<StockTradeRecord> dbList = stockTradeRecordDB.findByStockCodeAndMonth(stockcode, localDate.getYear(), localDate.getDayOfMonth());
        if (dbList.isEmpty()) {
            stockTradeRecordDB.insertAll(stockTradeRecordList);
            return;
        }

        Map<LocalDate, StockTradeRecord> dateMap = dbList.stream().collect(Collectors.toMap(StockTradeRecord::getDate, stockTradeRecord -> stockTradeRecord));
        for (StockTradeRecord stockTradeRecord : stockTradeRecordList) {
            StockTradeRecord dbRecord = dateMap.get(stockTradeRecord.getDate());
            if (dbRecord != null) {
                stockTradeRecord.setId(dbRecord.getId());
                stockTradeRecordDB.update(stockTradeRecord);
            } else {
                stockTradeRecordDB.insert(stockTradeRecord);
            }
        }
    }

    private List<StockTradeRecord> transferFunction(List<String[]> dataRows, String stockcode) {
        if (dataRows.isEmpty()) {
            return Collections.emptyList();
        }

        dataRows.remove(1);
        int totalDataColumn = 10;
        return dataRows.stream().filter(strings -> strings.length == totalDataColumn).map(strings -> {
            String[] dateArr = strings[0].split("/");
            int y = Integer.parseInt(dateArr[0]) + 1911;
            int m = Integer.parseInt(dateArr[1]);
            int d = Integer.parseInt(dateArr[2]);
            LocalDate dataDate = LocalDate.of(y, m, d);
            BigDecimal tradingVolume = new BigDecimal(strings[1]
                    .replace(",", ""));

            BigDecimal transaction = new BigDecimal(strings[2]
                    .replace(",", "")
                    .replace(",", ""));

            BigDecimal openingPrice = new BigDecimal(strings[3]
                    .replace(",", "")
                    .replace("--", "0"));

            BigDecimal highestPrice = new BigDecimal(strings[4]
                    .replace(",", "")
                    .replace("--", "0"));

            BigDecimal lowestPrice = new BigDecimal(strings[5]
                    .replace(",", "")
                    .replace("--", "0"));

            BigDecimal closingPrice = new BigDecimal(strings[6]
                    .replace(",", "")
                    .replace("--", "0"));

            BigDecimal turnover = new BigDecimal(strings[8]
                    .replace(",", ""));

            return StockTradeRecord.builder()
                    .stockcode(stockcode)
                    .date(dataDate)
                    .tradingVolume(tradingVolume)
                    .transaction(transaction)
                    .openingPrice(openingPrice)
                    .highestPrice(highestPrice)
                    .lowestPrice(lowestPrice)
                    .closingPrice(closingPrice)
                    .turnover(turnover)
                    .build();
        }).collect(Collectors.toList());
    }
}
