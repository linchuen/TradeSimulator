package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.StockTradeRecordDataAccess;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Service.Interface.StockDataDownloadService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AnueDataDownloadService implements StockDataDownloadService {
    @Autowired
    StockTradeRecordDataAccess stockTradeRecordDataAccess;
    @Autowired
    SkipDateService skipDateService;

    @Override
    public void downloadData(String stockcode, LocalDate localDate) throws IOException {
        if (skipDateService.isSkipDate(localDate)) return;

        if (LocalDateTime.now().isBefore(localDate.atTime(10, 0))) {
            localDate = LocalDate.now().minusDays(1);
        } else {
            localDate = LocalDate.now();
        }

        final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36";

        String stockUrl = String.format("https://invest.cnyes.com/twstock/TWS/%s", stockcode);
        Document doc = Jsoup.connect(stockUrl)
                .userAgent(UA)
                .referrer("http://www.google.com")
                .get();
        String price = doc.selectXpath(String.format("//*[@id='_profile-TWS:%s:STOCK']/div[2]/div[2]/div/div[6]/div[2]", stockcode)).text();
        String lastprice = doc.selectXpath(String.format("//*[@id='_profile-TWS:%s:STOCK']/div[2]/div[2]/div/div[4]/div[2]", stockcode)).text();
        String open = doc.selectXpath(String.format("//*[@id='_profile-TWS:%s:STOCK']/div[2]/div[2]/div/div[5]/div[2]", stockcode)).text();
        String highest = doc.selectXpath(String.format("//*[@id='_profile-TWS:%s:STOCK']/div[2]/div[2]/div/div[2]/div[2]", stockcode)).text().split("- ")[1];
        String lowest = doc.selectXpath(String.format("//*[@id='_profile-TWS:%s:STOCK']/div[2]/div[2]/div/div[2]/div[2]", stockcode)).text().split("- ")[0];
        String tradingVolume = doc.selectXpath(String.format("//*[@id='_profile-TWS:%s:STOCK']/div[2]/div[2]/div/div[1]/div[2]", stockcode)).text().replaceAll("[, 張]", "");
        String createdTime = LocalDate.now().getYear() + "/" + doc.selectXpath(String.format("//*[@id='_profile-TWS:%s:STOCK']/div[1]/div[2]/time", stockcode)).text().split(" ")[0];

        StockTradeRecord stockTradeRecord = StockTradeRecord.builder()
                .stockcode(stockcode)
                .date(localDate)
                .closingPrice(new BigDecimal(price))
                .openingPrice(new BigDecimal(open))
                .highestPrice(new BigDecimal(highest))
                .lowestPrice(new BigDecimal(lowest))
                .tradingVolume(new BigDecimal(tradingVolume))
                .build();

        stockTradeRecordDataAccess.insert(stockTradeRecord);
    }
}
