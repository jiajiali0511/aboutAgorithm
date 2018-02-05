package com.qunar.fresh.leetCode;

import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * create by lijiajia on 2017/12/19
 */
public class LetCode6ZigZagConversion {
    public static void main(String[] args) throws Exception {
        String initialStr = "PAYPALISHIRING";
        String resultStr = zigZagConversion(initialStr);
    }

    private static String zigZagConversion(String initialStr) {
        if (StringUtils.isBlank(initialStr)) {
            return "";
        }
        if (initialStr.length() <= 3) {
            return initialStr;
        }
        int strLen = initialStr.length();
        return initialStr;
    }
}
