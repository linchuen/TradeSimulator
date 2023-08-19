package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.StockTradeRecordDataAccess;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Service.Interface.StockDataDownloadService;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class YahooDataDownloadService implements StockDataDownloadService {
    @Autowired
    private StockTradeRecordDataAccess stockTradeRecordDataAccess;

    @Override
    public void downloadData(String stockcode, LocalDate localDate) {
        final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36";
        try {
            String stockUrl = String.format("https://tw.stock.yahoo.com/quote/%s", stockcode);
            Document doc = Jsoup.connect(stockUrl)
                    .userAgent(UA)
                    .referrer("http://www.google.com")
                    .get();
            String createdTime = doc.select("#qsp-overview-realtime-info > div:nth-child(1) > span > time > span:nth-child(2)").text().split(" ")[0];
            Element table = doc.select("#qsp-overview-realtime-info > div:nth-child(2) > div > div > ul").get(0);
            String price = table.select("li:nth-child(1) > span:nth-child(2)").text();
            String lastprice = table.select("li:nth-child(7) > span:nth-child(2)").text();
            String open = table.select("li:nth-child(2) > span:nth-child(2)").text();
            String highest = table.select("li:nth-child(3) > span:nth-child(2)").text();
            String lowest = table.select("li:nth-child(4) > span:nth-child(2)").text();
            String tradingVolume = table.select(" li:nth-child(10) > span:nth-child(2)").text().replace(",", "");

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
        } catch (Exception e) {

        }
    }
}
