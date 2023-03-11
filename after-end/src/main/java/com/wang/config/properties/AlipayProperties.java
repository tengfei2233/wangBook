package com.wang.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:alipay.properties")
@ConfigurationProperties(prefix = "alipay")
@Data
public class AlipayProperties {
    // appId
    private String appId;
    // 应用私钥
    private String appPrivateKey;
    // 支付宝公钥
    private String alipayPublicKey;
    // 异步回调地址（主要是用于支付完成后执行数据持久化（必须是公网ip））
    private String notifyUrl;
    // 同步回调地址（主要是用于支付完成后的页面跳转（可以是本地地址））
    private String returnUrl;
    // 支付宝网关地址
    private String gatewayUrl;
    // 加密方式
    private String signType;
    // 支付成功重定向地址
    private String redirectUrl;

}