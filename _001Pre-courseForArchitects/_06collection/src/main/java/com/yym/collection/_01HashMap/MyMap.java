package com.yym.collection._01HashMap;

import java.util.Set;

/**
 * @Description: hashMap接口
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-09-22 21:27
 */
public interface MyMap<K, V> {

    /**
     * @Description: 插入键值对
     * @Param: K key, V value
     * @Return: value
     */
    public V put(K k, V v);


    /**
     * @Description: 根据key取出value
     * @Param: K key
     * @Return: V value
     */
    public V get(K k);

    /**
     * @Description: 判断key是否存在
     * @Param: K key
     * @Return: true / false
     */
    public boolean containsKey(K k);


    /**
     * @Description: 取出所有的key并返回
     * @Return: key的集合
     */
    public Set<K> keySet();
}
