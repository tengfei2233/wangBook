package com.wang.service.manage.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.exception.OtherException;
import com.wang.mapper.BookMapper;
import com.wang.mapper.CommentMapper;
import com.wang.mapper.UserMapper;
import com.wang.pojo.Book;
import com.wang.pojo.Comment;
import com.wang.pojo.User;
import com.wang.pojo.bo.CommentSearchBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.CommentVo;
import com.wang.pojo.vo.PageData;
import com.wang.service.manage.ManageCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tengfei
 * @Create: 2023 05 04 17:05
 * @Description
 **/

@Service
public class ManageCommentServiceImpl implements ManageCommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BookMapper bookMapper;


    @Override
    public PageData<CommentVo> getCommentList(CommentSearchBo searchBo, PageQuery pageQuery) {
        if (ObjUtil.isNull(searchBo) || ObjUtil.isNull(searchBo.getVal())) {
            Page<Comment> commentPage = commentMapper.selectPage(pageQuery.build(), new LambdaQueryWrapper<Comment>()
                    .eq(Comment::getCmParentId, 0L)
                    .eq(Comment::getStatus, 1)
                    .orderByDesc(Comment::getCmDate));
            List<CommentVo> commentVos = BeanUtil.copyToList(commentPage.getRecords(), CommentVo.class);
            setChild(commentVos, true);
            return PageData.build(commentPage.getTotal(), commentVos);
        } else if (ObjUtil.isNotNull(searchBo) && ObjUtil.isNotNull(searchBo.getVal())) {
            Page<CommentVo> commentPage = commentMapper.selectPageByBook(searchBo, pageQuery.build());
            setChild(commentPage.getRecords(), false);
            return PageData.build(commentPage.getTotal(), commentPage.getRecords());
        }
        return PageData.build(0L, null);
    }

    @Override
    public Boolean delComment(Long cmId) {
        if (ObjUtil.isNull(cmId)) {
            throw new OtherException("id为空");
        }
        Comment comment = commentMapper.selectById(cmId);
        if (!comment.getCmParentId().equals(1L)) {
            commentMapper.update(null, new LambdaUpdateWrapper<Comment>()
                    .set(Comment::getStatus, 0)
                    .eq(Comment::getCmId, comment.getCmParentId()));
        }
        int update = commentMapper.update(null, new LambdaUpdateWrapper<Comment>()
                .set(Comment::getStatus, 0)
                .eq(Comment::getCmId, cmId));
        return update >= 1;
    }

    private void setChild(List<CommentVo> vos, boolean setBook) {
        vos.stream().forEach(comment -> {
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .select(User::getUsername)
                    .eq(User::getUserId, comment.getUserId()));
            comment.setUsername(user.getUsername());
            if (setBook) {
                Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                        .select(Book::getBookName)
                        .eq(Book::getBookId, comment.getBookId()));
                if (ObjUtil.isNotNull(book)) {
                    comment.setBookName(book.getBookName());
                }
            }
            // 获取子评论
            List<Comment> childComments = commentMapper.selectList(new LambdaQueryWrapper<Comment>()
                    .eq(Comment::getStatus, 1)
                    .eq(Comment::getCmParentId, comment.getCmId())
                    .orderByDesc(Comment::getCmDate)
            );
            List<CommentVo> childCommentVos = BeanUtil.copyToList(childComments, CommentVo.class);
            // 子评论设置用户信息
            childCommentVos.stream().forEach(child -> {
                User user2 = userMapper.selectOne(new LambdaQueryWrapper<User>()
                        .select(User::getUsername)
                        .eq(User::getUserId, child.getUserId()));
                child.setUsername(user2.getUsername());
                if (setBook) {
                    Book book2 = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                            .select(Book::getBookName)
                            .eq(Book::getBookId, comment.getBookId()));
                    if (ObjUtil.isNotNull(book2)) {
                        comment.setBookName(book2.getBookName());
                    }
                }
            });
            comment.setChildComment(childCommentVos);
        });
    }
}
