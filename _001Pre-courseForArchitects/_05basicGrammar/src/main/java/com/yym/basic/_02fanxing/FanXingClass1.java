package com.yym.basic._02fanxing;

/**
 * @Description:
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-08-29 23:25
 */
public class FanXingClass1 implements FanXingInterface<String>{
    @Override
    public String getVal() {
        return "test interface";
    }
}
