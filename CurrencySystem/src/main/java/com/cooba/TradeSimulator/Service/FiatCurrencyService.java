package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.CurrencyDataAccess;
import com.cooba.TradeSimulator.Entity.Currency;
import com.cooba.TradeSimulator.Service.Interface.CurrencyService;
import com.cooba.TradeSimulator.Util.HttpCsvResponse;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FiatCurrencyService implements CurrencyService {
    private final HttpUtil httpUtil;
    private final CurrencyDataAccess currencyDataAccess;

    public void downloadCurrencyData() throws IOException, CsvException {
        List<Currency> dbCurrencyList = currencyDataAccess.selectAll();

        List<Currency> currencyList = HttpCsvResponse.build(httpUtil)
                .sendHttpRequest("https://rate.bot.com.tw/xrt/flcsv/0/2023-08-08", Collections.emptyMap())
                .readResponseByCSV()
                .transferRawData(this::transferFunction);

        Map<String, Currency> keyMap = dbCurrencyList.stream().collect(Collectors.toMap(Currency::getName, currency -> currency));
        for (Currency currency : currencyList) {
            String name = currency.getName();

            if (keyMap.containsKey(name)) {
                currencyDataAccess.update(currency);
            } else {
                currencyDataAccess.insert(currency);
            }
        }
    }

    public List<Currency> transferFunction(List<String[]> dataRows) {
        if (dataRows.isEmpty()) {
            return Collections.emptyList();
        }

        dataRows.remove(0);

        return dataRows.stream().map(data -> {
            BigDecimal buyIn = new BigDecimal(data[2]);
            BigDecimal soldOut = new BigDecimal(data[12]);

            return Currency.builder()
                    .name(data[0])
                    .rate(buyIn.add(soldOut).divide(BigDecimal.valueOf(2), 5, RoundingMode.FLOOR))
                    .build();
        }).collect(Collectors.toList());
    }


}
