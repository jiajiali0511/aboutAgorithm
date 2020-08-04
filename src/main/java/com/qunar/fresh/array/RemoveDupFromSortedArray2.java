package com.qunar.fresh.array;

import com.alibaba.fastjson.JSON;

/**
 * 已排序的数组去除重复数据(duplicates ard allowed at most twice)
 * input A=[1,1,1,2,2,3]
 * output: length=5 and A is now [1,1,2,2,3]
 *
 * 分析：加一个变量记录一下元素出现的次数即可
 */
public class RemoveDupFromSortedArray2 {
    public static void main(String[] args) {
        int[] data = {1,1,1,2,2,3};
        int resultLength = getResultLength(data);
        System.out.println(resultLength);
        System.out.println(JSON.toJSON(data));
    }

    private static int getResultLength(int[] data) {
        if (data.length <= 2) {
            return data.length;
        }

        int index = 2;
        for (int i = 2; i < data.length; i++) {
            if (data[i] != data[index -2]) {
                data[index] = data[i];
                index++;
            }
        }

        return index;
    }
}
