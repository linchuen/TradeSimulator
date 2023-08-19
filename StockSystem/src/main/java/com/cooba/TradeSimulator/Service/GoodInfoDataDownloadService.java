package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.StockTradeRecordDataAccess;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Service.Interface.StockDataDownloadService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GoodInfoDataDownloadService implements StockDataDownloadService {
    @Autowired
    private StockTradeRecordDataAccess stockTradeRecordDataAccess;

    @Override
    public void downloadData(String stockcode, LocalDate localDate) throws IOException {
        final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36";

        String stockUrl = String.format("https://goodinfo.tw/tw/StockDetail.asp?STOCK_ID=%s", stockcode);

        Document doc = Jsoup.connect(stockUrl)
                .userAgent(UA)
                .referrer("http://www.google.com")
                .get();
        Element table = doc.select("body > table:nth-child(8) > tbody > tr > td:nth-child(3) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(1) > table").get(0);
        String price = table.select("tbody > tr:nth-child(3) > td:nth-child(1)").text();
        String lastprice = table.select("tbody > tr:nth-child(3) > td:nth-child(2)").text();
        String open = table.select("tbody > tr:nth-child(3) > td:nth-child(6)").text();
        String highest = table.select("tbody > tr:nth-child(3) > td:nth-child(7)").text();
        String lowest = table.select("tbody > tr:nth-child(3) > td:nth-child(8)").text();
        String tradingVolume = table.select(" tbody > tr:nth-child(5) > td:nth-child(3)").text().replace(",", "");
        String createdTime = LocalDate.now().getYear() + "/" + table.select("tbody > tr > td:nth-child(5) > nobr").text().split(" ")[1];

        StockTradeRecord stockTradeRecord = StockTradeRecord.builder()
                .stockcode(stockcode)
                .date(LocalDate.parse(createdTime, DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .closingPrice(new BigDecimal(price))
                .openingPrice(new BigDecimal(open))
                .highestPrice(new BigDecimal(highest))
                .lowestPrice(new BigDecimal(lowest))
                .tradingVolume(new BigDecimal(tradingVolume))
                .build();

        stockTradeRecordDataAccess.insert(stockTradeRecord);
    }
}
