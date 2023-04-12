package com.wang.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.mapper.*;
import com.wang.pojo.*;
import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.CommentBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BookVo;
import com.wang.pojo.vo.CommentVo;
import com.wang.pojo.vo.PageData;
import com.wang.pojo.vo.TypeVo;
import com.wang.service.user.UserBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-20:51
 * @description
 */

@Service
public class UserBookServiceImpl implements UserBookService {

    @Resource
    private TypeMapper typeMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookTypeMapper bookTypeMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public TypeVo getTypeList() {
        List<Type> types = typeMapper.selectList(null);
        TypeVo typeVo = BeanUtil.copyProperties(types, TypeVo.class);
        return typeVo;
    }

    @Override
    public PageData<BookVo> getBookList(BookSearchBo searchBo, PageQuery pageQuery) {
        // 查询是否是指定分类
        List<Long> bookIds = null;
        if (ObjUtil.isNotEmpty(searchBo.getType())) {
            List<BookType> bookTypes = bookTypeMapper.selectList(new LambdaQueryWrapper<BookType>()
                    .select(BookType::getBookId)
                    .eq(BookType::getTypeId, searchBo.getType())
                    .orderByAsc(BookType::getBookId));
            if (ObjUtil.isNotEmpty(bookTypes)) {
                bookIds = bookTypes.stream()
                        .map(BookType::getBookId)
                        .collect(Collectors.toList());
            }
        }
        Page<Book> bookPage = bookMapper.selectPage(pageQuery.build(), new LambdaQueryWrapper<Book>()
                .select(Book::getBookId, Book::getBookName, Book::getBookCover, Book::getBookAuthor, Book::getBookPrice)
                .in(ObjectUtil.isNotNull(bookIds), Book::getBookId, bookIds)
                .eq(Book::getStatus, 1)
                .like(searchBo.getPattern().intValue() == 1, Book::getBookName, searchBo.getKey())
                .eq(searchBo.getPattern().intValue() == 2, Book::getBookAuthor, searchBo.getKey())
                .eq(searchBo.getPattern().intValue() == 3, Book::getBookIsbn, searchBo.getKey())
        );
        List<BookVo> bookVos = BeanUtil.copyToList(bookPage.getRecords(), BookVo.class);

        return PageData.build(bookPage.getTotal(), bookVos);
    }

    @Override
    public BookVo getBookInfo(Long bookId) {
        Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                .eq(Book::getBookId, bookId)
                .eq(Book::getStatus, 1));
        BookVo bookVo = BeanUtil.copyProperties(book, BookVo.class);
        return bookVo;
    }

    @Override
    public PageData<CommentVo> CommentList(Long bookId, PageQuery pageQuery) {
        // 先获取父评论列表
        Page<Comment> commentPage = commentMapper.selectPage(pageQuery.build(), new LambdaQueryWrapper<Comment>()
                .eq(Comment::getBookId, bookId)
                .eq(Comment::getStatus, 1)
                .eq(Comment::getCmParentId, 0L)
                .orderByDesc(Comment::getCmDate));
        // TODO　给每条评论设置设置用户信息
        List<CommentVo> commentVos = BeanUtil.copyToList(commentPage.getRecords(), CommentVo.class);
        commentVos.stream().forEach(comment -> {
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .select(User::getUsername, User::getAvatar)
                    .eq(User::getUserId, comment.getUserId()));
            comment.setUsername(user.getUsername());
            comment.setAvatar(user.getAvatar());
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
                        .select(User::getUsername, User::getAvatar)
                        .eq(User::getUserId, child.getUserId()));
                child.setUsername(user2.getUsername());
                child.setAvatar(user2.getAvatar());
            });
        });
        return PageData.build(commentPage.getTotal(), commentVos);
    }

    @Override
    public Boolean setComment(CommentBo bo) {
        // 父评论id是否为0，为0代表是根评论
        if (ObjectUtil.isNull(bo)) {
            return false;
        }
        if (bo.getBookId().intValue() != 0) {
            // 获取父评论是否存在
            Comment comment = commentMapper.selectOne(new LambdaQueryWrapper<Comment>()
                    .select(Comment::getStatus)
                    .eq(Comment::getCmId, bo.getCmParentId()));
            if (comment.getStatus().intValue() == 0) {
                return false;
            }
        }
        Comment comment = BeanUtil.copyProperties(bo, Comment.class);
        comment.setCmParentId(bo.getCmParentId());
        comment.setCmDate(new Date());
        // TODO 获取用户id
        comment.setUserId(null);
        int insert = commentMapper.insert(comment);
        return insert == 1;
    }




}
