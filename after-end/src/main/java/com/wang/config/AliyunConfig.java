package com.wang.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: tengfei
 * @Create: 2023 04 24 16:53
 * @Description
 **/

@Data
@Configuration
@PropertySource("classpath:aliyun.properties")
@ConfigurationProperties(prefix = "aliyun")
public class AliyunConfig {

    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    private String endpoint;
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    private String accessKeyId;
    private String accessKeySecret;
    // 仓库名称
    private String bucketName;
    // 域名路径
    private String urlPrefix;
    // 跟路径
    private String rootPath;

    public OSS getInstance() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}
