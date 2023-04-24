package com.wang.service.manage.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.exception.BookException;
import com.wang.exception.OrderException;
import com.wang.mapper.*;
import com.wang.pojo.*;
import com.wang.pojo.bo.BookBo;
import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.OrderSearchBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BookVo;
import com.wang.pojo.vo.ManOrderVo;
import com.wang.pojo.vo.PageData;
import com.wang.pojo.vo.TypeVo;
import com.wang.service.manage.ManageBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private TypeMapper typeMapper;


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

    @Override
    public Boolean shelveBook(Long bookId) {
        int update = bookMapper.update(null, new LambdaUpdateWrapper<Book>()
                .eq(Book::getBookId, bookId)
                .setSql(" status=!status "));
        return update >= 1;
    }

    @Override
    public PageData<ManOrderVo> getOrderList(OrderSearchBo searchBo, PageQuery pageQuery) {
        Page<Order> orderPage = orderMapper.selectPage(pageQuery.build(), new LambdaQueryWrapper<Order>()
                .eq(ObjUtil.isNull(searchBo.getOrderId()), Order::getOrderId, searchBo.getOrderId())
                .eq(ObjUtil.isNull(searchBo.getStatus()), Order::getStatus, searchBo.getStatus())
                .orderByDesc(Order::getBuyDate));
        List<ManOrderVo> orderList = new ArrayList<>((int) orderPage.getTotal());
        orderPage.getRecords().forEach(order -> {
            ManOrderVo orderVo = BeanUtil.copyProperties(order, ManOrderVo.class);
            // 查询用户信息
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .select(User::getUsername)
                    .eq(User::getUserId, order.getOrderId()));
            orderVo.setUsername(user.getUsername());
            // 查询书籍信息
            Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                    .select(Book::getBookName, Book::getBookCover)
                    .eq(Book::getBookId, order.getBookId()));
            orderVo.setBookName(book.getBookName());
            orderVo.setBookCover(book.getBookCover());
            orderList.add(orderVo);
        });
        return PageData.build(orderPage.getTotal(), orderList);
    }

    @Override
    public ManOrderVo getOrderInfo(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (ObjUtil.isNull(order)) {
            throw new OrderException("订单不存在");
        }
        ManOrderVo manOrderVo = BeanUtil.copyProperties(order, ManOrderVo.class);

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getUsername, User::getPhone)
                .eq(User::getUserId, order.getUserId()));
        manOrderVo.setUsername(user.getUsername());
        manOrderVo.setPhone(user.getPhone());

        Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                .select(Book::getBookCover, Book::getBookName)
                .eq(Book::getBookId, order.getBookId()));
        manOrderVo.setBookName(book.getBookName());
        manOrderVo.setBookCover(book.getBookCover());

        return manOrderVo;
    }

    @Override
    public PageData<TypeVo> getTypeList(PageQuery pageQuery) {
        Page<Type> types = typeMapper.selectPage(pageQuery.build(), null);
        List<TypeVo> typeVos = BeanUtil.copyToList(types.getRecords(), TypeVo.class);
        return PageData.build(types.getTotal(), typeVos);
    }

    @Override
    public Boolean delType(Long typeId) {
        int i = typeMapper.deleteById(typeId);
        int j = bookTypeMapper.delete(new LambdaQueryWrapper<BookType>()
                .eq(BookType::getTypeId, typeId));
        return i >= 1 && j >= 0;
    }

    @Override
    public Boolean addType(String typeName) {
        Type type = typeMapper.selectOne(new LambdaQueryWrapper<Type>()
                .eq(Type::getTypeName, typeName));
        if (ObjUtil.isNotNull(type)) {
            throw new BookException("该书籍类型已存在");
        }
        Type newType = new Type();
        newType.setTypeName(typeName);
        int insert = typeMapper.insert(newType);
        return insert >= 1;
    }
}
