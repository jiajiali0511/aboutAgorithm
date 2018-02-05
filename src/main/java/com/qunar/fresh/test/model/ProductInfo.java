package com.qunar.fresh.test.model;

import lombok.Data;

/**
 * create by lijiajia on 2017/12/22
 */
@Data
public class ProductInfo {

    private int group;

    private int appleNum;

    public ProductInfo(int group, int appleNum) {
        this.group = group;
        this.appleNum = appleNum;
    }
}
