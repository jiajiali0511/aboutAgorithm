package com.qunar.fresh.array;

import com.alibaba.fastjson.JSON;

/**
 * 已排序的数组去除重复数据
 * input A=[1,1,2]
 * output: length=2 and A is now [1,2]
 */
public class RemoveDupFromSortedArray {
    public static void main(String[] args) {
        int[] data = {1,1,2};
        int resultLength = getResultLength(data);
        System.out.println(resultLength);
        System.out.println(JSON.toJSON(data));
    }

    private static int getResultLength(int[] data) {
        if (data.length == 0) {
            return 0;
        }

        int index = 1;
        for (int i = 1; i < data.length; i++) {
            if (data[i] != data[index -1]) {
                data[index] = data[i];
                index++;
            }
        }

        return index;
    }
}
