package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataAccess.SkipDateDataLink;
import com.cooba.TradeSimulator.Entity.SkipDate;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
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
    private SkipDateDataLink skipDateDataLink;

    @Override
    public void downloadData(int year) throws IOException, CsvException {
        List<SkipDate> skipDateList = sendHttpRequest(year)
                .readResponseByCSV()
                .transferRawData();
        skipDateDataLink.saveAll(skipDateList);
    }

    private SkipDataResponse sendHttpRequest(int year) {
        Map<String,String> map = new HashMap(Map.of(
                "response", "csv",
                "queryYear", year - 1911));
        Response response = httpUtil.httpGet("https://www.twse.com.tw/holidaySchedule/holidaySchedule", map);
        return new SkipDataResponse(year, response);
    }

    private static class SkipDataResponse {
        private final int year;
        private final Response response;
        private List<String[]> dataRows;

        public SkipDataResponse(int year, Response response) {
            this.year = year;
            this.response = response;
            dataRows = null;
        }

        public SkipDataResponse readResponseByCSV() throws IOException, CsvException {
            CSVReader csvReader = new CSVReader(Objects.requireNonNull(this.response.body()).charStream());
            dataRows = csvReader.readAll();
            return this;
        }

        private List<SkipDate> transferRawData() {
            if (dataRows.isEmpty()) {
                return Collections.emptyList();
            }
            dataRows = dataRows.subList(2, dataRows.size() - 1);
            return dataRows.stream().map(strings -> {
                String date = strings[1];
                String regex = "(\\d+)月(\\d+)日";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(date);

                if (matcher.find()) {
                    int month = Integer.parseInt(matcher.group(1));
                    int day = Integer.parseInt(matcher.group(2));

                    return SkipDate.builder()
                            .skipDate(LocalDate.of(year, month, day))
                            .build();
                }
                return SkipDate.builder().build();
            }).collect(Collectors.toList());
        }
    }

    public boolean isSkipDate(LocalDate date) {
        List<DayOfWeek> weekend = Stream.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).collect(Collectors.toList());
        if (weekend.contains(date.getDayOfWeek())) {
            return true;
        }
        return Optional.ofNullable(skipDateDataLink.find(date)).isPresent();
    }


}

