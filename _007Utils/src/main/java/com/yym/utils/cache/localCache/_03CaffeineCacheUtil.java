package com.yym.utils.cache.localCache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @Description: Caffeine缓存工具
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-11 22:29
 */
public class _03CaffeineCacheUtil<K, V> {
    private final Cache<K, V> cache;

    public _03CaffeineCacheUtil(long maximumSize, long expireAfterWrite, TimeUnit timeUnit) {
        this.cache = Caffeine.newBuilder()
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
