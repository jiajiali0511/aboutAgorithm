package com.qunar.fresh.sort;

import com.alibaba.fastjson.JSON;

import java.util.Random;

/**
 * Created by ljiajiali on 17-10-20.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] data = {1, 18, 20, 10, 7, 30, 5};
        quickSort(data, 0, 6, 7);
        System.out.println(JSON.toJSON(data));
    }

    public static void quickSort(int[] data, int start, int end, int length) {
        if (start == end) {
            return;
        }
        int index = partition(data, start, end, length);
        if (index > start) {
            quickSort(data, start, index - 1, length);
        }
        if (index < end) {
            quickSort(data, index + 1, end, length);
        }
    }

    public static int partition(int[] data, int start, int end, int length) {
        if (length < 0 || start < 0 || end < start) {
            return -1;
        }
        int small = start - 1;
        int rand = new Random().nextInt(end - start) + start;
        swap(data, rand, end);
        for (int index = start; index < end; index++) {
            if (data[index] < data[end]) {
                small++;
                if (small != index) {
                    swap(data, small, index);
                }
            }
        }
        small++;
        swap(data, small, end);
        return small;
    }

    private static void swap(int[] data, int i, int j) {
        if (i >= data.length || j > data.length) {
            return;
        }
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
