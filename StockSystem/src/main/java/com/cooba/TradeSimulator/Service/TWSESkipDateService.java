package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.SkipDateDB;
import com.cooba.TradeSimulator.Entity.SkipDate;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Util.HttpCsvResponse;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.cooba.TradeSimulator.Util.RegexUtil;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TWSESkipDateService implements SkipDateService {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private SkipDateDB skipDateDB;

    @Override
    public void downloadData(int year) throws IOException, CsvException {
        Map<String, String> map = new HashMap(Map.of(
                "response", "csv",
                "queryYear", String.valueOf(year - 1911)));

        List<SkipDate> skipDateList = HttpCsvResponse.build(httpUtil)
                .sendHttpRequest("https://www.twse.com.tw/holidaySchedule/holidaySchedule", map)
                .readResponseByCSV()
                .transferRawData(dataRows -> transferFunction(dataRows, year));
        skipDateDB.insertAll(skipDateList);
    }

    private List<SkipDate> transferFunction(List<String[]> dataRows, int year) {
        if (dataRows.isEmpty()) {
            return Collections.emptyList();
        }
        dataRows = dataRows.subList(2, dataRows.size());

        List<SkipDate> result = new LinkedList<>();
        Set<LocalDate> dateSet = new HashSet<>();
        for (String[] dataRow : dataRows) {
            String reason = dataRow[0];
            String date = dataRow[1];
            String regex = "(\\d+)月(\\d+)日";
            Matcher matcher = RegexUtil.getMatcher(date, regex);

            if (reason.contains("開始交易") || reason.contains("最後交易")) continue;
            if (!matcher.find()) continue;
            int month = Integer.parseInt(matcher.group(1));
            int day = Integer.parseInt(matcher.group(2));

            LocalDate localDate = LocalDate.of(year, month, day);
            if (dateSet.contains(localDate)) continue;

            dateSet.add(localDate);
            result.add(SkipDate.builder()
                    .reason(dataRow[0])
                    .date(localDate)
                    .build());
        }
        return result;
    }

    public boolean isSkipDate(LocalDate date) {
        List<DayOfWeek> weekend = Stream.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).collect(Collectors.toList());
        if (weekend.contains(date.getDayOfWeek())) {
            return true;
        }
        return skipDateDB.findByDate(date).isPresent();
    }
}

