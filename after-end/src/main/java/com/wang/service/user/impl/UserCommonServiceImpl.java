package com.wang.service.user.impl;

import cn.hutool.core.io.file.FileNameUtil;
import com.wang.config.AliyunConfig;
import com.wang.exception.OtherException;
import com.wang.service.AsyncService;
import com.wang.service.user.UserCommonService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: tengfei
 * @Create: 2023 04 24 16:43
 * @Description
 **/

@Service
public class UserCommonServiceImpl implements UserCommonService {

    @Resource
    private AliyunConfig aliyunConfig;
    @Resource
    private AsyncService asyncService;


    @Override
    public String uploadImg(MultipartFile file) {
        // TODO 此处应该需要文件校验

        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            throw new OtherException("文件上传失败");
        }

        String fileName = UUID.randomUUID().toString();
        String extName = FileNameUtil.extName(file.getOriginalFilename());
        String filePath = new StringBuilder(aliyunConfig.getRootPath())
                .append("/images/")
                .append(fileName)
                .append(".")
                .append(extName)
                .toString();
        // 上传到阿里云OSS
        asyncService.uploadImg(filePath, is);
        return aliyunConfig.getUrlPrefix() + filePath;
    }
}
