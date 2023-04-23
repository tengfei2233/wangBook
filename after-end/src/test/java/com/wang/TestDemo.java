package com.wang;

import com.wang.pojo.LoginUser;
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

    public static void main(String[] args) {

    }

    @Test
    public void testRedis() {
        User user = new User();
        user.setPassword("1111");
        user.setUserId(11L);
        user.setUserType(1);
        LoginUser loginUser = new LoginUser(user);
        redisUtil.set("user", user);
        User o = redisUtil.get("user");
        System.out.println(o);
    }


}
