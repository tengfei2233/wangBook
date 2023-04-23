package com.wang.utils;

import cn.hutool.core.util.ObjUtil;
import com.wang.pojo.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 17:13
 * @Description
 **/

@Component
public class JwtUtil {

    @Value("${token.key}")
    private String key;

    @Value("${token.salt}")
    private String salt;

    @Value("${token.expire}")
    private Integer expire;

    @Resource
    private RedisUtil redisUtil;

    private static long MINUTE = 60 * 1000L;


    public String createJwt(LoginUser loginUser) {
        refreshToken(loginUser);
        return createJwt(loginUser.getUserId());
    }

    /**
     * 生成token
     *
     * @param userId 用户id
     * @return
     */
    public String createJwt(Long userId) {

        String token = Jwts.builder().claim("date", new Date().getTime()).claim("userId", userId).signWith(SignatureAlgorithm.HS256, salt).compact();
        return token;
    }

    /**
     * 获取用户id
     *
     * @param request
     * @return
     */
    public Long getUserId(HttpServletRequest request) {
        String token = request.getHeader(key);
        return getUserId(token);
    }

    /**
     * 获取登录用户信息
     *
     * @param request
     * @return
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = request.getHeader(key);
        if (ObjUtil.isNotNull(token)) {
            Long userId = getUserId(token);
            String userKey = RedisKey.LOGIN_USER_KEY + (userId);
            LoginUser loginUser = redisUtil.get(userKey);
            return loginUser;
        }
        return null;

    }

    /**
     * 当里过期时间还有10min时，刷新令牌
     *
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= 10 * MINUTE) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expire * MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = RedisKey.LOGIN_USER_KEY + (loginUser.getUserId());
        redisUtil.set(userKey, loginUser, expire);
    }

    /**
     * 获取用户id
     *
     * @param token
     * @return
     */
    public Long getUserId(String token) {
        Claims claims = parseToken(token);
        Long userId = Long.parseLong(claims.get("userId").toString());
        return userId;
    }

    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(salt).parseClaimsJws(token).getBody();
    }


}
