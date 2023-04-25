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
import java.util.*;
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
        if (ObjUtil.isNotNull(searchBo.getType())) {
            List<BookType> bookTypes = bookTypeMapper.selectList(new LambdaQueryWrapper<BookType>()
                    .select(BookType::getBookId)
                    .eq(BookType::getTypeId, searchBo.getType())
                    .orderByAsc(BookType::getBookId));
            if (ObjUtil.isNotNull(bookTypes) && bookTypes.size() >= 1) {
                bookIds = bookTypes.stream()
                        .map(BookType::getBookId)
                        .collect(Collectors.toList());
            } else {
                return PageData.build(0L, null);
            }
        }
        Page<Book> bookPage = bookMapper.selectPage(pageQuery.build(), new LambdaQueryWrapper<Book>()
                .in(ObjectUtil.isNotNull(bookIds), Book::getBookId, bookIds)
                .and(ObjUtil.isNotNull(searchBo.getKey()), wrapper -> {
                    wrapper.like(searchBo.getPattern().intValue() == 1, Book::getBookName, searchBo.getKey())
                            .eq(searchBo.getPattern().intValue() == 2, Book::getBookAuthor, searchBo.getKey())
                            .eq(searchBo.getPattern().intValue() == 3, Book::getBookIsbn, searchBo.getKey());
                })
        );
        List<BookVo> bookVos = BeanUtil.copyToList(bookPage.getRecords(), BookVo.class);
        // 增加书籍类型
        Map<Long, String> map = new HashMap<>();
        bookVos.forEach(item -> {
            BookType bookType = bookTypeMapper.selectOne(new LambdaQueryWrapper<BookType>()
                    .select(BookType::getTypeId)
                    .eq(BookType::getBookId, item.getBookId()));
            String typeName;
            if (ObjUtil.isNotNull(typeName = map.get(bookType.getTypeId()))) {
            } else {
                Type type = typeMapper.selectOne(new LambdaQueryWrapper<Type>()
                        .select(Type::getTypeName)
                        .eq(Type::getTypeId, bookType.getTypeId()));
                typeName = type.getTypeName();
                map.put(bookType.getTypeId(), type.getTypeName());
            }
            item.setTypeId(bookType.getTypeId());
            item.setTypeName(typeName);
        });
        map.clear();
        return PageData.build(bookPage.getTotal(), bookVos);
    }

    @Override
    public Boolean addBook(BookBo bo) {
        if (ObjUtil.isNull(bo)) {
            throw new BookException("参数为空");
        }
        Book oldBook = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                .eq(Book::getBookIsbn, bo.getBookIsbn()));
        if (ObjUtil.isNotNull(oldBook)) {
            throw new BookException("ISBN号相同，书籍存在");
        }
        Book book = BeanUtil.copyProperties(bo, Book.class);
        // 防止前端传入id
        book.setBookId(null);
        book.setBookAddDate(new Date());
        book.setStatus(1);
        int insert = bookMapper.insert(book);
        // 设置书籍类型
        if (ObjUtil.isNotNull(bo.getTypeId())) {
            BookType bookType = new BookType();
            bookType.setBookId(book.getBookId());
            bookType.setTypeId(bo.getTypeId());
            bookTypeMapper.insert(bookType);
        }
        return insert >= 1;
    }

    @Override
    public Boolean updateBook(BookBo bo) {
        if (ObjUtil.isNull(bo) || ObjUtil.isNull(bo.getBookId())) {
            throw new BookException("参数为空");
        }
        Book book = BeanUtil.copyProperties(bo, Book.class);
        int update = bookMapper.updateById(book);
        // 修改书籍类型
        if (ObjUtil.isNotNull(bo.getTypeId())) {
            BookType bookType = bookTypeMapper.selectOne(new LambdaQueryWrapper<BookType>()
                    .eq(BookType::getBookId, book.getBookId()));
            if (ObjUtil.isNull(bookType)) {
                bookType = new BookType();
                bookType.setBookId(book.getBookId());
                bookType.setTypeId(bo.getTypeId());
                bookTypeMapper.insert(bookType);
            } else if (!bookType.getTypeId().equals(bo.getTypeId())) {
                bookType.setTypeId(bo.getTypeId());
                bookTypeMapper.updateById(bookType);
            }
        }
        return update >= 1;
    }

    @Override
    public Boolean shelveBook(Long bookId) {
        Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                .select(Book::getStatus)
                .eq(Book::getBookId, bookId));
        if (ObjUtil.isNull(book)) {
            throw new BookException("书籍不存在");
        }

        int update = bookMapper.update(null, new LambdaUpdateWrapper<Book>()
                .eq(Book::getBookId, bookId)
                .set(book.getStatus() == 1, Book::getStatus, 0)
                .set(book.getStatus() == 0, Book::getStatus, 1));
        return update >= 1;
    }

    @Override
    public PageData<ManOrderVo> getOrderList(OrderSearchBo searchBo, PageQuery pageQuery) {
        if (ObjUtil.isNotNull(searchBo.getVal()) && ObjUtil.isNotNull(searchBo.getPattern())) {
            Page<ManOrderVo> manOrderVos = null;
            if (searchBo.getPattern() == 2) {
                // 按用户名/手机号搜索
                manOrderVos = orderMapper.selectOrderListByUser(pageQuery.build(), searchBo);
                // 添加书籍信息
                manOrderVos.getRecords().forEach(item -> {
                    Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                            .select(Book::getBookName, Book::getBookCover)
                            .eq(Book::getBookId, item.getBookId()));
                    item.setBookCover(book.getBookCover());
                    item.setBookName(book.getBookName());
                });
            } else {
                manOrderVos = orderMapper.selectOrderListByBook(pageQuery.build(), searchBo);
                // 添加用户信息
                manOrderVos.getRecords().forEach(item -> {
                    User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                            .select(User::getUsername, User::getPhone)
                            .eq(User::getUserId, item.getUserId()));
                    item.setUsername(user.getUsername());
                    item.setPhone(user.getPhone());
                });
            }
            return PageData.build(manOrderVos.getTotal(), manOrderVos.getRecords());
        } else {
            // 按订单id搜索
            Page<Order> orderPage = orderMapper.selectPage(pageQuery.build(), new LambdaQueryWrapper<Order>()
                    .eq(ObjUtil.isNotNull(searchBo.getPattern()) && searchBo.getPattern() == 1 && ObjUtil.isNotNull(searchBo.getVal())
                            , Order::getOrderId, searchBo.getVal())
                    .eq(ObjUtil.isNotNull(searchBo.getStatus()), Order::getStatus, searchBo.getStatus())
                    .orderByDesc(Order::getOrderDate));
            List<ManOrderVo> orderList = new ArrayList<>(orderPage.getRecords().size());
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
    public List<TypeVo> getTypeList() {
        List<Type> types = typeMapper.selectList(new LambdaQueryWrapper<Type>()
                .orderByDesc(Type::getTypeId));
        List<TypeVo> typeVos = BeanUtil.copyToList(types, TypeVo.class);
        return typeVos;
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
