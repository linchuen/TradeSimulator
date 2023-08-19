package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Config.Configuration;
import com.cooba.TradeSimulator.Config.HttpConfig;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Util.RegexUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {Configuration.class, TWSESkipDateService.class, HttpConfig.class, ObjectMapper.class})
class TWSESkipDateServiceTest {
    @Autowired
    SkipDateService skipDateService;

    @Test
    public void testRegex() {
        String regex = "(\\d+)月(\\d+)日";
        Matcher matcher = RegexUtil.getMatcher("1月3日", regex);
        assertTrue(matcher.find());
        assertEquals("1", matcher.group(1));
        assertEquals("3", matcher.group(2));
    }

    @Test
    void downloadData() throws IOException, CsvException {
        skipDateService.downloadData(2023);

        LocalDate startOfYear = LocalDate.of(2023, 1, 1);

        for (int i = 0; i < 365; i++) {
            LocalDate localDate = startOfYear.plusDays(i);
            boolean isSkipDate = skipDateService.isSkipDate(localDate);
            if (isSkipDate) {
                System.out.println(localDate);
            }
        }
    }
}