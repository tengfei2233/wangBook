package com.wang.utils;

import com.wang.exception.UserException;
import com.wang.pojo.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 20:24
 * @Description
 **/

public class SecurityUtil {

    public static Long getUserId() {
        Long userId = getLoginUser().getUserId();
        return userId;
    }

    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new UserException("获取用户信息异常");
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String encryptPassword(String password) {
        AesPasswordEncoder passwordEncoder = new AesPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        AesPasswordEncoder passwordEncoder = new AesPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static String decodePassword(String rawPassword) {
        AesPasswordEncoder passwordEncoder = new AesPasswordEncoder();
        return passwordEncoder.decode(rawPassword);
    }

}
