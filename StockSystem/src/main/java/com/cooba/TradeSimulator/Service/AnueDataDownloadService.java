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
    private StockTradeRecordDataAccess stockTradeRecordDataAccess;
    @Autowired
    private SkipDateService skipDateService;

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
        String price = doc.selectXpath("//*[@id=\"anue-ga-wrapper\"]/div[4]/div[2]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/h3").text();
        String lastPrice = doc.selectXpath("//*[@id=\"anue-ga-wrapper\"]/div[4]/div[2]/div[1]/div[1]/div[1]/div/div[3]/div[2]/ul[1]/li[4]/span[2]").text();
        String open = doc.selectXpath("//*[@id=\"anue-ga-wrapper\"]/div[4]/div[2]/div[1]/div[1]/div[1]/div/div[3]/div[2]/ul[1]/li[1]/span[2]").text();
        String highest = doc.selectXpath("//*[@id=\"anue-ga-wrapper\"]/div[4]/div[2]/div[1]/div[1]/div[1]/div/div[3]/div[2]/ul[1]/li[2]/span[2]").text();
        String lowest = doc.selectXpath("//*[@id=\"anue-ga-wrapper\"]/div[4]/div[2]/div[1]/div[1]/div[1]/div/div[3]/div[2]/ul[1]/li[5]/span[2]").text();
        String tradingVolume = doc.selectXpath("//*[@id=\"anue-ga-wrapper\"]/div[4]/div[2]/div[1]/div[1]/div[1]/div/div[3]/div[2]/ul[1]/li[3]/span[2]").text().replaceAll(",","");
        String createdTime = LocalDate.now().getYear() + "/" + doc.selectXpath("//*[@id=\"anue-ga-wrapper\"]/div[4]/div[2]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/span[2]").text().split(" ")[0];

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
