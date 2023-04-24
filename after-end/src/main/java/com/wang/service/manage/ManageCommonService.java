package com.wang.service.manage;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: tengfei
 * @Create: 2023 04 24 16:43
 * @Description
 **/

public interface ManageCommonService {
    String uploadImg(MultipartFile file);
}
