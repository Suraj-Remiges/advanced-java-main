package com.remiges.adv_java_assignment.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;


    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Add a new key with default value
    public void addKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // Get the value of a key
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // Increment the value of a key
    public Long incrementValue(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    // Decrement the value of a key
    public Long decrementValue(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    // Set TTL for a key (in seconds)
    public void setTTL(String key, long ttlInSeconds) {
        redisTemplate.expire(key, ttlInSeconds, TimeUnit.SECONDS);
    }

    

}
