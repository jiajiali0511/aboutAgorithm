package com.qunar.fresh.test.model;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * create by lijiajia on 2017/12/27
 */
public class CollectionTest {
    public static void main(String[] args) {
        List<Integer> aList = Lists.newArrayList(1, 2, 3, 4);
        List<Integer> bList = Lists.newArrayList(2, 3, 4);
        Collection subtract = CollectionUtils.subtract(aList, bList);
        System.out.println(JSON.toJSON(subtract));
        System.out.println(JSON.toJSON(CollectionUtils.subtract(bList, aList)));
        Collections.sort(aList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? -1 : 1;
            }
        });
        System.out.println(JSON.toJSON(aList));
    }
}
