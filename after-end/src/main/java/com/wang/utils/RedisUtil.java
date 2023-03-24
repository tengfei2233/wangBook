package com.wang.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-24-16:16
 * @description Redis工具类
 */

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public boolean del(String... key) {
        boolean flag = true;
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                flag = redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
        return flag;
    }


    /**
     * 缓存获取
     *
     * @param key 键
     * @return 值
     */
    public <T> T get(String key) {
        return key == null ? null : (T) redisTemplate.opsForValue().get(key);
    }


    /**
     * 缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public <V> boolean set(String key, V value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(分) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public <V> boolean set(String key, V value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
