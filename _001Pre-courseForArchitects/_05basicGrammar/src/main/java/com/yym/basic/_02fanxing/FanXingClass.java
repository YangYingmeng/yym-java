package com.yym.basic._02fanxing;

/**
 * @Description: 泛型类(单一泛型T 多元泛型再加其它属性)
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-08-29 23:15
 */
public class FanXingClass<T, K> {
    private T var;
    private K key;
    public T getVar() {
        return var;
    }
    public void setVar(T var) {
        this.var = var;
    }

    public K getKey() {
        return key;
    }

    public FanXingClass<T, K> setKey(K key) {
        this.key = key;
        return this;
    }
}
