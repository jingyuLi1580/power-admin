package com.example.powerAdmin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // ========== String 操作 ==========

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置键值对，并支持自定义过期时间
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 原子增加指定键的值
     * @param key
     * @param delta
     */
    public void incr(String key, long delta) {
        redisTemplate.opsForValue().increment(key, delta);
    }

    // ========== 哈希操作 ==========

    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public Object hget(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public Map<Object, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public void hmset(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 删除哈希表中的一个或多个字段
     * @param key
     * @param fields
     */
    public void hdel(String key, String... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    // ========== 列表操作 ==========

    public Long lpush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public List<Object> lrange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    // ========== 集合操作 ==========

    public void sadd(String key, Object... members) {
        redisTemplate.opsForSet().add(key, members);
    }

    public Set<Object> smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public Boolean sismember(String key, Object member) {
        return redisTemplate.opsForSet().isMember(key, member);
    }

    // ========== 有序集合操作 ==========

    public void zadd(String key, Object value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public Set<Object> zrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public Long zrevrank(String key, Object member) {
        return redisTemplate.opsForZSet().reverseRank(key, member);
    }

    // ========== 过期时间操作 ==========

    /**
     * 设置指定键的过期时间
     * @param key
     * @param timeout
     * @param unit
     */
    public void expire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 设置指定键的失效时间
     * @param key
     * @param date
     * @return
     */
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    // ========== 分布式锁操作 ==========

    public boolean lock(String key, long expireTime) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, "", expireTime, TimeUnit.SECONDS);
        return result != null && result;
    }

    public void unlock(String key) {
        redisTemplate.delete(key);
    }


}
