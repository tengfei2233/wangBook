package com.wang.service;

import com.aliyun.oss.OSS;
import com.wang.config.AliyunConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: tengfei
 * @Create: 2023 04 24 17:10
 * @Description
 **/

@Service
public class AsyncService {

    @Resource
    private AliyunConfig aliyunConfig;

    // TODO 图片上传不需要异步，否者会有问题
    public void uploadImg(String imgPath, InputStream file) {
        OSS ossClient = aliyunConfig.getInstance();
        ossClient.putObject(aliyunConfig.getBucketName(), imgPath, file);
        ossClient.shutdown();
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
