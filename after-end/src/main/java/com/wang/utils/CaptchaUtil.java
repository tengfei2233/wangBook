package com.wang.utils;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.captcha.generator.RandomGenerator;

import java.awt.*;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-24-16:50
 * @description 验证码工具列，指数字验证码
 */
public class CaptchaUtil {

    private static CircleCaptcha circleCaptcha = null;

    static {
        // 验证码宽高
        circleCaptcha = new CircleCaptcha(160, 60);
        circleCaptcha.setBackground(Color.PINK);
        circleCaptcha.setFont(new Font("Arial", Font.BOLD, 48));
    }


    /**
     * 随机验证码
     *
     * @param len 数字验证码长度
     * @return
     */
    public static CodeGenerator getCodeGenerator(int len) {
        return new RandomGenerator(len);
    }


    /**
     * 获取验证码绘制对象
     *
     * @return
     */
    public static AbstractCaptcha getCaptchaDrawer() {
        return circleCaptcha;
    }


}
