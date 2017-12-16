package com.qunar.fresh.leetCode;

import javafx.scene.control.Alert;

/**
 * create by lijiajia on 2017/12/16
 */
public class LetCode4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] a = {1, 5, 67, 89, 104, 287};
        int[] b = {28, 39, 70, 489, 589, 999};
        int k = 7;
        int result = getTheTopKNum(a, b, k);
        System.out.println(result);
    }

    private static int getTheTopKNum(int[] a, int[] b, int k) {
        if (a == null && b == null) {
            return -1;
        }
        int len = a == null ? b.length : b == null ? a.length : a.length + b.length;
        if (k > len) {
            return -1;
        }
        if (a == null || a.length == 0) {
            return b[k - 1];
        }
        if (b == null || b.length == 0) {
            return a[k - 1];
        }
        int i = a.length - 1;
        int j = b.length - 1;

        while (k-- > 1) {
            if (a[i] > b[j]) {
                i--;
            } else {
                j--;
            }
        }
        return a[i] > b[j] ? a[i] : b[j];
    }
}
