package com.wang.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.mapper.BannerMapper;
import com.wang.mapper.BookMapper;
import com.wang.mapper.OrderMapper;
import com.wang.pojo.Banner;
import com.wang.pojo.Book;
import com.wang.pojo.vo.BannerVo;
import com.wang.pojo.vo.BookVo;
import com.wang.service.user.UserIndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-31-22:46
 * @description
 */

@Service
public class UserIndexServiceImpl implements UserIndexService {

    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<BannerVo> getBannerList(Integer count) {
        List<Banner> banners = bannerMapper.selectList(new LambdaQueryWrapper<Banner>()
                .eq(Banner::getBnIsShow, 1)
                .eq(Banner::getStatus, 1)
                .orderByDesc(Banner::getBnDate)
                .last(" limit " + count));
        List<BannerVo> bannerVos = BeanUtil.copyToList(banners, BannerVo.class);
        return bannerVos;
    }

    @Override
    public List<BookVo> getNewBooksList(Integer count) {
        List<Book> books = bookMapper.selectList(new LambdaQueryWrapper<Book>()
                .select(Book::getBookId, Book::getBookAuthor, Book::getBookName, Book::getBookCover,Book::getBookPrice)
                .eq(Book::getStatus, 1)
                .orderByDesc(Book::getBookAddDate)
                .last("limit " + count));
        List<BookVo> bookVos = BeanUtil.copyToList(books, BookVo.class);
        return bookVos;
    }

    @Override
    public List<BookVo> getHotBooksList(Integer count) {
        // 查询购买量最多的书籍
        List<BookVo> Books = orderMapper.selectHotBooks(count);

        return Books;
    }
}
