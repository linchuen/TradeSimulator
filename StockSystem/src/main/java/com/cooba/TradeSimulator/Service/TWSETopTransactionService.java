package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.SkipDateDB;
import com.cooba.TradeSimulator.Entity.SkipDate;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Entity.TopTransactionStock;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Service.Interface.TopTransactionService;
import com.cooba.TradeSimulator.Util.HttpCsvResponse;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.cooba.TradeSimulator.Util.RegexUtil;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TWSETopTransactionService implements TopTransactionService {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private SkipDateDB skipDateDB;

    public void downloadData(int year) throws IOException, CsvException {
        Map<String, String> map = new HashMap(Map.of(
                "response", "csv"));

        List<TopTransactionStock> topTransactionStocks = HttpCsvResponse.build(httpUtil)
                .sendHttpRequest("https://www.twse.com.tw/rwd/zh/afterTrading/MI_INDEX20", map)
                .readResponseByCSV()
                .transferRawData(dataRows -> transferFunction(dataRows));
        skipDateDB.insertAll(skipDateList);
    }

    private List<TopTransactionStock> transferFunction(List<String[]> dataRows) {
        if (dataRows.isEmpty()) {
            return Collections.emptyList();
        }
        String regex = "(\\d+)年(\\d+)月(\\d+)日成交量前二十名證券";
        String date = dataRows.get(0)[0];
        Matcher matcher = RegexUtil.getMatcher(date, regex);

        LocalDate localDate;
        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            localDate = LocalDate.of(year, month, day);
        } else {
            localDate = LocalDate.now();
        }

        dataRows = dataRows.subList(2, dataRows.size());
        return dataRows.stream().filter(strings -> strings.length == 13).map(strings -> {
            int rank = Integer.parseInt(strings[0]);

            String stockcode = strings[1];

            String name = strings[2];

            BigDecimal tradingVolume = new BigDecimal(strings[3]
                    .replace(",", ""));

            BigDecimal transaction = new BigDecimal(strings[4]
                    .replace(",", "")
                    .replace(",", ""));

            BigDecimal openingPrice = new BigDecimal(strings[5]
                    .replace(",", "")
                    .replace("--", "0"));

            BigDecimal highestPrice = new BigDecimal(strings[6]
                    .replace(",", "")
                    .replace("--", "0"));

            BigDecimal lowestPrice = new BigDecimal(strings[7]
                    .replace(",", "")
                    .replace("--", "0"));

            BigDecimal closingPrice = new BigDecimal(strings[8]
                    .replace(",", "")
                    .replace("--", "0"));

            BigDecimal turnover = new BigDecimal(strings[9]
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

    public boolean isSkipDate(LocalDate date) {
        List<DayOfWeek> weekend = Stream.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).collect(Collectors.toList());
        if (weekend.contains(date.getDayOfWeek())) {
            return true;
        }
        return skipDateDB.findByDate(date).isPresent();
    }
}

