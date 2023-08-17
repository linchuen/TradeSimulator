package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.SkipDateDataAccess;
import com.cooba.TradeSimulator.Entity.SkipDate;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Util.HttpCsvResponse;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TWSESkipDateService implements SkipDateService {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private SkipDateDataAccess skipDateDataAccess;

    @Override
    public void downloadData(int year) throws IOException, CsvException {
        Map<String, String> map = new HashMap(Map.of(
                "response", "csv",
                "queryYear", year - 1911));

        List<SkipDate> skipDateList = HttpCsvResponse.build(httpUtil)
                .sendHttpRequest("https://www.twse.com.tw/holidaySchedule/holidaySchedule", map)
                .readResponseByCSV()
                .transferRawData(dataRows -> transferFunction(dataRows, year));
        skipDateDataAccess.insertAll(skipDateList);
    }

    private List<SkipDate> transferFunction(List<String[]> dataRows, int year) {
        if (dataRows.isEmpty()) {
            return Collections.emptyList();
        }
        dataRows = dataRows.subList(2, dataRows.size() - 1);
        return dataRows.stream().filter(strings -> {
            String reason = strings[0];
            String date = strings[1];
            String regex = "(\\d+)月(\\d+)日";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(date);

            return !reason.contains("開始") && matcher.find();
        }).map(strings -> {
            String date = strings[1];
            String regex = "(\\d+)月(\\d+)日";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(date);

            int month = Integer.parseInt(matcher.group(1));
            int day = Integer.parseInt(matcher.group(2));

            return SkipDate.builder()
                    .reason(strings[0])
                    .date(LocalDate.of(year, month, day))
                    .build();
        }).collect(Collectors.toList());
    }

    public boolean isSkipDate(LocalDate date) {
        List<DayOfWeek> weekend = Stream.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).collect(Collectors.toList());
        if (weekend.contains(date.getDayOfWeek())) {
            return true;
        }
        return skipDateDataAccess.findByDate(date).isPresent();
    }
}

