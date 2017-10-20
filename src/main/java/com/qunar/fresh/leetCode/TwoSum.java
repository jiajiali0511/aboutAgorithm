package com.qunar.fresh.leetCode;

import com.alibaba.fastjson.JSON;

/**
 * Created by ljiajiali on 17-10-20.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] data = {1, 2, 4, 5, 7, 10, 20, 21, 22};
        int[] index = getSumIndex(data, 50);
        System.out.println(JSON.toJSON(index));
    }

    private static int[] getSumIndex(int[] data, int sum) {
        int start = 0, end = data.length - 1;
        while (start < end) {
            if (data[start] + data[end] == sum) {
                break;
            }
            if (data[start] + data[end] > sum) {
                end--;
                continue;
            }
            if (data[start] + data[end] < sum) {
                start++;
                continue;
            }
        }
        if (start < end) {
            return new int[]{start, end};
        } else {
            return new int[]{-1};
        }
    }
}
