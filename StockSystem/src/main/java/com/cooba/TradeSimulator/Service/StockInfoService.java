package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.StockInfoDataAccess;
import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Util.SSLHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockInfoService {
    @Autowired
    private StockInfoDataAccess stockInfoDataAccess;

    public void crawlIndustry() throws IOException {
        String siteurl = "https://isin.twse.com.tw/isin/C_public.jsp?strMode=2";
        List<StockInfo> stockInfoList = new ArrayList<>();

        Document doc = Jsoup.connect(siteurl).sslSocketFactory(SSLHelper.socketFactory()).get();
        Elements elements = doc.select("tr");
        elements.remove(0);
        elements.remove(0);
        for (Element element : elements) {
            if (element.childNodeSize() == 1) break;
            String stockcode = element.child(0).text().replace(" ", "").split("　")[0];
            String name = element.child(0).text().split("　")[1];
            String ISINCode = element.child(1).text();
            LocalDate publishDate = LocalDate.parse(element.child(2).text(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String marketType = element.child(3).text();
            String industryType = element.child(4).text();

            StockInfo stockInfo = StockInfo.builder()
                    .stockcode(stockcode)
                    .name(name)
                    .isinCode(ISINCode)
                    .publishDate(publishDate)
                    .marketType(marketType)
                    .industryType(industryType)
                    .build();

            stockInfoList.add(stockInfo);
        }
        stockInfoDataAccess.insertAll(stockInfoList);
    }

    public List<StockInfo> findAllStockInfo() {
        return stockInfoDataAccess.findAll();
    }

    public Optional<StockInfo> findStockInfo(int id) {
        return stockInfoDataAccess.findById(id);
    }
}
