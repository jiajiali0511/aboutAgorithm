package com.qunar.fresh.leetCode;

import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.util.Arrays;

/**
 * create by lijiajia on 2017/12/15
 */
public class LetCode2AddTwoNumbers {
    public static void main(String[] args) {
        int a[] = {2, 4, 3};
        int b[] = {5, 6, 4};
        int c[] = addTwoNumbers(a, b);
        int result[] = c;
        if (c[c.length - 1] == 0) {
            result = Arrays.copyOf(c, c.length - 1);
        }
        System.out.println(JSON.toJSON(result));
    }

    private static int[] addTwoNumbers(int[] a, int[] b) {
        if (a == null && b == null) {
            return new int[] {0};
        }
        if (a == null || a.length == 0) {
            return b;
        }
        if (b == null || b.length == 0) {
            return a;
        }
        int alen = a.length;
        int blen = b.length;
        int c[] = new int[alen > blen ? alen + 1 : blen + 1];
        int i = 0;
        //进位
        int index = 0;
        while (i < alen && i < blen) {
            int sum = a[i] + b[i];
            int result = sum + index;
            c[i] = result % 10;
            index = result /10;
            i++;
        }
        if (i == alen && i == blen) {
            c[i] = index;
            return c;
        }
        if (i == alen) {
            while (i++ < blen) {
                int result = b[i] + index;
                c[i] = result % 10;
                index = result /10;
            }
            return c;
        } else if (i == blen) {
            while (i++ < alen) {
                int result = a[i] + index;
                c[i] = result % 10;
                index = result /10;
            }
            return c;
        }
        return new int[] {0};
    }
}
