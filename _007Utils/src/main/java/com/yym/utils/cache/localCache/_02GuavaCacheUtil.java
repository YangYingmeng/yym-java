package com.yym.utils.cache.localCache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Description: Guava缓存工具(过时 不再维护)
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-11 22:17
 */
public class _02GuavaCacheUtil<K, V> {

    private final Cache<K, V> cache;

    public _02GuavaCacheUtil(long maximumSize, long expireAfterWrite, TimeUnit timeUnit) {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(expireAfterWrite, timeUnit)
                .build();
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.getIfPresent(key);
    }

    public void remove(K key) {
        cache.invalidate(key);
    }

    public void clear() {
        cache.invalidateAll();
    }

}
