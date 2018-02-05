package com.qunar.fresh.test;

/**
 * create by lijiajia on 2018/2/1
 */
public enum EnumTest {
    ABCD(1, "abcd"),
    ;

    private int i;
    private String msg;
    EnumTest(int i, String msg) {
        this.i = i;
        this.msg = msg;
    }
}
