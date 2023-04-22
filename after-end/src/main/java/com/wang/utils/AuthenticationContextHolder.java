package com.wang.utils;

import org.springframework.security.core.Authentication;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 20:46
 * @Description
 **/

public class AuthenticationContextHolder {
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext() {
        return contextHolder.get();
    }

    public static void setContext(Authentication context) {
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}

