package com.example.demo.utils;

public interface CacheLoadHandler<E> {
    E load(String key);

}
