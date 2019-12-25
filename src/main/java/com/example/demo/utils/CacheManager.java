package com.example.demo.utils;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 缓存统一管理
 */
public class CacheManager {
    //缓存的组统一写这里
    public static final String GROUP_CUSTOMER_TOKEN = "GROUP_CUSTOMER_TOKEN";
    private static final Map<String, LoadingCache<String, Optional>> LOADINGCACHES = Maps.newHashMap();
    private static final CacheManager INSTANCE = new CacheManager();


    private CacheManager(){}

    public static CacheManager instance() {
        return INSTANCE;
    }


    /**
     * loading cache构建
     * expireTime 建议大于refreshTime，这样当缓存在刷新的过程中，可以返回旧值
     * 创建缓存，由业务类去调，这里统一调，会有循环引用
     */
    public void buildLoadingCache(String group, int refreshTime, int expireTime, TimeUnit timeUnit, final CacheLoadHandler cacheLoadHandler) {
        LoadingCache<String, Optional> cache = CacheBuilder.newBuilder()
                // 给定时间内没有被读/写访问，则回收。
                .refreshAfterWrite(refreshTime, timeUnit)
                .expireAfterAccess(expireTime, timeUnit)
                .build(new CacheLoader<String, Optional>() {
                    @Override
                    public Optional load(String s){
                        return Optional.fromNullable(cacheLoadHandler.load(s));
                    }
                });
        LOADINGCACHES.put(group, cache);
    }

    public <E> E get(String group, String key) {
        return ((Optional<E>) LOADINGCACHES.get(group).getUnchecked(key)).orNull();
    }

    public <E> void put(String group, String key, E value) {
        LoadingCache<String, Optional> cache = LOADINGCACHES.get(group);
        if (cache != null) {
            cache.put(key, Optional.fromNullable(value));
        }
    }

    public void invalidate(String group, String key) {
        LoadingCache<String, Optional> cache = LOADINGCACHES.get(group);
        if (cache != null) {
            cache.invalidate(key);
        }
    }

    public void invalidate(String group) {
        LoadingCache<String, Optional> cache = LOADINGCACHES.get(group);
        if (cache != null) {
            cache.invalidateAll();
        }
    }

    public void refresh(String group, String key) {
        LoadingCache<String, Optional> cache = LOADINGCACHES.get(group);
        if (cache != null) {
            cache.refresh(key);
        }
    }
}
