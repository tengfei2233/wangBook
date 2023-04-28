package com.wang.service.user;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: tengfei
 * @Create: 2023 04 24 16:43
 * @Description
 **/

public interface UserCommonService {
    String uploadImg(MultipartFile file);
}
