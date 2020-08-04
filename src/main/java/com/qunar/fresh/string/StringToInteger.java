package com.qunar.fresh.string;

import org.apache.commons.lang3.StringUtils;

/**
 * created by lijiajia on 2020-08-03
 */
public class StringToInteger {
    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("    -42"));
        System.out.println(myAtoi("4193 with words5"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332111111"));
    }

    public static int myAtoi(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }

        int sign = 1;
        int num = 0;

        int n = str.length();

        int i = 0;
        while (i < n && str.charAt(i) == ' ') {
            i++;
        }

        if (str.charAt(i) == '+') {
            i++;
        } else if (str.charAt(i) == '-') {
            i++;
            sign = -1;
        } else if (str.charAt(i) < '0' || str.charAt(i) > '9') {
            return 0;
        }

        for (; i < n; i++) {
            int temp;
            boolean flag = false;
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                temp = 0;
                flag = true;
            } else {
                temp = str.charAt(i) - '0';
            }

            if (!flag) {
                if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && temp > Integer.MAX_VALUE % 10)) {
                    return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                num = num * 10 + temp;
            }
        }

        return num * sign;
    }
}
