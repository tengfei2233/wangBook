package com.wang.service.manage.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.exception.BookException;
import com.wang.mapper.BookMapper;
import com.wang.mapper.BookTypeMapper;
import com.wang.pojo.Book;
import com.wang.pojo.BookType;
import com.wang.pojo.bo.BookBo;
import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BookVo;
import com.wang.pojo.vo.PageData;
import com.wang.service.manage.ManageBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 23:57
 * @Description
 **/

@Service
public class ManageBookServiceImpl implements ManageBookService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookTypeMapper bookTypeMapper;


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
    public Boolean addBook(BookBo bo) {
        if (ObjUtil.isNull(bo)) {
            throw new BookException("参数为空");
        }

        Book book = BeanUtil.copyProperties(bo, Book.class);
        // 防止前端传入id
        book.setBookId(null);
        book.setBookAddDate(new Date());
        book.setStatus(1);
        int insert = bookMapper.insert(book);
        return insert >= 1;
    }

    @Override
    public Boolean updateBook(BookBo bo) {
        if (ObjUtil.isNull(bo) || ObjUtil.isNull(bo.getBookId())) {
            throw new BookException("参数为空");
        }
        Book book = BeanUtil.copyProperties(bo, Book.class);
        int update = bookMapper.update(book, null);
        return update >= 1;
    }
}
