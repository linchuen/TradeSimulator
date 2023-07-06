package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataAccess.StockTradeRecordDataLink;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Service.Interface.StockDataDownloadService;
import com.cooba.TradeSimulator.Util.DateUtil;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import okhttp3.Response;
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
    HttpUtil httpUtil;
    @Autowired
    StockTradeRecordDataLink stockTradeRecordDataLink;

    @Override
    public void downloadData(String stockcode, LocalDate localDate) throws IOException, CsvException {
            List<StockTradeRecord> stockTradeRecordList = sendHttpRequest(stockcode, localDate)
                    .readResponseByCSV()
                    .transferRawData();
            stockTradeRecordDataLink.saveAll(stockTradeRecordList);
    }

    private StockDataResponse sendHttpRequest(String stockcode, LocalDate localDate) {
        Map<String, String> map = new HashMap<>(Map.of(
                "response", "csv",
                "date", DateUtil.toFormatString(localDate),
                "stockNo", String.valueOf(stockcode)));
        Response response = httpUtil.httpGet("https://www.twse.com.tw/exchangeReport/STOCK_DAY", map);
        return new StockDataResponse(stockcode, response);
    }

    private static class StockDataResponse {
        private final String stockcode;
        private final Response response;
        private List<String[]> dataRows;

        public StockDataResponse(String stockcode, Response response) {
            this.stockcode = stockcode;
            this.response = response;
            dataRows = null;
        }

        public StockDataResponse readResponseByCSV() throws IOException, CsvException {
            CSVReader csvReader = new CSVReader(Objects.requireNonNull(this.response.body()).charStream());
            dataRows = csvReader.readAll();
            return this;
        }

        private List<StockTradeRecord> transferRawData() {
            if (dataRows.isEmpty()) {
                return Collections.emptyList();
            }

            dataRows.remove(1);
            return dataRows.stream().filter(strings -> strings.length == 10).map(strings -> {
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
                        .stockcode(this.stockcode)
                        .date(dataDate)
                        .year(y)
                        .month(m)
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
}