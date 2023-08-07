package com.cooba.TradeSimulator.Util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class HttpCsvResponse {
    private final HttpUtil httpUtil;
    private Response response;
    private List<String[]> dataRows;

    public HttpCsvResponse(HttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    public static HttpCsvResponse build(HttpUtil httpUtil) {
        return new HttpCsvResponse(httpUtil);
    }

    public HttpCsvResponse sendHttpRequest(String url, Map<String, String> requestMap) {
        this.response = this.httpUtil.httpGet(url, requestMap);
        return this;
    }

    public HttpCsvResponse readResponseByCSV() throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(Objects.requireNonNull(this.response.body()).charStream());
        this.dataRows = csvReader.readAll();
        return this;
    }

    public  <T> List<T> transferRawData(Function<List<String[]>, List<T>> function) {
        return function.apply(this.dataRows);
    }
}
