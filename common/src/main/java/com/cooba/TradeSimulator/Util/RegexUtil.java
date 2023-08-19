package com.cooba.TradeSimulator.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static Matcher getMatcher(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text);
    }
}
