package com.example.demo.utils;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class SessionCache {

    @PostConstruct
    public void init(){
        CacheManager.me().buildLoadingCache(CacheManager.GROUP_CUSTOMER_TOKEN, 50, 60, TimeUnit.MINUTES, new CacheLoadHandler<String>() {
            @Override
            public String load(String key) {
                return null;
            }
        });
    }

    public String getToken(String key){
        return CacheManager.me().get(CacheManager.GROUP_CUSTOMER_TOKEN, key);
    }

    public void setToken(String key, String value){
        CacheManager.me().put(CacheManager.GROUP_CUSTOMER_TOKEN, key, value);
    }

    public void clearToken(String key){
        CacheManager.me().invalidate(CacheManager.GROUP_CUSTOMER_TOKEN, key);
    }

    public static void main(String[] args){
        SessionCache ucache = new SessionCache();
        ucache.init();
        String key = "name";
        System.out.println(ucache.getToken(key));
        ucache.setToken(key, "wjj");
        System.out.println(ucache.getToken(key));

        ucache.clearToken(key);
        System.out.println("-------过期时间2分钟，看看是不是过期了");
        System.out.println(ucache.getToken(key));
    }
}
