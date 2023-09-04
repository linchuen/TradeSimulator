package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.CurrencyDB;
import com.cooba.TradeSimulator.Entity.Currency;
import com.cooba.TradeSimulator.Enum.DefaultCurrency;
import com.cooba.TradeSimulator.Exception.NotSupportCurrencyException;
import com.cooba.TradeSimulator.Service.Interface.CurrencyService;
import com.cooba.TradeSimulator.Util.HttpCsvResponse;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FiatCurrencyService implements CurrencyService {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private CurrencyDB currencyDB;

    public void downloadCurrencyData() throws IOException, CsvException {
        LocalDate today = LocalDate.now();
        String todayStr = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<Currency> dbCurrencyList = currencyDB.selectAll();

        List<Currency> currencyList = HttpCsvResponse.build(httpUtil)
                .sendHttpRequest("https://rate.bot.com.tw/xrt/flcsv/0/" + todayStr, Collections.emptyMap())
                .readResponseByCSV()
                .transferRawData(this::transferFunction);

        currencyList.add(Currency.builder().id(1).name(DefaultCurrency.TWD.getName()).rate(BigDecimal.ONE).build());
        Map<String, Currency> keyMap = dbCurrencyList.stream().collect(Collectors.toMap(Currency::getName, currency -> currency));

        Map<String, DefaultCurrency> defaultCurrencyMap = DefaultCurrency.getCurrencyNameMap();
        Map<Boolean, List<Currency>> isDefaultCurrencyMap = currencyList.stream().collect(Collectors.groupingBy(currency -> defaultCurrencyMap.containsKey(currency.getName())));

        //make sure default currency order is correct
        upsertCurrency(isDefaultCurrencyMap.get(true), keyMap);
        upsertCurrency(isDefaultCurrencyMap.get(false), keyMap);
    }

    private void upsertCurrency(List<Currency> currencyList, Map<String, Currency> keyMap) {
        for (Currency currency : currencyList) {
            String name = currency.getName();

            if (keyMap.containsKey(name)) {
                currencyDB.update(currency);
            } else {
                currencyDB.insert(currency);
            }
        }
    }

    private List<Currency> transferFunction(List<String[]> dataRows) {
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

    public Currency getCurrencyInfo(Integer currencyId) throws NotSupportCurrencyException, IOException, CsvException {
        Optional<Currency> currencyOptional = currencyDB.selectById(currencyId);

        if (currencyOptional.isEmpty())
            throw new NotSupportCurrencyException();

        Currency currency = currencyOptional.get();

        LocalDate today = LocalDate.now();
        if (currency.getUpdatedTime().isBefore(today.atStartOfDay())) {
            downloadCurrencyData();
        }
        return currencyDB.selectById(currencyId).orElseThrow(NotSupportCurrencyException::new);
    }
}
