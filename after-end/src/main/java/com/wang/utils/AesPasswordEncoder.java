package com.wang.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Author: tengfei
 * @Create: 2023 05 05 11:47
 * @Description
 **/

public class AesPasswordEncoder implements PasswordEncoder {

    // TODO 不要改变这里
    public static final String KEY = "wangqingzezzzzzz";

    /**
     * 加密
     *
     * @param rawPassword 待加密密码
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        try {
            byte[] raw = KEY.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(rawPassword.toString().getBytes("utf-8"));
            // base64转码
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 比对
     *
     * @param rawPassword     未加密密码
     * @param encodedPassword 数据库加密的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(decode(encodedPassword));
    }

    /**
     * 解密
     *
     * @param rawPassword 待解密密码
     * @return
     */
    public String decode(String rawPassword) {
        try {
            byte[] raw = KEY.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decode = Base64.getDecoder().decode(rawPassword);
            byte[] original = cipher.doFinal(decode);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
