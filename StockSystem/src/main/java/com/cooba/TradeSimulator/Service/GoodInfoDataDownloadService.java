package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataAccess.StockTradeRecordDataLink;
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

@Service
public class GoodInfoDataDownloadService implements StockDataDownloadService {
    @Autowired
    StockTradeRecordDataLink stockTradeRecordDataLink;
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

        String stockUrl = String.format("https://goodinfo.tw/tw/StockDetail.asp?STOCK_ID=%s", stockcode);

        Document doc = Jsoup.connect(stockUrl)
                .userAgent(UA)
                .referrer("http://www.google.com")
                .get();
        Element infotable = doc.getElementsByClass("b1 p4_4 r10").get(0);
        String industryType = infotable.select("tbody > tr:nth-child(3) > td:nth-child(2)").text();
        String companyType = infotable.select(" tbody > tr:nth-child(4) > td:nth-child(2) > nobr").text();
        Element table = doc.getElementsByClass("b1 p4_2 r10").get(0);
        String name = table.select("tbody > tr > td:nth-child(1) > nobr > a").text().split(" ")[1];
        String price = table.select("tbody > tr:nth-child(3) > td:nth-child(1)").text();
        String lastprice = table.select("tbody > tr:nth-child(3) > td:nth-child(2)").text();
        String open = table.select("tbody > tr:nth-child(3) > td:nth-child(6)").text();
        String highest = table.select("tbody > tr:nth-child(3) > td:nth-child(7)").text();
        String lowest = table.select("tbody > tr:nth-child(3) > td:nth-child(8)").text();
        String tradingVolume = table.select(" tbody > tr:nth-child(5) > td:nth-child(3)").text().replace(",", "");
        String createdTime = LocalDate.now().getYear() + "/" + table.select("tbody > tr > td:nth-child(5) > nobr").text().split(" ")[1];

        StockTradeRecord stockTradeRecord = StockTradeRecord.builder()
                .stockcode(stockcode)
                .date(localDate)
                .closingPrice(new BigDecimal(price))
                .openingPrice(new BigDecimal(open))
                .highestPrice(new BigDecimal(highest))
                .lowestPrice(new BigDecimal(lowest))
                .tradingVolume(new BigDecimal(tradingVolume))
                .build();

        stockTradeRecordDataLink.insert(stockTradeRecord);
    }
}
