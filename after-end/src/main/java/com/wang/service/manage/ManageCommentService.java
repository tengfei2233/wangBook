package com.wang.service.manage;

import com.wang.pojo.bo.CommentSearchBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.CommentVo;
import com.wang.pojo.vo.PageData;

/**
 * @Author: tengfei
 * @Create: 2023 05 04 17:05
 * @Description
 **/

public interface ManageCommentService {
    PageData<CommentVo> getCommentList(CommentSearchBo searchBo, PageQuery pageQuery);

    Boolean delComment(Long cmId);
}
