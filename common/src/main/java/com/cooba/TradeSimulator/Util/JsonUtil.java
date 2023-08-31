package com.cooba.TradeSimulator.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T readJson(String s, Class<T> clazz) {
        try {
            return objectMapper.readValue(s,clazz);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
