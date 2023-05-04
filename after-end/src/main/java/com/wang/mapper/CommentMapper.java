package com.wang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.pojo.Comment;
import com.wang.pojo.bo.CommentSearchBo;
import com.wang.pojo.vo.CommentVo;
import com.wang.pojo.vo.ManOrderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-22:35
 * @description
 */

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    Page<CommentVo> selectPageByBook(@Param("bo") CommentSearchBo searchBo, Page<ManOrderVo> build);
}
