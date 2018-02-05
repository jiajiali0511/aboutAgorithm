package com.qunar.fresh.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qunar.fresh.test.model.ProductInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * create by lijiajia on 2017/12/22
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<ProductInfo> productInfos = Lists.newArrayList(
                new ProductInfo(10, 1),
                new ProductInfo(10, 2),
                new ProductInfo(10, 3),
                new ProductInfo(10, 4),
                new ProductInfo(20, 1),
                new ProductInfo(20, 3),
                new ProductInfo(30, 1),
                new ProductInfo(30, 1),
                new ProductInfo(30, 1),
                new ProductInfo(30, 1)
        );
        Integer a = -1;
        getCode(a);
        System.out.println(a);
    }

    public static void add(int i) {
        throw new BizException("hahhahhahah");
    }

    public static int getCode(Integer a) {
        a = 2;
        return 0;
    }
}
