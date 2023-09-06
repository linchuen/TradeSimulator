package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.TopTransactionDB;
import com.cooba.TradeSimulator.Entity.TopTransactionStock;
import com.cooba.TradeSimulator.Exception.DownloadException;
import com.cooba.TradeSimulator.Service.Interface.TopTransactionService;
import com.cooba.TradeSimulator.Util.HttpCsvResponse;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.cooba.TradeSimulator.Util.RegexUtil;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TWSETopTransactionService implements TopTransactionService {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private TopTransactionDB topTransactionDB;

    public void downloadData() throws IOException, CsvException {
        Map<String, String> map = Map.of("response", "csv");

        List<TopTransactionStock> topTransactionStocks = HttpCsvResponse.build(httpUtil)
                .sendHttpRequest("https://www.twse.com.tw/rwd/zh/afterTrading/MI_INDEX20", map)
                .readResponseByCSV()
                .transferRawData(this::transferFunction);

        topTransactionDB.insertAll(topTransactionStocks);
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
            int year = Integer.parseInt(matcher.group(1)) + 1911;
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            localDate = LocalDate.of(year, month, day);
        } else {
            localDate = LocalDate.now();
        }

        dataRows = dataRows.subList(2, dataRows.size());
        int totalDataColumn = 14;
        return dataRows.stream().filter(strings -> strings.length == totalDataColumn).map(strings -> {
            int rank = Integer.parseInt(strings[0]);

            String stockcode = strings[1];

            String name = strings[2];

            BigDecimal tradingVolume = new BigDecimal(strings[3]
                    .replace(",", ""));

            BigDecimal transaction = new BigDecimal(strings[4]
                    .replace(",", ""));

            BigDecimal openingPrice = new BigDecimal(strings[5]
                    .replace(",", ""));

            BigDecimal highestPrice = new BigDecimal(strings[6]
                    .replace(",", ""));

            BigDecimal lowestPrice = new BigDecimal(strings[7]
                    .replace(",", ""));

            BigDecimal closingPrice = new BigDecimal(strings[8]
                    .replace(",", ""));

            return TopTransactionStock.builder()
                    .rank(rank)
                    .name(name)
                    .stockcode(stockcode)
                    .date(localDate)
                    .tradingVolume(tradingVolume)
                    .transaction(transaction)
                    .openingPrice(openingPrice)
                    .highestPrice(highestPrice)
                    .lowestPrice(lowestPrice)
                    .closingPrice(closingPrice)
                    .build();
        }).collect(Collectors.toList());
    }

    public List<TopTransactionStock> getTodayTopTransactionStock() throws DownloadException {
        List<TopTransactionStock> topTransactionStocks = topTransactionDB.findByDate(LocalDate.now());
        if (!topTransactionStocks.isEmpty()){
            return topTransactionStocks;
        }
        try {
            downloadData();
        }catch (Exception e) {
            log.error("Error downloading top transaction data, Date:{}", LocalDate.now());
            throw new DownloadException();
        }
        return topTransactionDB.findByDate(LocalDate.now());
    }
}

