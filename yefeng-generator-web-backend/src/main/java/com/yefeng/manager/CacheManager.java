package com.yefeng.manager;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 叶枫
 * @Date: 2024/05/16/15:50
 * @Description:
 */
@Component
public class CacheManager {
    // 本地缓存,设置过期15分钟
    Cache<String, Object> localCache = Caffeine.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .build();

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 写缓存
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        localCache.put(key, value);
        redisTemplate.opsForValue().set(key, value, 15, TimeUnit.MINUTES);
    }

    /**
     * 读缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        // 先从本地缓存中尝试获取
        Object value = localCache.getIfPresent(key);
        if (value != null) {
            return value;
        }

        // 本地缓存未命中，尝试从 Redis 中获取
        value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            // 将从 Redis 获取的值放入本地缓存
            localCache.put(key, value);
        }

        return value;
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public void delete(String key) {
        localCache.invalidate(key);
        redisTemplate.delete(key);
    }
}
