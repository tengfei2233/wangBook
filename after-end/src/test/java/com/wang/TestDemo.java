package com.wang;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.mapper.UserMapper;
import com.wang.pojo.User;
import com.wang.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-13-21:21
 * @description
 */

@SpringBootTest
public class TestDemo {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;

    public static void main(String[] args) {

    }

    @Test
    public void testRedis() {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getPhone)
                .eq(User::getUserId, 1L)
                .eq(User::getStatus, 1));
        System.out.println(user);
    }


}
