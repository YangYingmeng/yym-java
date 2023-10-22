package com.yym.utils;

import com.yym.exception.DistributedLockException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 分布式锁切面
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-12 11:19
 */
@Aspect
@Component
public class DistributedLockAspect {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Around("@annotation(distributedLock)")
    public Object manageDistributedLock(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        String lockKey = distributedLock.value();
        String lockValue = Long.toString(Thread.currentThread().getId());
        long timeout = distributedLock.timeout();

        boolean acquired = false;

        try {
            acquired = acquireLock(lockKey, lockValue, timeout);
            if (acquired) {
                return joinPoint.proceed();
            } else {
                throw new DistributedLockException("Failed to acquire the distributed lock");
            }
        } finally {
            if (acquired) {
                releaseLock(lockKey, lockValue);
            }
        }
    }

    private boolean acquireLock(String lockKey, String lockValue, long timeout) {
        Boolean lockAcquired = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, timeout, TimeUnit.MICROSECONDS);
        return lockAcquired != null && lockAcquired;
    }

    private final String releaseLockLua =
            "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "   return redis.call('del', KEYS[1]) " +
                    "else " +
                    "   return 0 " +
                    "end";

    // 释放锁采用lua脚本 将根据key取值和锁标识的比较放在一起 保证原子性, 防止多线程同时进入删除锁操作, 误删其它线程的锁
    private void releaseLock(String lockKey, String lockValue) {
        RedisScript<Long> script = new DefaultRedisScript<>(releaseLockLua, Long.class);
        Long result = redisTemplate.execute(script, Collections.singletonList(lockKey), lockValue);

        if (result == null || result == 0) {
            throw new DistributedLockException("Failed to release the distributed lock.");
        }
    }
}
